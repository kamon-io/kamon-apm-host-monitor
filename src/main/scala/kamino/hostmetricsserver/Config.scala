package kamino.hostmetricsserver

case class Port(number: Int) extends AnyVal

case class Config(
  port: Port
)
