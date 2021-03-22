import com.typesafe.sbt.packager.docker.DockerChmodType

dockerExposedPorts := Seq(5266)
dockerRepository := Some("kamon")
dockerUpdateLatest := true
packageName in Docker := "host-monitor"
maintainer in Docker := "kamon.io"

dockerChmodType := DockerChmodType.UserGroupWriteExecute
daemonUserUid in Docker := Some("777")
daemonGroupGid in Docker := Some("700")
daemonUser in Docker := "demogorgon"
daemonGroup in Docker := "nomad"

