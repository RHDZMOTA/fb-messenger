package com.rhdzmota.fbmessenger.send.model.message.quickreply

sealed trait QuickReply {
  def contentType: String
}

case class Text(contentType: String = "text", title: String, payload: String, imageUrl: Option[String]) extends QuickReply
case class Other(contentType: String) extends QuickReply

object QuickReply {
  val textTypeLabel  = "text"
  val phoneTypeLabel = "user_phone_number"
  val emailTypeLabel = "user_email"
  def textType(title: String, payload: String, imageUrl: Option[String] = None): Option[Text] = (title, payload) match {
    case (validTitle, validPayload) if (validTitle.length < 20) & (validPayload.length < 1000) =>
      Some(Text(textTypeLabel, validTitle, validPayload, imageUrl))
    case _ => None
  }
  def phoneType: Other = Other(phoneTypeLabel)
  def emailType: Other = Other(emailTypeLabel)
}
