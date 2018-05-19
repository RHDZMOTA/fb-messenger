package com.rhdzmota.fbmessenger.webhook.model.implicits

import com.rhdzmota.fbmessenger.webhook.model._
import com.rhdzmota.fbmessenger.webhook.model.message._
import com.rhdzmota.fbmessenger.webhook.model.attachment._

import io.circe.Decoder
import io.circe.generic.extras.Configuration
import io.circe.generic.extras.semiauto.deriveDecoder

object Decoders {
  implicit val customConfig: Configuration = Configuration.default.withSnakeCaseMemberNames.withDefaults
  implicit val decodeLocationPayload: Decoder[LocationPayload] = deriveDecoder[LocationPayload]
  implicit val decodeUrl: Decoder[Url] = deriveDecoder[Url]
  implicit val decodeAbsent: Decoder[Absent] = deriveDecoder[Absent]
  implicit val decodeCoordinates: Decoder[Coordinates] = deriveDecoder[Coordinates]
  implicit val decodeSimpleAttachment: Decoder[SimpleAttachment] = deriveDecoder[SimpleAttachment]
  implicit val decodeFallback: Decoder[Fallback] = deriveDecoder[Fallback]
  implicit val decodeLocation: Decoder[Location] = deriveDecoder[Location]
  implicit val decodeQuickReply: Decoder[QuickReply] = deriveDecoder[QuickReply]
  implicit val decodeParticipant: Decoder[Participant] = deriveDecoder[Participant]
  implicit val decodeText: Decoder[Text] = deriveDecoder[Text]
  implicit val decodeWithAttachment: Decoder[WithAttachment] = deriveDecoder[WithAttachment]
  implicit val decodeWithFallback: Decoder[WithFallback] = deriveDecoder[WithFallback]
  implicit val decodeWithLocation: Decoder[WithLocation] = deriveDecoder[WithLocation]
  implicit val decodeBigInt: Decoder[BigInt] = deriveDecoder[BigInt]
  implicit val decodeMessageData: Decoder[MessageData] = deriveDecoder[MessageData]
  implicit val decodeMessage: Decoder[Message] = deriveDecoder[Message]
  implicit val decodeEntry: Decoder[Entry] = deriveDecoder[Entry]
  implicit val decodeEvent: Decoder[Event] = deriveDecoder[Event]
}
