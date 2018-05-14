package com.rhdzmota.fbmessenger.webhook.model.message

import io.circe.Json
import io.circe.generic.auto._
import io.circe.syntax._

case class QuickReply(payload: String) {
  def toJson: Json = this.asJson
  override def toString: String = this.asJson.toString()
}
