package com.rhdzmota.fbmessenger.extras.model.config

import org.mongodb.scala.bson.ObjectId
import com.rhdzmota.fbmessenger.graph.model.Node
import com.rhdzmota.fbmessenger.extras.model.mongo.MongoModel

final case class FacebookConfig(
                                 _id: String,
                                 apiKeyMap: Map[String, String],
                                 replyKeyMap: Map[String, Node],
                                 serviceId: String) extends MongoModel

object FacebookConfig {
  def createId: String = new ObjectId().toString
  def apply(apiKeyMap: Map[String, String], replyKeyMap: Map[String, Node], serviceId: String): FacebookConfig = {
    FacebookConfig(createId, apiKeyMap, replyKeyMap, serviceId)
  }
}
