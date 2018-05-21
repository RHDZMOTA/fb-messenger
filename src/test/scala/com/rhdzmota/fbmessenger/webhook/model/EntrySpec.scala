package com.rhdzmota.fbmessenger.webhook.model

import org.scalatest.{FlatSpec, Matchers}

import io.circe.parser.decode
import io.circe.syntax._

import com.rhdzmota.fbmessenger.webhook.model.attachment.{Coordinates, Location, LocationPayload}
import com.rhdzmota.fbmessenger.webhook.model.implicits.Encoders._
import com.rhdzmota.fbmessenger.webhook.model.implicits.Decoders._
import com.rhdzmota.fbmessenger.webhook.model.message._

class EntrySpec extends FlatSpec with Matchers {

  "An Entry with a single message with location" should "jsonify correcty" in {
    val coordinates = Coordinates(0.0, 0.0)
    val locationPayload = LocationPayload(coordinates)
    val location = Location("<TITLE>", "<URL>", "location", Some(locationPayload))
    val withLocation = WithLocation("<MID>", 0, List(location))
    val message = Message(Participant("<PSID>"), Participant("<PAGE_ID>"), 0, withLocation)
    val entry = Entry("<PAGE_ID>", 0, List(message))
    val jsonString =
      """{
        |  "id": "<PAGE_ID>",
        |  "time": 0,
        |  "messaging": [
        |    {
        |      "sender": {
        |        "id": "<PSID>"
        |       },
        |      "recipient": {
        |        "id": "<PAGE_ID>"
        |      },
        |      "timestamp": 0,
        |      "message": {
        |        "mid": "<MID>",
        |        "seq": 0,
        |        "attachments": [
        |          {
        |            "title": "<TITLE>",
        |            "url": "<URL>",
        |            "type": "location",
        |            "payload": {
        |              "coordinates": {
        |                "lat": 0.0,
        |                "long": 0.0
        |              }
        |            }
        |          }
        |        ]
        |      }
        |    }
        |  ]
        |}""".stripMargin
    val result = decode[Entry](jsonString)
    result shouldBe 'right
    result match {
      case Left(error) => fail(s"failure: ${error.toString}")
      case Right(x)    =>
        x shouldBe entry
        x.asJson shouldBe entry.asJson
    }
  }

  "An Entry with a multiple text messages" should "jsonify correcty" in {

    val quickReply = QuickReply("<DEVELOPER_DEFINED_PAYLOAD>")
    val firstText  = Text("<MID>", "<TEXT-1>", quickReply)
    val secondText = Text("<MID>", "<TEXT-2>", quickReply)
    val firstMessage  = Message(Participant("<PSID>"), Participant("<PAGE_ID>"), 0, firstText)
    val secondMessage = Message(Participant("<PSID>"), Participant("<PAGE_ID>"), 1, secondText)
    val entry = Entry("<PAGE_ID>", 0, List(firstMessage, secondMessage))
    val jsonString =
      """{
        |  "id": "<PAGE_ID>",
        |  "time": 0,
        |  "messaging": [
        |    {
        |      "sender": {
        |        "id": "<PSID>"
        |       },
        |      "recipient": {
        |        "id": "<PAGE_ID>"
        |      },
        |      "timestamp": 0,
        |      "message": {
        |        "mid": "<MID>",
        |        "text": "<TEXT-1>",
        |         "quick_reply": {
        |           "payload": "<DEVELOPER_DEFINED_PAYLOAD>"
        |         }
        |      }
        |    },
        |    {
        |      "sender": {
        |        "id": "<PSID>"
        |       },
        |      "recipient": {
        |        "id": "<PAGE_ID>"
        |      },
        |      "timestamp": 1,
        |      "message": {
        |        "mid": "<MID>",
        |        "text": "<TEXT-2>",
        |         "quick_reply": {
        |           "payload": "<DEVELOPER_DEFINED_PAYLOAD>"
        |         }
        |      }
        |    }
        |  ]
        |}""".stripMargin
    val result = decode[Entry](jsonString)
    result shouldBe 'right
    result match {
      case Left(error) => fail(s"failure: ${error.toString}")
      case Right(x)    =>
        x shouldBe entry
        x.asJson shouldBe entry.asJson
    }
  }

}
