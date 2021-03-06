package com.rhdzmota.fbmessenger.webhook.model.attachment

import org.scalatest._

import io.circe.parser.decode
import io.circe.syntax._
import com.rhdzmota.fbmessenger.webhook.model.implicits.Encoders._
import com.rhdzmota.fbmessenger.webhook.model.implicits.Decoders._

class AttachmentSpec extends FlatSpec with Matchers {

  "A SimpleAttachment(...)" should "jsonify correctly" in {
    val url = Url("<ATTACHMENT_URL>")
    val simpleAttachment = SimpleAttachment("image", Some(url))
    val jsonString = "{\"type\": \"image\", \"payload\": {\"url\": \"<ATTACHMENT_URL>\"}}"
    val result = decode[SimpleAttachment](jsonString)
    result shouldBe ('right)
    result match {
      case Left(error) => error
      case Right(x)    =>
        x shouldBe simpleAttachment
        x.asJson shouldBe simpleAttachment.asJson
    }
  }

  "A Fallback(...)" should "jsonify correctly" in {
    val fallback = Fallback("fallback", None, "<TITLE>", "<URL>")
    val jsonString = "{\"type\": \"fallback\", \"payload\": null, \"title\": \"<TITLE>\", \"URL\": \"<URL>\"}"
    val result = decode[Fallback](jsonString)
    result shouldBe 'right
    result match {
      case Left(error) => error
      case Right(x)    =>
        x shouldBe fallback
        x.asJson shouldBe fallback.asJson
    }
  }

  "A Location(...)" should "jsonify correctly" in {
    val coordinates = Coordinates(0.0, 0.0)
    val locationPayload = LocationPayload(coordinates)
    val location = Location("<TITLE>", "<URL>", "location", Some(locationPayload))
    val jsonString = "{\"title\": \"<TITLE>\", \"url\": \"<URL>\", \"type\": \"location\", \"payload\": {\"coordinates\": {\"lat\": 0.0, \"long\": 0.0}}}"
    val result = decode[Location](jsonString)
    result shouldBe 'right
    result match {
      case Left(error) => error
      case Right(x)    =>
        x shouldBe location
        x.asJson shouldBe location.asJson
    }
  }

}
