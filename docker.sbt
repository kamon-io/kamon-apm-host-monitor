dockerBaseImage := "openjdk:8-jre-slim"

dockerExposedPorts := Seq(5266)
dockerRepository := Some("kamon")
dockerUpdateLatest := true
packageName in Docker := "host-monitor"
maintainer in Docker := "kamon.io"
