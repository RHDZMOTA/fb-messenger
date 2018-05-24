package com.rhdzmota.fbmessenger.send.model.attachment

import com.rhdzmota.fbmessenger.send.model.attachment.button.Button

case class Element(title: String, subtitle: String, imageUrl: Option[String], buttons: List[Button])

object Element {
  val titleCharLimit = 80
  val subtitleCharLimit = 80
  val maxButtonsAllowed = 3

  def create(title: String, subtitle: String, imageUrl: Option[String] = None, buttons: List[Button] = List()): Option[Element] = (title, subtitle, buttons) match {
    case (validTitle, validSubtitle, validButtons) if (validTitle.length <= titleCharLimit) & (validSubtitle.length <= subtitleCharLimit) & (validButtons.length <= maxButtonsAllowed) =>
      Some(Element(validTitle, validSubtitle, imageUrl, validButtons))
    case _ => None
  }
}
