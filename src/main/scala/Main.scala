package com.example

import zio.*
import zio.http.*

object Main extends ZIOAppDefault:
  override val bootstrap: ZLayer[ZIOAppArgs, Nothing, Unit] =
    // sentry
    SentryService.layer

  def run: RIO[Environment, Nothing] =
    val app = {
      Routes(
        Method.GET / "error" ->
          handler(throw new RuntimeException("This is a test."))
      )
    } @@ SentryService.middleware

    Server
      .serve(app.toHttpApp)
      .provide(
        // http
        Server.defaultWithPort(8080)
      )

  