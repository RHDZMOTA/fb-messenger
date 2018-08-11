package com.rhdzmota.fbmessenger.graph.model

import com.rhdzmota.fbmessenger.send.model.message._
import com.rhdzmota.fbmessenger.send.model.attachment._
import com.rhdzmota.fbmessenger.send.model.message.quickreply._

sealed trait MessageType {
  def toMessage(quickReplies: Option[List[QuickReply]]): Option[Message]
}

final case class TextType(content: String) extends MessageType {
  override def toMessage(quickReplies: Option[List[QuickReply]]): Option[Message] =
    Message.withText(content, quickReplies)
}

final case class AttachmentType(content: Attachment) extends MessageType {
  override def toMessage(quickReplies: Option[List[QuickReply]]): Option[Message] =
    Message.withAttachment(content, quickReplies)
}
