package kamino.hostmetricsserver

import com.linecorp.armeria.common.{HttpResponse, HttpStatus}
import com.linecorp.armeria.server.docs.DocService
import com.linecorp.armeria.server.{HttpService, Server}
import com.typesafe.scalalogging.Logger
import kamon.Kamon
import pureconfig._
import pureconfig.generic.auto._

object EntryPoint extends App {
  Kamon.init()
  private val logger = Logger(getClass)

  private def aranaServer(port: Port): Server = {
    val status: HttpService = (_, _) => HttpResponse.of(HttpStatus.OK)
    val docs = new DocService()

    Server
      .builder()
      .http(port.number)
      .service("/status", status)
      .serviceUnder("/docs", docs)
      .build()
  }

  private def run(cfg: Config): Unit = this.synchronized {
    logger.debug("Starting Host Metrics Process ...")
    while (true) {
      try this.wait(2000)
      catch {
        case e: InterruptedException =>
          logger.warn("Interrupted", e)
      }
    }
    val arana = aranaServer(cfg.port)
    arana.start().join()
    logger.info("Host Metrics Server is up")

    Runtime.getRuntime.addShutdownHook(new Thread() {
      override def run(): Unit = {
        logger.debug("Stopping Host Metrics Server ...")
        arana.stop().join()
        logger.info("Host Metrics Server is shut down")
        Kamon.stopModules()
      }
    })
  }

  logger.debug("Loading configuration ...")
  ConfigSource.default.load[Config] match {
    case Right(config)  =>
      Runtime.getRuntime.addShutdownHook(new Thread() {
        override def run(): Unit = {
          logger.info("Host Metrics Process is shutting down")
          Kamon.stopModules()
        }
      })

      run(config)
    case Left(failures) =>
      logger.warn("Failed to load configuration")
      failures.toList.foreach(f => logger.error(s"Configuration load failure: $f"))
  }

}
