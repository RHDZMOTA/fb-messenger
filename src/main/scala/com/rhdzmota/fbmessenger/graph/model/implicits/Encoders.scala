package com.rhdzmota.fbmessenger.graph.model.implicits

import com.rhdzmota.fbmessenger.graph.model._
import com.rhdzmota.fbmessenger.send.model.implicits.Encoders._

import io.circe.Encoder
import io.circe.syntax._
import io.circe.generic.extras.Configuration
import io.circe.generic.extras.semiauto.deriveEncoder

object Encoders {
  implicit val customConfig: Configuration = Configuration.default.withSnakeCaseMemberNames.withDefaults
  // Answer
  implicit val encodeAnswer: Encoder[Answer] = deriveEncoder[Answer]
  // Node
  implicit val encodeNode: Encoder[Node] = deriveEncoder[Node]
  // Message Type
  implicit val encodeTextType: Encoder[TextType] = deriveEncoder[TextType]
  implicit val encodeAttachmentType: Encoder[AttachmentType] = deriveEncoder[AttachmentType]
  implicit val encodeMessageType: Encoder[MessageType] = Encoder.instance {
    case textType @ TextType(_)             => textType.asJson
    case attachmentType @ AttachmentType(_) => attachmentType.asJson
  }
}
