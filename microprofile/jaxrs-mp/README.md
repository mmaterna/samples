
JAXRS-MP
=========

JAX-RS and MicroProfile samples.
Checked on Wildfly, Openliberty.


Configuration
=============

Openliberty server.xml: `openliberty\server.xml`


OpenAPI 3 + OpenAPI-UI
======================

Added libraries to generate OpenApi definition of JAX-RS services. This is only needed when MicroProfile server does not implement MicroProfile OpenAPI.

Two dependencies are needed:

```
<dependency>
	<groupId>io.swagger.core.v3</groupId>
	<artifactId>swagger-jaxrs2</artifactId>
	<version>2.0.6</version>
</dependency>
<dependency>
	<groupId>io.swagger.core.v3</groupId>
	<artifactId>swagger-jaxrs2-servlet-initializer</artifactId>
	<version>2.0.6</version>
</dependency>
```

Additionally Swagger-UI is added:

```
<dependency>
	<groupId>org.webjars</groupId>
	<artifactId>swagger-ui</artifactId>
	<version>3.17.4</version>
</dependency>
```
plus html page `swagger-ui.html` in main webapp directory.

Url to check openapi.json:

Wildfly: `http://localhost:8080/api/openapi.json`
Openliberty: `http://localhost:8080/openapi`

Url to swagger-ui:

Wildfly: `http://localhost:8080/swagger-ui.html`
Openliberty: `http://localhost:8080/openapi/ui`

Microprofile Config
====================
Tested on:
- Wildfly 15
- Openliberty 18

Based on: https://openliberty.io/guides/microprofile-config.html

Application url:
http://localhost:8080/api/configurations

There are three default configuration sources as following:
1. System properties has a default ordinal of 400. (e.g. bootstrap.properties file)
1. Environment variables has a default ordinal of 300. (e.g. server.env file)
1. The META-INF/microprofile-config.properties configuration property file on the classpath has a default ordinal of 100.

Default oridinal can be changed, just add property "config_ordinal" with new value. Example in `microprofile-config.properties` and `CustomConfigSource`.

If configuration property is defined to Inject, but not present - exception is thrown.

Used configurations:
- `META-INF/microprofile-config.properties` - e.g. developer properties
- `pl.mmat.samples.mp.config.CustomConfigSource` - custom configuration (see next paragraph)
- `pl.mmat.samples.mp.config.AppContextListener` - simulate adding property at system properties level (post construct)

Custom configuration with dynamic values:
-----------------------------------------

1. Add new class implementing ConfigSource (CustomConfigSource)
1. Create file `org.eclipse.microprofile.config.spi.ConfigSource` in `META-INF/services` with fully qualified name of new Config Source class.
1. Custom config is periodically refreshed
1. Dynamic injection via: Provider<T>
1. Supports conversion to common types (Boolean, Date, Integer etc.). Custom converters by implementing `org.eclipse.microprofile.config.spi.Converter<T>`, and registration in `META-INF/services/org.eclipse.microprofile.config.spi.Converter` file.
1. Order of custom configuration source is determined by `getOrdinal` method - higher values are more important. If value is found in the highest ordinal, then next methods are not called


Microprofile Health
====================

Tested on:
- Wildfly 15
- Openliberty 18

Url Wildfly: `http://localhost:9990/health`
Url Openliberty: `http://localhost:8080/health`

Microprofile Metrics
====================

Tested on:
- Wildfly 15
- Openliberty 18

Url Wildfly: 
`http://localhost:9990/metrics`
`http://localhost:9990/metrics/application`

Url Openliberty:
`http://localhost:8080/metrics`


Rest endpoints
`http://localhost:8080/api/metrics/method-time`
`http://localhost:8080/api/metrics/method-count-monotonic`
`http://localhost:8080/api/metrics/method-count-concurrent`



OpenTracing
===========

Start Jaeger from docker:

```
docker run --rm --name jaeger -p6831:6831/udp -p16686:16686 jaegertracing/all-in-one:1.6
```

Activate Jaeger reporting on Wildfly - add environment variables:

```
JAEGER_REPORTER_LOG_SPANS=true
JAEGER_SAMPLER_TYPE=const
JAEGER_SAMPLER_PARAM=1
JAEGER_SERVICE_NAME=<app-name>
```

View Jaeger data:
http://localhost:16686/search




