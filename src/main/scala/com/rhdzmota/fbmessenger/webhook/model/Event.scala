package com.rhdzmota.fbmessenger.webhook.model


case class Event(`object`: String, entry: List[Entry])

object Event {
  import com.rhdzmota.fbmessenger.webhook.model.implicits.Decoders._
  import io.circe.parser.decode  
  def fromJson(jsonString: String): Either[io.circe.Error, Event] = decode[Event](jsonString) 
}
