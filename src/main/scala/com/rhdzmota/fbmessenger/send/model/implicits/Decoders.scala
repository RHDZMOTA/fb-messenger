package com.rhdzmota.fbmessenger.send.model.implicits

import com.rhdzmota.fbmessenger.send.model.reply._
import com.rhdzmota.fbmessenger.send.model.message._
import com.rhdzmota.fbmessenger.send.model.message.quickreply._

import io.circe.Decoder
import io.circe.generic.extras.Configuration
import io.circe.generic.extras.semiauto.deriveDecoder

object Decoders {
  implicit val customConfig: Configuration = Configuration.default.withSnakeCaseMemberNames.withDefaults
  implicit val decodeRecipient: Decoder[Recipient] = deriveDecoder[Recipient]
  // QUICK REPLY
  implicit val decodeText: Decoder[Text] = deriveDecoder[Text]
  implicit val decodeOther: Decoder[Other] = deriveDecoder[Other]
  implicit val decodeQuickReply: Decoder[QuickReply] =
    Decoder[Text].map[QuickReply](identity).or(Decoder[Other].map[QuickReply](identity))
  // MESSAGE
  implicit val decodeWithText: Decoder[WithText] = deriveDecoder[WithText]
  //implicit val decodeWithAttachment: Decoder[WithAttachment] = deriveDecoder[WithAttachment]
  implicit val decodeMessage: Decoder[Message] =
    Decoder[WithText].map[Message](identity)//.or(Decoder[WithAttachment].map[Message](identity))
  // REPLY
  implicit val decodeWithMessage: Decoder[WithMessage] = deriveDecoder[WithMessage]
  implicit val decodeWithAction: Decoder[WithAction] = deriveDecoder[WithAction]
  implicit val decodeReply: Decoder[Reply] =
    Decoder[WithMessage].map[Reply](identity).or(Decoder[WithAction].map[Reply](identity))
}
