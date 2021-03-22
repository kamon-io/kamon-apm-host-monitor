package kamon.hostmonitor

import com.typesafe.scalalogging.Logger
import kamon.Kamon

object EntryPoint extends App {
  Kamon.init()
  private val logger = Logger(getClass)

  private def run(): Unit = this.synchronized {
    logger.info("Kamon Host Monitor is up")

    Runtime.getRuntime.addShutdownHook(new Thread() {
      override def run(): Unit = {
        logger.info("Stopping Kamon Host Monitor ...")
        Kamon.stopModules()
      }
    })

    while (true) {
      try this.wait(2000)
      catch {
        case _: InterruptedException =>
          logger.info("Interrupted ...")
      }
    }
  }

  run()
}
