package com.rhdzmota.fbmessenger.send.model.message

import com.rhdzmota.fbmessenger.send.model.attachment.Attachment
import com.rhdzmota.fbmessenger.send.model.message.quickreply.QuickReply

sealed trait Message {
  def quickReplies: List[QuickReply]
}

case class WithText(text: String, quickReplies: List[QuickReply] = List[QuickReply]()) extends Message
case class WithAttachment(attachment: Attachment, quickReplies: List[QuickReply] = List[QuickReply]()) extends Message
