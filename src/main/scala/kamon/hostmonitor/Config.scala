package kamon.hostmonitor

case class Port(number: Int) extends AnyVal

case class Config(
  port: Port
)
