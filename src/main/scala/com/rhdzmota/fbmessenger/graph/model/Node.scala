package com.rhdzmota.fbmessenger.graph.model

import com.rhdzmota.fbmessenger.send.model.message._
import com.rhdzmota.fbmessenger.send.model.message.quickreply._

final case class Node(messages: List[MessageType], answers: Option[List[Answer]]) {
  def toMessageList: List[Option[Message]] =
    (messages.reverse.head.toMessage(
      answers.map(list => list.map(_.quickReply))
    ) :: messages.reverse.tail.map(_.toMessage(None))).reverse

  def getNext(payload: String): Option[Node] =
    Node.searchTree(answers, payload)
}

object Node {
  def containsPayload(quickReply: QuickReply, payload: String): Boolean = quickReply match {
    case text: Text => text.payload == payload
    case _ => false // TODO: complete
  }

  def searchDown(answer: Answer, payload: String): Option[Node] = answer match {
    case Answer(_, None) => None
    case Answer(quickReply, Some(node)) =>
      if (containsPayload(quickReply, payload)) Some(node)
      else searchTree(node.answers, payload)
  }

  def searchRight(list: List[Answer], payload: String): Option[Node] = list match {
    case Nil => None
    case answer :: rest =>
      if (containsPayload(answer.quickReply, payload)) answer.next
      else searchDown(answer, payload) match {
        case Some(node) => Some(node)
        case None => searchRight(rest, payload)
      }
  }

  def searchTree(answers: Option[List[Answer]], payload: String): Option[Node] = answers match {
    case None => None
    case Some(answerList) => answerList match {
      case Nil => None
      case answer :: rest => searchDown(answer, payload) match {
        case None => searchRight(rest, payload)
        case Some(node) => Some(node)
      }
    }
  }

}
