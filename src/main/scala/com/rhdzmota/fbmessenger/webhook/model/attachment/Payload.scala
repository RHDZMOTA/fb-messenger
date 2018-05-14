package com.rhdzmota.fbmessenger.webhook.model.attachment


sealed trait Payload

case class Url(url: String) extends Payload
case class LocationPayload(coordinates: Coordinates) extends Payload
case class Coordinates(lat: Double, long: Double)
