package com.rhdzmota.fbmessenger.webhook.model.attachment

import org.scalatest._

import io.circe.parser.decode
import io.circe.generic.auto._

class PayloadSpec extends FlatSpec with Matchers {

  "An Url payload " should "jsonify correctly" in {
    val url = Url("<ATTACHMENT_URL>")
    val jsonString = "{\"url\": \"<ATTACHMENT_URL>\"}"
    decode[Url](jsonString).foreach(x => {
      x shouldBe url
      x.toJson shouldBe url.toJson
    })
  }

  "A Location payload" should "jsonify corretly" in {
    val coordinates = Coordinates(0.0, 0.0)
    val locationPayload = LocationPayload(coordinates)
    val jsonString = "{\"coordinates\": {\"lat\": 0.0, \"long\": 0.0}}"
    decode[LocationPayload](jsonString).foreach(x => {
      x shouldBe locationPayload
      x.toJson shouldBe locationPayload.toJson
    })
  }

}
