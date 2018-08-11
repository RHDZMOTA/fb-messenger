package com.rhdzmota.fbmessenger.extras.model.config.implicits

import com.rhdzmota.fbmessenger.extras.model.config.FacebookConfig
import com.rhdzmota.fbmessenger.graph.model.implicits.Encoders._

import io.circe.Encoder
import io.circe.generic.extras.Configuration
import io.circe.generic.extras.semiauto.deriveEncoder

object Encoders {
  implicit val customConfig: Configuration = Configuration.default.withSnakeCaseMemberNames.withDefaults
  //  Facebook Config
  implicit val encodeFacebookConfig: Encoder[FacebookConfig] = deriveEncoder[FacebookConfig]
}
