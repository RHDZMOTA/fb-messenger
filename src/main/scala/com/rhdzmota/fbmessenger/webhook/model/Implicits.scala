package com.rhdzmota.fbmessenger.webhook.model

import com.rhdzmota.fbmessenger.webhook.model._
import com.rhdzmota.fbmessenger.webhook.model.message._
import com.rhdzmota.fbmessenger.webhook.model.attachment._

import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}

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

object Decoders {
  implicit val decodeUrl: Decoder[Url] = deriveDecoder[Url]
  implicit val decodeLocationPayload: Decoder[LocationPayload] = deriveDecoder[LocationPayload]
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
