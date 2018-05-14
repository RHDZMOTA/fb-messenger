package com.rhdzmota.fbmessenger.webhook.model.message

import com.rhdzmota.fbmessenger.webhook.model.attachment.{Fallback, Location, SimpleAttachment}




sealed trait MessageData {
  def mid: String
}

case class Text(mid: String, text: String, quickReply: QuickReply) extends MessageData
case class WithAttachment(mid: String, seq: BigInt, attachments: List[SimpleAttachment]) extends MessageData
case class WithFallback(mid: String, text: String, attachments: List[Fallback]) extends MessageData
case class WithLocation(mid: String, seq: BigInt, attachments: List[Location]) extends MessageData
