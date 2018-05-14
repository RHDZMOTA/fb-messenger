package com.rhdzmota.fbmessenger.webhook.model.attachment


sealed trait Attachment {
  def `type`: String
  def payload: Option[Payload]
}

case class SimpleAttachment(`type`: String, payload: Option[Url]) extends Attachment
case class Fallback(`type`: String, payload: Option[Payload], title: String, URL: String) extends Attachment
case class Location(title: String, url: String, `type`: String, payload: Option[LocationPayload]) extends Attachment
