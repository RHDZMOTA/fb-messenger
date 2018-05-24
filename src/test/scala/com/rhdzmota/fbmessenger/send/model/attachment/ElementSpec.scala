package com.rhdzmota.fbmessenger.send.model.attachment

import org.scalatest.{FlatSpec, Matchers}

import io.circe.syntax._
import io.circe.parser.decode

import com.rhdzmota.fbmessenger.send.model.attachment.button._
import com.rhdzmota.fbmessenger.send.model.attachment._
import com.rhdzmota.fbmessenger.send.model.implicits.Encoders._
import com.rhdzmota.fbmessenger.send.model.implicits.Decoders._

class ElementSpec extends FlatSpec with Matchers {
  "An Element with just title and subtitle" should "encode/decode correctly" in {
    val element = Element.create("<TITLE>", "<SUBTITLE>")
    val jsonString =
      """{
        |  "title": "<TITLE>",
        |  "subtitle": "<SUBTITLE>",
        |  "buttons": []
        |}""".stripMargin
    val result = decode[Element](jsonString)
    result shouldBe 'right
    element match {
      case Some(elm) => result match {
        case Left(error) => fail("failure: " + error.toString)
        case Right(x) =>
          x shouldBe elm
          x.asJson shouldBe elm.asJson
      }
      case None => fail("failure: element = None")
    }
  }

  "An Element with title, subtitle and buttons" should "encode/decode correctly" in {
    val firstButton = Button.url("<TITLE>", "<URL>", "<FALLBACK-URL>")
    val secondButton = Button.share
    val element: Option[Element]  = for {
      first   <- firstButton
      second  <- secondButton
      element <- Element.create("<TITLE>", "<SUBTITLE>", None, List(first, second))
    } yield element
    val jsonString =
      """{
        |  "title": "<TITLE>",
        |  "subtitle": "<SUBTITLE>",
        |  "buttons": [
        |    {
        |      "type": "web_url",
        |      "title": "<TITLE>",
        |      "url": "<URL>",
        |      "webview_height_ratio": "full",
        |      "fallback_url": "<FALLBACK-URL>"
        |    },
        |    {
        |      "type": "element_share"
        |    }
        |  ]
        |}""".stripMargin
    val result = decode[Element](jsonString)
    result shouldBe 'right
    element match {
      case Some(elm) => result match {
        case Left(error) => fail("failure: " + error.toString)
        case Right(x) =>
          x shouldBe elm
          x.asJson shouldBe elm.asJson
      }
      case None => fail("failure: element = None")
    }
  }
}
