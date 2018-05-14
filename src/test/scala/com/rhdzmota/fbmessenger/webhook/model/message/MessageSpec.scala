package com.rhdzmota.fbmessenger.webhook.model.message

import com.rhdzmota.fbmessenger.webhook.model.attachment.{SimpleAttachment, Url}
import org.scalatest._
import io.circe.parser.decode
import io.circe.generic.auto._
import io.circe.syntax._

class MessageSpec extends FlatSpec with Matchers {

  "A Message with Text attachment" should "jsonify correctly" in {
    val quickReply = QuickReply("DEVELOPER_DEFINED_PAYLOAD")
    val text = Text("<MID>", "<TEXT>", quickReply)
    val message = Message(Sender("<PSID>"), Recipient("<PAGE_ID>"), 0, text)
    val jsonString = "{\"sender\": {\"id\": \"<PSID>\"}, \"recipient\": {\"id\": \"<PAGE_ID>\"}, \"timestamp\": 0, " +
      "\"message\": {\"mid\": \"<MID>\", \"text\": \"<TEXT>\", \"quick_reply\": " +
      "{\"payload\": \"DEVELOPER_DEFINED_PAYLOAD\"}}}"
    decode[Message](jsonString).foreach(x => {
      x shouldBe message
      x.asJson shouldBe message.asJson
    })
  }

  "A Message WithAttachment" should "jsonify correctly" in {
    val url = Url("<ATTACHMENT_URL>")
    val simpleAttachment = SimpleAttachment("image", Some(url))
    val withAttachment = WithAttachment("<MID>", 0, List(simpleAttachment))
    val message = Message(Sender("<PSID>"), Recipient("<PAGE_ID>"), 0, withAttachment)
    val jsonString = "{\"sender\": {\"id\": \"<PSID>\"}, \"recipient\": {\"id\": \"<PAGE_ID>\"}, \"timestamp\": 0, " +
      "\"message\": {\"mid\": \"<MID>\", \"seq\": 0, \"attachments\": " +
      "[{\"type\": \"image\", \"payload\": {\"url\": \"<ATTACHMENT_URL>\"}}]}}"
    decode[Message](jsonString).foreach(x => {
      x shouldBe message
      x.asJson shouldBe message.asJson
    })
  }

}
