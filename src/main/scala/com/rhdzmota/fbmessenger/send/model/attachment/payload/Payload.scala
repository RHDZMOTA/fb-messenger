package com.rhdzmota.fbmessenger.send.model.attachment.payload

import com.rhdzmota.fbmessenger.send.model.attachment._
import com.rhdzmota.fbmessenger.send.model.attachment.button.Button


sealed trait Payload

case class RichMedia(url: String) extends Payload
case class GenericTemplate(templateType: String, sharable: Boolean, imageApectRatio: String, elements: List[Element]) extends Payload
case class ButtonTemplate(templateType: String, text: String, buttons: List[Button], sharable: Boolean) extends Payload
case class ListTemplate(templateType: String, topElementStyle: String, buttons: List[Button], elements: List[Element], sharable: Boolean) extends Payload

object Payload {

  val topElementCompactStyleLabel = "compact"
  val topElementLargeStyleLabel = "large"
  val maxElementsAllowedGeneric = 10
  val maxButtonsAllowedButton = 4
  val maxButtonsAllowedList = 1
  val minElementsAllowed = 2
  val maxElementsAllowed = 4
  val textCharLimit = 640

  val topElementValidStyleLabels = List(topElementCompactStyleLabel, topElementLargeStyleLabel)

  object AspectRatio {
    val horizontal = "1.91:1"
    val square = "1:1"
    val validRatios = List(horizontal, square)
  }

  def genericTemplate(sharable: Boolean, elements: List[Element], imageAspectRatio: String = AspectRatio.horizontal): Option[GenericTemplate] =
    (elements.length, imageAspectRatio) match {
      case (validLength, validRatio) if (validLength <= maxElementsAllowedGeneric) & (AspectRatio.validRatios contains validRatio) =>
        Some(GenericTemplate("generic", sharable, validRatio, elements))
      case _ => None
    }

  def buttonTemplate(text: String, buttons: List[Button], sharable: Boolean = false): Option[ButtonTemplate] = (text, buttons) match {
    case (validText, validButtons) if (validText.length <= textCharLimit) & (validButtons.length <= maxButtonsAllowedButton) =>
      Some(ButtonTemplate("button", validText, validButtons, sharable))
    case _ => None
  }

  def listTemplate(elements: List[Element], buttons: List[Button] = List(), sharable: Boolean = false,
                   topElementStyle: String = topElementCompactStyleLabel): Option[ListTemplate] =

    (elements, buttons, topElementStyle) match {
      case (validElements, validButtons, validStyle) if
      (validElements.length >= minElementsAllowed) &
        (validElements.length <= maxElementsAllowed) &
        (validButtons.length <= maxButtonsAllowedList) &
        (topElementValidStyleLabels contains validStyle) =>
        Some(ListTemplate("list", validStyle, validButtons, validElements, sharable))
      case _ => None
    }

  def richMedia(url: String): Option[RichMedia] = Some(RichMedia(url))
}

