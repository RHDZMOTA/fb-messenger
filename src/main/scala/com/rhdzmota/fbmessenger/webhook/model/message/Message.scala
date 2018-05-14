package com.rhdzmota.fbmessenger.webhook.model.message

case class Message(sender: Sender, recipient: Recipient, timestamp: BigInt, message: MessageData)
