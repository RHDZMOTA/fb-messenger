package com.rhdzmota.fbmessenger.send.model.reply

import org.scalatest.{FlatSpec, Matchers}

import io.circe.parser.decode
import io.circe.syntax._

import com.rhdzmota.fbmessenger.send.model.reply._
import com.rhdzmota.fbmessenger.send.model.message._
import com.rhdzmota.fbmessenger.send.model.message.quickreply._
import com.rhdzmota.fbmessenger.send.model.implicits.Decoders._
import com.rhdzmota.fbmessenger.send.model.implicits.Encoders._

class ReplySpec extends FlatSpec with Matchers {

  "A Reply WithMessage and without quick replies" should "encode/decode correctly" in {
    val recipient = Recipient("<PSID>")
    val withText = WithText("<TEXT-1>", None)
    val withMessage = Reply.withDefaultConfigMessage(recipient, withText)
    val jsonString =
      """{
        |  "messaging_type": "RESPONSE",
        |  "recipient": {
        |    "id": "<PSID>"
        |  },
        |  "message": {
        |    "text": "<TEXT-1>"
        |  },
        |  "notification_type": "REGULAR"
        |}""".stripMargin
    val result = decode[WithMessage](jsonString)
    result shouldBe 'right
    result match {
      case Left(error) => fail("failure: " + error.toString)
      case Right(x)    =>
        x shouldBe withMessage
        x.asJson shouldBe withMessage.asJson
    }
  }

  "A Reply WithMessage and with one quick replies (text)" should "encode/decode correctly" in {
    val recipient = Recipient("<PSID>")
    val quickReply = Text("text", "<TITLE>", "<PAYLOAD>", None)
    val withText = WithText("<TEXT-1>", Some(List(quickReply)))
    val withMessage = Reply.withDefaultConfigMessage(recipient, withText)
    val jsonString =
      """{
        |  "messaging_type": "RESPONSE",
        |  "recipient": {
        |    "id": "<PSID>"
        |  },
        |  "message": {
        |    "text": "<TEXT-1>",
        |    "quick_replies": [
        |      {
        |        "content_type": "text",
        |        "title": "<TITLE>",
        |        "payload": "<PAYLOAD>"
        |      }
        |    ]
        |  },
        |  "notification_type": "REGULAR"
        |}""".stripMargin
    val result = decode[WithMessage](jsonString)
    result shouldBe 'right
    result match {
      case Left(error) => fail("failure: " + error.toString)
      case Right(x)    =>
        x shouldBe withMessage
        x.asJson shouldBe withMessage.asJson
    }
  }
}
