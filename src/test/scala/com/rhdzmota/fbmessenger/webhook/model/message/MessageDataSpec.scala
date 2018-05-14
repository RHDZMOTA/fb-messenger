package com.rhdzmota.fbmessenger.webhook.model.message

import com.rhdzmota.fbmessenger.webhook.model.attachment._
import org.scalatest._
import io.circe.parser.decode
import io.circe.generic.auto._
import io.circe.syntax._

class MessageDataSpec extends FlatSpec with Matchers {

  "A Text message data" should "jsonify correctly" in {
    val quickReply = QuickReply("DEVELOPER_DEFINED_PAYLOAD")
    val text = Text("<MID>", "<TEXT>", quickReply)
    val jsonString = "{\"mid\": \"<MID>\", \"text\": \"<TEXT>\", \"quick_reply\": {\"payload\": \"<DEVELOPER_DEFINED_PAYLOAD>\"}}"

    decode[Text](jsonString).foreach(x => {
      x shouldBe text
      x.asJson shouldBe text.asJson
    })
  }

  "A message data WithAttachment" should "jsonify correctly" in {
    val url = Url("<ATTACHMENT_URL>")
    val simpleAttachment = SimpleAttachment("image", Some(url))
    val withAttachment = WithAttachment("<MID>", 0, List(simpleAttachment))
    val jsonString = "{\"mid\": <MID>, \"sql\": 0, \"attachments\": [{\"type\": \"image\", \"payload\": {\"url\": \"<ATTACHEMENT_URL>\"}}]}"
    decode[WithAttachment](jsonString).foreach(x => {
      x shouldBe withAttachment
      x.asJson shouldBe withAttachment.asJson
    })
  }

  "A message data WithFallback" should "jsonify correctly" in {
    val fallback = Fallback("fallback", None, "<TITLE>", "<URL>")
    val withFallback = WithFallback("<MID>", "<URL_SENT_BY_THE_USER>", List(fallback))
    val jsonString = "{\"mid\": \"<MID>\", \"text\": \"<URL_SENT_BY_THE_USER>\", \"attachments\": [{\"type\": \"fallback\", \"payload\": null, \"title\": \"<TITLE>\", \"URL\": \"<URL>\"}]"
    decode[WithFallback](jsonString).foreach(x => {
      x shouldBe withFallback
      x.asJson shouldBe withFallback.asJson
    })
  }

  "A message data WithLocation" should "jsonify correctly" in {
    val coordinates = Coordinates(0.0, 0.0)
    val locationPayload = LocationPayload(coordinates)
    val location = Location("<TITLE>", "<URL>", "location", Some(locationPayload))
    val withLocation = WithLocation("<MID>", 0, List(location))
    val jsonString = "{\"mid\": \"<MID>\", \"seq\": 0, \"attachments\": [{\"title\": \"<TITLE>\", \"url\": \"<URL>\", \"type\": \"location\", \"payload\": {\"coordinates\": {\"lat\": 0.0, \"long\": 0.0}}}]}"
    decode[WithLocation](jsonString).foreach(x => {
      x shouldBe withLocation
      x.asJson shouldBe withLocation.asJson
    })
  }

}