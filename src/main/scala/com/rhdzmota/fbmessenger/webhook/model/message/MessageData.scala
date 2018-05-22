package com.rhdzmota.fbmessenger.webhook.model.message

import com.rhdzmota.fbmessenger.webhook.model.attachment.{Fallback, Location, SimpleAttachment}

sealed trait MessageData {
  def mid: String
}

case class Text(mid: String, text: String, quickReply: Option[QuickReply]) extends MessageData
case class WithAttachment(mid: String, seq: Int, attachments: List[SimpleAttachment]) extends MessageData
case class WithFallback(mid: String, text: String, attachments: List[Fallback]) extends MessageData
case class WithLocation(mid: String, seq: Int, attachments: List[Location]) extends MessageData
