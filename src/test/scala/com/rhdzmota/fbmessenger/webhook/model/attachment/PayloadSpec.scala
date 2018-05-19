package com.rhdzmota.fbmessenger.webhook.model.attachment

import org.scalatest._

import io.circe.parser.decode
import io.circe.syntax._
import com.rhdzmota.fbmessenger.webhook.model.implicits.Encoders._
import com.rhdzmota.fbmessenger.webhook.model.implicits.Decoders._

class PayloadSpec extends FlatSpec with Matchers {

  "An Url payload " should "jsonify correctly" in {
    val url = Url("<ATTACHMENT_URL>")
    val jsonString = "{\"url\": \"<ATTACHMENT_URL>\"}"
    val result = decode[Url](jsonString)
    result shouldBe 'right
    result match {
      case Left(error) => error
      case Right(x)    =>
        x shouldBe url
        x.asJson shouldBe url.asJson
    }
  }

  "A Location payload" should "jsonify corretly" in {
    val coordinates = Coordinates(0.0, 0.0)
    val locationPayload = LocationPayload(coordinates)
    val jsonString = "{\"coordinates\": {\"lat\": 0.0, \"long\": 0.0}}"
    val result = decode[LocationPayload](jsonString)
    result shouldBe 'right
    result match {
      case Left(error) => error
      case Right(x)    =>
        x shouldBe locationPayload
        x.asJson shouldBe locationPayload.asJson
    }
  }

}
