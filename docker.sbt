dockerBaseImage := "openjdk:8-jre-slim"

dockerExposedPorts := Seq(5266)
dockerRepository := Some("kamon")
dockerUpdateLatest := true
packageName in Docker := "apm-host-monitor"
maintainer in Docker := "kamon.io"
