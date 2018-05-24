package com.rhdzmota.fbmessenger.send.model.implicits

import com.rhdzmota.fbmessenger.send.model.attachment.{Attachment, Element}
import com.rhdzmota.fbmessenger.send.model.attachment.button.{Button, PostbackCall, Share, Url}
import com.rhdzmota.fbmessenger.send.model.attachment.payload._
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
  implicit val decodeWithAttachment: Encoder[WithAttachment] = deriveEncoder[WithAttachment]
  implicit val encodeMessage: Encoder[Message] = Encoder.instance {
    case withText @ WithText(_, _)             => withText.asJson
    case withAttachment @ WithAttachment(_, _) => withAttachment.asJson
  }
  // REPLY
  implicit val encodeWithMessage: Encoder[WithMessage] = deriveEncoder[WithMessage]
  implicit val encodeWithAction: Encoder[WithAction] = deriveEncoder[WithAction]
  implicit val encodeReply: Encoder[Reply] = Encoder.instance {
    case withMessage @ WithMessage(_, _, _, _) => withMessage.asJson
    case withAction @ WithAction(_, _)         => withAction.asJson
  }
  // BUTTONS
  implicit val encodeUrl: Encoder[Url] = deriveEncoder[Url]
  implicit val encodeShare: Encoder[Share] = deriveEncoder[Share]
  implicit val encodePostbackCall: Encoder[PostbackCall]= deriveEncoder[PostbackCall]
  implicit val encodeButton: Encoder[Button] = Encoder.instance {
    case url @ Url(_, _, _, _, _) => url.asJson
    case share @ Share(_) => share.asJson
    case postbackCall @ PostbackCall(_, _, _) => postbackCall.asJson
  }
  // ELEMENT
  implicit val encodeElement: Encoder[Element] = deriveEncoder[Element]
  // PAYLOAD
  implicit val encodeRichMedia: Encoder[RichMedia] = deriveEncoder[RichMedia]
  implicit val encodeGenericTemplate: Encoder[GenericTemplate] = deriveEncoder[GenericTemplate]
  implicit val encodeButtonTemplate: Encoder[ButtonTemplate] = deriveEncoder[ButtonTemplate]
  implicit val encodeListTemplate: Encoder[ListTemplate] = deriveEncoder[ListTemplate]
  implicit val encodePayload: Encoder[Payload] = Encoder.instance {
    case richMedia @ RichMedia(_) => richMedia.asJson
    case genericTemplate @ GenericTemplate(_, _, _, _) => genericTemplate.asJson
    case buttonTemplate @ ButtonTemplate(_, _, _, _) => buttonTemplate.asJson
    case listTemplate @ ListTemplate(_, _, _, _, _) => listTemplate.asJson
  }
  // ATTACHMENT
  implicit val encodeAttachment: Encoder[Attachment] = deriveEncoder[Attachment]
}
