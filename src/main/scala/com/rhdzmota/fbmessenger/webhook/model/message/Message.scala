package com.rhdzmota.fbmessenger.webhook.model.message

case class Message(sender: Participant, recipient: Participant, timestamp: Long, message: MessageData)
