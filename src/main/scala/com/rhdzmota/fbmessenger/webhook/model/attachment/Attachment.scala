package com.rhdzmota.fbmessenger.webhook.model.attachment

import io.circe.Json
import io.circe.generic.auto._
import io.circe.syntax._

sealed trait Attachment {
  def `type`: String
  def payload: Option[Payload]
  def toJson: Json = this.asJson
  override def toString: String = this.asJson.toString()
}

case class SimpleAttachment(`type`: String, payload: Option[Url]) extends Attachment

case class Fallback(`type`: String, payload: Option[Payload], title: String, URL: String) extends Attachment

case class Location(title: String, url: String, `type`: String, payload: Option[LocationPayload]) extends Attachment
