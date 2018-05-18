package com.rhdzmota.fbmessenger.webhook.model.implicits

import com.rhdzmota.fbmessenger.webhook.model._
import com.rhdzmota.fbmessenger.webhook.model.message._
import com.rhdzmota.fbmessenger.webhook.model.attachment._

import io.circe.Encoder
import io.circe.generic.semiauto.deriveEncoder

object Encoders {
  implicit val encodeLocationPayload: Encoder[LocationPayload] = deriveEncoder[LocationPayload]
  implicit val encodeUrl: Encoder[Url] = deriveEncoder[Url]
  implicit val encodeAbsent: Encoder[Absent] = deriveEncoder[Absent]
  implicit val encodeCoordinates: Encoder[Coordinates] = deriveEncoder[Coordinates]
  implicit val encodeSimpleAttachment: Encoder[SimpleAttachment] = deriveEncoder[SimpleAttachment]
  implicit val encodeFallback: Encoder[Fallback] = deriveEncoder[Fallback]
  implicit val encodeLocation: Encoder[Location] = deriveEncoder[Location]
  implicit val encodeQuickReply: Encoder[QuickReply] = deriveEncoder[QuickReply]
  implicit val encodeParticipant: Encoder[Participant] = deriveEncoder[Participant]
  implicit val encodeText: Encoder[Text] = deriveEncoder[Text]
  implicit val encodeWithAttachment: Encoder[WithAttachment] = deriveEncoder[WithAttachment]
  implicit val encodeWithFallback: Encoder[WithFallback] = deriveEncoder[WithFallback]
  implicit val encodeWithLocation: Encoder[WithLocation] = deriveEncoder[WithLocation]
  implicit val encodeBigInt: Encoder[BigInt] = deriveEncoder[BigInt]
  implicit val encodeMessageData: Encoder[MessageData] = deriveEncoder[MessageData]
  implicit val encodeMessage: Encoder[Message] = deriveEncoder[Message]
  implicit val encodeEntry: Encoder[Entry] = deriveEncoder[Entry]
  implicit val encodeEvent: Encoder[Event] = deriveEncoder[Event]
}
