package com.rhdzmota.fbmessenger.webhook.model

import com.rhdzmota.fbmessenger.webhook.model.attachment.{Coordinates, Location, LocationPayload}
import com.rhdzmota.fbmessenger.webhook.model.message._
import org.scalatest.{FlatSpec, Matchers}
import io.circe.parser.decode
import io.circe.generic.auto._
import io.circe.syntax._

class EntrySpec extends FlatSpec with Matchers {

  "An Entry " should "jsonify correcty" in {
    val coordinates = Coordinates(0.0, 0.0)
    val locationPayload = LocationPayload(coordinates)
    val location = Location("<TITLE>", "<URL>", "location", Some(locationPayload))
    val withLocation = WithLocation("<MID>", 0, List(location))
    val message = Message(Participant("<PSID>"), Participant("<PAGE_ID>"), 0, withLocation)
    val entry = Entry("<PAGE_ID>", 0, List(message))
    val jsonString = "{\"id\": \"<PAGE_ID>\", \"time\": 0, \"messaging\": [{" +
      "\"sender\": {\"id\": \"<PSID>\"}, \"recipient: {\"id\": \"<PAGE_ID>\"}, \"timestamp\": 0, \"message\": {" +
      "\"mid\": \"<MID>\", \"seq\": 0, \"attachments\": [{" +
      "\"title\": \"<TITLE>\", \"url\": \"<URL>\", \"type\": \"location\", \"payload\": {\"coordinates\": {\"lat\": 0.0, \"long\": 0.0}}" +
      "}]" +
      "}" +
      "}]}"
    decode[Entry](jsonString).foreach(x => {
      x shouldBe entry
      x.asJson shouldBe entry.asJson
    })
  }

}
