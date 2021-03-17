import com.typesafe.sbt.packager.docker.DockerChmodType

dockerExposedPorts := Seq(8080)
dockerRepository := Some("kamino")
dockerUpdateLatest := true
packageName in Docker := "host-metrics-server"
maintainer in Docker := "kamino.io"

dockerChmodType := DockerChmodType.UserGroupWriteExecute
daemonUserUid in Docker := Some("777")
daemonGroupGid in Docker := Some("700")
daemonUser in Docker := "demogorgon"
daemonGroup in Docker := "nomad"

