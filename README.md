Kamon APM Host Monitor
======================

The purpose of this service is to report host metrics to Kamon APM.
One instance should be deployed to every node in a cluster. Already
existing Docker image can be used for convenience.

Parameters
----------

Service can be parameterized through environment variables. Those are:

* `KAMON_API_KEY` *mandatory*: API key used by Kamon APM for ingesting the metrics
* `KAMON_INGESTION_API` *optional*: The ingestion endpoint, by default `https://ingestion.apm.kamon.io/v1`
* `KAMON_STATUS_PAGE_PORT` *optional*: Service's Status page port number, by default `5266`

Usage
-----

Start the Kamon APM Host Monitor container by running:
```shell
$ docker run --rm --network=host -e KAMON_API_KEY=<api key> kamon/apm-host-monitor
```
The service will start reporting host metrics to your Kamon APM environment associated with the API key.
Using `--network=host` is the easiest way for the Kamon Host Monitor to know and report the correct host name.

Alternatively, `--hostname` option could be used instead of `--network` to the same effect (bash example):
```shell
$ docker run --rm --hostname=`hostname` -e KAMON_API_KEY=<api key> -p 5266:5266 kamon/apm-host-monitor
```
Note that, in this case, for Status page to be accessible its port needs to be exposed.

Kamon's Status page can be used for liveness probe:
```shell
$ curl -I -XGET http://localhost:5266
HTTP/1.1 200 OK 
Content-Type: text/html
Date: Tue, 23 Mar 2021 08:38:37 GMT
connection: close
Transfer-Encoding: chunked
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

Docker image building and publishing process will tag the published image with Git's last commit id and `latest` tags.
