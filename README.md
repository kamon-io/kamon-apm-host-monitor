Kamon Host Monitor
==================

The purpose of this service is to report host metrics to Kamon APM.
One instance should be deployed to every node in a cluster. Already
existing Docker image can be used for convenience.

Parameters
----------

Service can be parameterized through environment variables. Those are:

* `KAMON_API_KEY` *mandatory*: API key used by Kamon APM for ingesting the metrics
* `KAMON_INGESTION_API` *optional*: The ingestion endpoint, by default `https://ingestion.apm.kamon.io/v1`
* `KAMON_HOST_METRICS_PORT` *optional*: Service's port number, by default `18081`

Usage
-----

Start the Kamon Host Monitor container by running:
```shell
$ docker run --rm --network=host -e KAMON_API_KEY=<api key> kamon/host-monitor
```
The service will start reporting host metrics to your Kamon APM environment associated with the API key.
`--network=host` is needed for the service to properly report host's name.

Host Monitor's `/status` endpoint can be used for liveness probe.
```shell
$ curl http://localhost:18081/status
200 OK
```

Dev Stuff
---------

Project is handled in the usual `sbt` way:
```shell
# compile
$ sbt compile

# publish docker image
$ sbt docker:publish
```

Docker image building and publishing process will add Git's last commit id and `latest` tags to the published image.