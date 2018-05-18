package com.rhdzmota.fbmessenger.webhook.model.attachment

import org.scalatest._

import io.circe.parser.decode
import io.circe.syntax._
import com.rhdzmota.fbmessenger.webhook.model.Decoders._
import com.rhdzmota.fbmessenger.webhook.model.Encoders._

class PayloadSpec extends FlatSpec with Matchers {

  "An Url payload " should "jsonify correctly" in {
    val url = Url("<ATTACHMENT_URL>")
    val jsonString = "{\"url\": \"<ATTACHMENT_URL>\"}"
    decode[Url](jsonString).foreach(x => {
      x shouldBe url
      x.asJson shouldBe url.asJson
    })
  }

  "A Location payload" should "jsonify corretly" in {
    val coordinates = Coordinates(0.0, 0.0)
    val locationPayload = LocationPayload(coordinates)
    val jsonString = "{\"coordinates\": {\"lat\": 0.0, \"long\": 0.0}}"
    decode[LocationPayload](jsonString).foreach(x => {
      x shouldBe locationPayload
      x.asJson shouldBe locationPayload.asJson
    })
  }

}
