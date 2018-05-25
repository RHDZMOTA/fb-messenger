package com.rhdzmota.fbmessenger.send.model.message

import com.rhdzmota.fbmessenger.send.model.attachment.Attachment
import com.rhdzmota.fbmessenger.send.model.message.quickreply.QuickReply

sealed trait Message {
  def quickReplies: Option[List[QuickReply]]
}

case class WithText(text: String, quickReplies: Option[List[QuickReply]]) extends Message
case class WithAttachment(attachment: Attachment, quickReplies: Option[List[QuickReply]]) extends Message

object Message {
  val textCharLimit = 2000
  def withText(text: String, quickReplies: Option[List[QuickReply]] = None): Option[WithText] =
    text match {
      case validText if text.length < textCharLimit => Some(WithText(validText, quickReplies))
      case _ => None
    }
  def withAttachment(attachment: Attachment, quickReplies: Option[List[QuickReply]] = None): Option[WithAttachment] =
    Some(WithAttachment(attachment, quickReplies))
}