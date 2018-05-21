package com.rhdzmota.fbmessenger.webhook.model.implicits

import com.rhdzmota.fbmessenger.webhook.model._
import com.rhdzmota.fbmessenger.webhook.model.message._
import com.rhdzmota.fbmessenger.webhook.model.attachment._

import io.circe.Encoder
import io.circe.syntax._
import io.circe.generic.extras.Configuration
import io.circe.generic.extras.semiauto.deriveEncoder

object Encoders {
  implicit val customConfig: Configuration = Configuration.default.withSnakeCaseMemberNames.withDefaults
  implicit val encodeCoordinates: Encoder[Coordinates] = deriveEncoder[Coordinates]
  implicit val encodeQuickReply: Encoder[QuickReply] = deriveEncoder[QuickReply]
  implicit val encodeParticipant: Encoder[Participant] = deriveEncoder[Participant]
  implicit val encodeBigInt: Encoder[BigInt] = deriveEncoder[BigInt]
  // PAYLOAD
  implicit val encodeLocationPayload: Encoder[LocationPayload] = deriveEncoder[LocationPayload]
  implicit val encodeUrl: Encoder[Url] = deriveEncoder[Url]
  implicit val encodeAbsent: Encoder[Absent] = deriveEncoder[Absent]
  implicit val encodePayload: Encoder[Payload] = Encoder.instance {
    case locationPayload @ LocationPayload(_) => locationPayload.asJson
    case url @ Url(_) => url.asJson
    case absent @ Absent() => absent.asJson
  }
  // ATTACHMENT
  implicit val encodeSimpleAttachment: Encoder[SimpleAttachment] = deriveEncoder[SimpleAttachment]
  implicit val encodeFallback: Encoder[Fallback] = deriveEncoder[Fallback]
  implicit val encodeLocation: Encoder[Location] = deriveEncoder[Location]
  implicit val encodeAttachment: Encoder[Attachment] = Encoder.instance {
    case location @ Location(_, _, _, _) => location.asJson
    case fallback @ Fallback(_, _, _, _) => fallback.asJson
    case simpleAttachment @ SimpleAttachment(_, _) => simpleAttachment.asJson
  }
  // MESSAGE DATA
  implicit val encodeText: Encoder[Text] = deriveEncoder[Text]
  implicit val encodeWithAttachment: Encoder[WithAttachment] = deriveEncoder[WithAttachment]
  implicit val encodeWithFallback: Encoder[WithFallback] = deriveEncoder[WithFallback]
  implicit val encodeWithLocation: Encoder[WithLocation] = deriveEncoder[WithLocation]
  implicit val encodeMessageData: Encoder[MessageData] = Encoder.instance {
    case withLocation @ WithLocation(_, _, _) => withLocation.asJson
    case withAttachment @ WithAttachment(_, _, _) => withAttachment.asJson
    case withFallback @ WithFallback(_, _, _) => withFallback.asJson
    case text @ Text(_, _, _) => text.asJson
  }
  // MESSAGE
  implicit val encodeMessage: Encoder[Message] = deriveEncoder[Message]
  // ENTRY
  implicit val encodeEntry: Encoder[Entry] = deriveEncoder[Entry]
  // EVENT
  implicit val encodeEvent: Encoder[Event] = deriveEncoder[Event]
}
