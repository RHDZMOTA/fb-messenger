package com.rhdzmota.fbmessenger.send.model.attachment.button

sealed trait Button

case class Url(`type`: String, title: String, url: String, webviewHeightRatio: String, fallbackUrl: String) extends Button
case class Share(`type`: String) extends Button
case class PostbackCall(`type`: String, title: String, payload: String) extends Button

object Button {
  val compactLabel = "compact"
  val tallLabel = "tall"
  val fullLabel = "full"
  val titleCharLimit = 20
  val payloadCharLimit = 1000
  val webviewValidRatios = List(compactLabel, tallLabel, fullLabel)

  def url(title: String, url: String, fallbackUrl: String, webviewHeightRatio: String = fullLabel): Option[Url] = (title, webviewHeightRatio) match {
    case (validTitle, validRatio) if (validTitle.length <= titleCharLimit) & (webviewValidRatios contains validRatio) =>
      Some(Url("web_url", validTitle, url, validRatio, fallbackUrl))
    case _ => None
  }

  def postback(title: String, payload: String): Option[PostbackCall] = (title, payload) match {
    case (validTitle, validPayload) if (validTitle.length <= titleCharLimit) & (validPayload.length <= payloadCharLimit) => Some(PostbackCall("postback", validTitle, payload))
    case _ => None
  }

  def share: Option[Share] = Some(Share("element_share"))

  def call(title: String, payload: String): Option[PostbackCall] = (title, payload) match {
    case (validTitle, validPayload) if (validTitle.length <= titleCharLimit) & validPayload.startsWith("+") & (validPayload.tail forall Character.isDigit) =>
      Some(PostbackCall("phone_number", validTitle, validPayload))
    case _ => None
  }
}

