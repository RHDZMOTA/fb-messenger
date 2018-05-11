package com.rhdzmota.fbmessenger.webhook.model.attachment

import io.circe.Json
import io.circe.generic.auto._
import io.circe.syntax._

sealed trait Payload {
  def toJson: Json = this.asJson
  override def toString: String = this.asJson.toString()
}


case class Url(url: String) extends Payload
case class LocationPayload(coordinates: Coordinates) extends Payload

case class Coordinates(lat: Double, long: Double)
