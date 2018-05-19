package com.rhdzmota.fbmessenger.webhook.model.message

import org.scalatest._

import io.circe.parser.decode
import io.circe.syntax._

import com.rhdzmota.fbmessenger.webhook.model.attachment.{SimpleAttachment, Url}
import com.rhdzmota.fbmessenger.webhook.model.implicits.Encoders._
import com.rhdzmota.fbmessenger.webhook.model.implicits.Decoders._

class MessageSpec extends FlatSpec with Matchers {

      "A Message with Text attachment" should "jsonify correctly" in {
        val quickReply = QuickReply("<DEVELOPER_DEFINED_PAYLOAD>")
        val text = Text("<MID>", "<TEXT>", quickReply)
        val message = Message(Participant("<PSID>"), Participant("<PAGE_ID>"), 0, text)
        val textMessageJson = "{\"mid\": \"<MID>\", \"text\": \"<TEXT>\", \"quick_reply\": {\"payload\": \"<DEVELOPER_DEFINED_PAYLOAD>\"}}"
        val jsonString = "{\"sender\": {\"id\": \"<PSID>\"}, \"recipient\": {\"id\": \"<PAGE_ID>\"}, \"timestamp\": 0, \"message\": " + textMessageJson + "}"
        decode[Text](textMessageJson) shouldBe 'right
        val result = decode[Message](jsonString)
        result shouldBe 'right
        result match {
          case Left(error) => fail(s"failure: ${error.toString}")
          case Right(x)    =>
            x shouldBe message
            x.asJson shouldBe message
        }
        decode[Message](jsonString).foreach(x => {
      x shouldBe message
      x.asJson shouldBe message.asJson
    })
  }

  "A Message WithAttachment" should "jsonify correctly" in {
    val url = Url("<ATTACHMENT_URL>")
    val simpleAttachment = SimpleAttachment("image", Some(url))
    val withAttachment = WithAttachment("<MID>", 0, List(simpleAttachment))
  val message = Message(Participant("<PSID>"), Participant("<PAGE_ID>"), 0, withAttachment)
  val jsonString = "{\"sender\": {\"id\": \"<PSID>\"}, \"recipient\": {\"id\": \"<PAGE_ID>\"}, \"timestamp\": 0, " +
    "\"message\": {\"mid\": \"<MID>\", \"seq\": 0, \"attachments\": " +
    "[{\"type\": \"image\", \"payload\": {\"url\": \"<ATTACHMENT_URL>\"}}]}}"
  decode[Message](jsonString).foreach(x => {
    x shouldBe message
    x.asJson shouldBe message.asJson
  })
}

}
