package com.rhdzmota.fbmessenger.webhook.model.message

case class Message(sender: Participant, recipient: Participant, timestamp: BigInt, message: MessageData)
