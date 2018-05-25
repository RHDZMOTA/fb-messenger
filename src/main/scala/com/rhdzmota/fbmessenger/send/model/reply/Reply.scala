package com.rhdzmota.fbmessenger.send.model.reply

import com.rhdzmota.fbmessenger.send.model.message._
import com.rhdzmota.fbmessenger.send.model.message.quickreply.QuickReply


sealed trait Reply {
  def recipient: Recipient
}

case class WithMessage(messagingType: String, recipient: Recipient, message: Message, notificationType: String) extends Reply
case class WithAction(recipient: Recipient, senderAction: String) extends Reply

object Reply {

  val responseLabel = "RESPONSE"
  val updateLabel = "UPDATE"
  val messageTagLabel = "MESSAGE_TAG"
  val typingOnLabel = "typing_on"
  val typingOffLabel = "typing_off"
  val markSeenLabel = "mark_seen"
  val regularLabel = "REGULAR"
  val silentPushLabel = "SILENT_PUSH"
  val noPushLabel = "NO_PUSH"
  val validMessageTypes = List(responseLabel, updateLabel, messageTagLabel)
  val validSenderActions = List(typingOnLabel, typingOffLabel, markSeenLabel)
  val validNotificationTypes = List(responseLabel, silentPushLabel, noPushLabel)

  def withMessage(recipient: Recipient, message: Message, messageType: String = responseLabel, notificationType: String = regularLabel): Option[WithMessage] =
    (messageType, notificationType) match {
      case (validMessageType, validNotificationType) if (validMessageTypes contains validMessageType) & (validNotificationTypes contains validNotificationType) =>
        Some(WithMessage(validMessageType, recipient, message, validNotificationType))
      case _ => None
    }

  def withTextMessage(recipient: Recipient, text: String, quickReplies: Option[List[QuickReply]] = None,
                      messageType: String = responseLabel, notificationType: String = regularLabel): Option[WithMessage] = for {
    withText        <- Message.withText(text, quickReplies)
    withTextMessage <- Reply.withMessage(recipient, withText, messageType, notificationType)
  } yield withTextMessage

  def withAction(recipient: Recipient, senderAction: String): Option[WithAction] = senderAction match {
    case validSenderAction if validSenderActions contains validSenderAction => Some(WithAction(recipient, validSenderAction))
    case _ => None
  }

  def withTypingOn(recipient: Recipient): WithAction = WithAction(recipient, typingOnLabel)
  def withTypingOff(recipient: Recipient): WithAction = WithAction(recipient, typingOffLabel)
  def withMarkSeen(recipient: Recipient): WithAction = WithAction(recipient, markSeenLabel)

  def withDefaultConfigMessage(recipient: Recipient, message: Message): WithMessage =
    WithMessage(responseLabel, recipient, message, regularLabel)

}