package kamon.hostmonitor

import com.typesafe.scalalogging.Logger
import kamon.Kamon
import oshi.util.GlobalConfig

import java.nio.file.{Files, Paths}

object EntryPoint extends App {
  {
    val procPath = sys.env.getOrElse("PROCFS_PATH", "/host/proc")
    if (Files.exists(Paths.get(procPath))) {
      GlobalConfig.set("oshi.util.proc.path", procPath)
    }
  }

  Kamon.loadModules()
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
