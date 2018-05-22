package com.rhdzmota.fbmessenger.webhook.model

import com.rhdzmota.fbmessenger.webhook.model.message.Message

case class Entry(id: String, time: Long, messaging: List[Message])
