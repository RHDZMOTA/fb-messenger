package com.rhdzmota.fbmessenger.graph.model

import com.rhdzmota.fbmessenger.send.model.message.quickreply._

final case class Answer(quickReply: QuickReply, next: Option[Node])
