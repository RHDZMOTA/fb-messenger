package com.rhdzmota.fbmessenger.send.model.attachment.payload

import org.scalatest.{FlatSpec, Matchers}

import io.circe.syntax._
import io.circe.parser.decode

import com.rhdzmota.fbmessenger.send.model.attachment._
import com.rhdzmota.fbmessenger.send.model.attachment.payload._
import com.rhdzmota.fbmessenger.send.model.implicits.Decoders._
import com.rhdzmota.fbmessenger.send.model.implicits.Encoders._

class PayloadSpec extends FlatSpec with Matchers {

  "An RichMedia Attachment" should "encode/decode correctly" in {
    val richMediaPayload = Payload.richMedia("<URL>")
    val jsonString =
      """{
        |  "url": "<URL>"
        |}""".stripMargin
    val result = decode[RichMedia](jsonString)
    result shouldBe 'right
    richMediaPayload match {
      case Some(richMedia) => result match {
        case Left(error) => fail("failure: " + error.toString)
        case Right(x) =>
          x shouldBe richMedia
          x.asJson shouldBe richMedia.asJson
      }
      case None => fail("failure: richMediaPayload = None")
    }
  }

  // TODO: complete tests
}
