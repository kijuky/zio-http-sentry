# Sample ZIO HTTP + Sentry

# Prepare

You need to enter the correct DNS in ["input your dns"](src/main/scala/SentryService.scala).

# Run

```shell
sbt run
```

```shell
curl http://localhost:8080/error
```

An error will be logged in Sentry.
