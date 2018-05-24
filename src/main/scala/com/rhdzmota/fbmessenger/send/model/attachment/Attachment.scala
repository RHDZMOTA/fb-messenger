package com.rhdzmota.fbmessenger.send.model.attachment

import com.rhdzmota.fbmessenger.send.model.attachment.payload.Payload

case class Attachment(`type`: String, payload: Payload)

object Attachment {

  val validTypes = List("image", "audio", "video", "file", "template")

  def create(`type`: String, payload: Payload): Option[Attachment] = `type` match {
    case validType if validTypes contains validType => Some(Attachment(validType, payload))
    case _ => None
  }
}
