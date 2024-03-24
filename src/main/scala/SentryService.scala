package com.example

import io.sentry.{Sentry, SentryLevel}
import org.unbescape.html.HtmlEscape
import zio.*
import zio.http.*

import java.util.Scanner
import scala.util.chaining.*

object SentryService:
  val layer: URLayer[Any, Unit] =
    ZLayer.fromZIO:
      ZIO.succeed:
        Sentry.init: options =>
          options.setDsn("input your dns")

  def middleware: HandlerAspect[Any, Unit] =
    HandlerAspect.updateResponse:
      _.tap:
        _.headers.iterator
          .filter(_.isInstanceOf[Header.Warning])
          .map(_.asInstanceOf[Header.Warning])
          .filter(_.text.startsWith("Exception"))
          .foreach: w =>
            val scn = new Scanner(HtmlEscape.unescapeHtml(w.text))
            val name = scn.useDelimiter(":").next().split(" ").last
            val text = scn.next().lines().findFirst().orElse("").trim
            Sentry.captureMessage(text, SentryLevel.ERROR)
