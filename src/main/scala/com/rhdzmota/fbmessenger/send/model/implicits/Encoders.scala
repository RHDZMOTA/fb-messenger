package com.rhdzmota.fbmessenger.send.model.implicits

import com.rhdzmota.fbmessenger.send.model.reply._
import com.rhdzmota.fbmessenger.send.model.message._
import com.rhdzmota.fbmessenger.send.model.message.quickreply._

import io.circe.Encoder
import io.circe.syntax._
import io.circe.generic.extras.Configuration
import io.circe.generic.extras.semiauto.deriveEncoder

object Encoders {
  implicit val customConfig: Configuration = Configuration.default.withSnakeCaseMemberNames.withDefaults
  implicit val encodeRecipient: Encoder[Recipient] = deriveEncoder[Recipient]
  // QUICK REPLY
  implicit val encodeText: Encoder[Text] = deriveEncoder[Text]
  implicit val encodeOther: Encoder[Other] = deriveEncoder[Other]
  implicit val encodeQuickReply: Encoder[QuickReply] = Encoder.instance {
    case text @ Text(_, _, _, _) => text.asJson
    case other @ Other(_) => other.asJson
  }
  // MESSAGE
  implicit val encodeWithText: Encoder[WithText] = deriveEncoder[WithText]
  //implicit val decodeWithAttachment: Encoder[WithAttachment] = deriveEncoder[WithAttachment]
  implicit val encodeMessage: Encoder[Message] = Encoder.instance {
    case withText @ WithText(_, _)             => withText.asJson
    case _ => "{}".asJson //withAttachment @ WithAttachment(_, _) => "{}".asJson
  }
  // REPLY
  implicit val encodeWithMessage: Encoder[WithMessage] = deriveEncoder[WithMessage]
  implicit val encodeWithAction: Encoder[WithAction] = deriveEncoder[WithAction]
  implicit val encodeReply: Encoder[Reply] = Encoder.instance {
    case withMessage @ WithMessage(_, _, _, _) => withMessage.asJson
    case withAction @ WithAction(_, _)         => withAction.asJson
  }
}
