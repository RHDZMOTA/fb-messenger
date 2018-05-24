package com.rhdzmota.fbmessenger.send.model.attachment.button

import org.scalatest.{FlatSpec, Matchers}

import io.circe.parser.decode
import io.circe.syntax._

import com.rhdzmota.fbmessenger.send.model.implicits.Decoders._
import com.rhdzmota.fbmessenger.send.model.implicits.Encoders._

class ButtonSpec extends FlatSpec with Matchers {

  "A URL Button" should "encode/decode correctly" in {
    val urlButton: Option[Url] = Button.url("<TITLE>", "<URL>", "<FALLBACK-URL>")
    val jsonString =
      """{
        |  "type": "web_url",
        |  "title": "<TITLE>",
        |  "url": "<URL>",
        |  "webview_height_ratio": "full",
        |  "fallback_url": "<FALLBACK-URL>"
        |}""".stripMargin
    val result = decode[Url](jsonString)
    result shouldBe 'right
    urlButton match {
      case Some(url) => result match {
        case Left(error) => fail("failure: " + error.toString)
        case Right(x)    =>
          x shouldBe url
          x.asJson shouldBe url.asJson
      }
      case _ => fail("failure: urlButton = None")
    }
  }

  "A Share Button" should "encode/decode correctly" in {
    val shareButton: Option[Share] = Button.share
    val jsonString =
      """{
        |  "type": "element_share"
        |}""".stripMargin
    val result = decode[Share](jsonString)
    result shouldBe 'right
    shareButton match {
      case Some(share) => result match {
        case Left(error) => fail("failure: " + error.toString)
        case Right(x)    =>
          x shouldBe share
          x.asJson shouldBe share.asJson
      }
      case None => fail("failure: shareButton = None")
    }
  }

  "A PostbackCall Button" should "encode/decode" in {
    val callButton = Button.call("<TITLE>", "+521234567891")
    val jsonString =
      """{
        |  "type": "phone_number",
        |  "title": "<TITLE>",
        |  "payload": "+521234567891"
        |}""".stripMargin
    val result = decode[PostbackCall](jsonString)
    result shouldBe 'right
    callButton match {
      case Some(call) => result match {
        case Left(error) => fail("failure: " + error.toString)
        case Right(x)    =>
          x shouldBe call
          x.asJson shouldBe call.asJson
      }
      case None => fail("failure: callButton = None")
    }
  }


}
