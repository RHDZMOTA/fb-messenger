package com.rhdzmota.fbmessenger.extras.model.config.implicits

import com.rhdzmota.fbmessenger.extras.model.config.FacebookConfig
import com.rhdzmota.fbmessenger.graph.model.implicits.Decoders._

import io.circe.Decoder
import io.circe.generic.extras.Configuration
import io.circe.generic.extras.semiauto.deriveDecoder

object Decoders {
  implicit val customConfig: Configuration = Configuration.default.withSnakeCaseMemberNames.withDefaults
  // FacebookConfig
  implicit val decodeFacebookConfig: Decoder[FacebookConfig] = deriveDecoder[FacebookConfig]
}
