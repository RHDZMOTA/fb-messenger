# FB-Messenger Scala Models

Serializable scala models for the Webhook and Send API of the Facebook Messenger Platform. 
This repo follows the [SemVer Specification](https://semver.org/). 

* Current version: **1.0.0**

## Usage

The original objective was to provide the models for the webhook and send APIs to facilitate the development
of chatbots in the facebook messenger platform. 

A chatbot project written in Scala might look something like this:
* A server validated by facebook to receive webhook events (e.g. [Akka HTTP](https://doc.akka.io/docs/akka-http/current/)).
* Usage of `io.circe.decode` and the webhook models to interpret the webhook events.
* Pattern matching to identify message types (text, attachments, fallback, etc).
* Process the messages (e.g. ML, expert model, business logic).
* Build the response using the Send models.
* Send the response using `io.circe.syntax` for json encoding and a client http to send the post request. 

A more advanced implementation involves streaming technologies, natural language processing, reinforced learning 
and complex business logic.  



## Webhook API

The webhook API is needed when a message is send to a facebook page. 
You may receive text messages, attachments or fallback. 
For a precise definition of these elements see the [messenger platform official documentation for webhook events](https://developers.facebook.com/docs/messenger-platform/reference/webhook-events/messages).

Usage example:
```scala
import io.circe.parser.decode

import com.rhdzmota.fbmessenger.webhook.model.Event
import com.rhdzmota.fbmessenger.webhook.model.implicits.Decoders._

// This is a facebook callback (payload a post request from fb representing an event)
val eventJsonString: String = ??? 

// We can use our implicit decoders with circe to get the event object. 
val result = decode[Event](eventJsonString)

result match {
  case Left(error)  => println("There was an error decoding the eventJsonString: " + error.toString)
  case Right(event) => println("We have an event.")
}

```

Event json example:
```
{
  "object":"page",
  "entry":[
    {
      "id":"<PAGE_ID>",
      "time":0,
      "messaging":[
        {
          "sender":{
            "id":"<PSID>"
          },
          "recipient":{
            "id":"<PAGE_ID>"
          },
          "timestamp":0,
          "message":{
            "mid":"<MID>",
            "seq":0,
            "attachments":[
              {
                "title":"<TITLE>",
                "url":"<URL>",
                "type":"location",
                "payload":{
                  "coordinates":{
                    "lat":0,
                    "long":0
                  }
                }
              }
            ]
          }
        }
      ]
    }
  ]
}
```

## Send API

The send API is needed in order to send a message from a facebook page to a user. For a complete decription of the API see the [messenger platform official documentation to send requests](https://developers.facebook.com/docs/messenger-platform/reference/send-api/). 

Usage example:
```scala
import io.circe.syntax._

import com.rhdzmota.fbmessenger.send.model.reply._
import com.rhdzmota.fbmessenger.send.model.message._
import com.rhdzmota.fbmessenger.send.model.message.quickreply._
import com.rhdzmota.fbmessenger.send.model.implicits.Encoders._

val recipient = Recipient("<PSID>") // Message recipient (user)
val quickReply = Text("text", "<TITLE>", "<PAYLOAD>", None) // Quick reply buttons (create none or many)
val withText = WithText("<TEXT-1>", Some(List(quickReply))) // Create text message with quick reply list (a message can be withText or withAttachment)
val withMessage = Reply.withDefaultConfigMessage(recipient, withText) // Define reply element (a reply can be done with a message or with an action)

// You can use the implicit encoders and circe syntax to serialize to json
withMessage.asJson
```

Send request message example:
```
{
  "messaging_type": "RESPONSE",
  "recipient": {
    "id": "<PSID>"
  },
  "message": {
    "text": "<TEXT-1>",
    "quick_replies": [
      {
        "content_type": "text",
        "title": "<TITLE>",
        "payload": "<PAYLOAD>"
      }
    ]
  },
  "notification_type": "REGULAR"
} 
```

## Post request to fb-messenger 

This repository only contains the models to interact with the facebook messenger API. If you are using the Send API models, you will need to figure out a way of sending the 
request to fb messenger. In this section we present a suggestion for sending the requests using akka http async client library.

Dependencies
* Akka HTTP

Create the required implicit variables for the Actor Model.
```scala
import akka.actor.ActorSystem
import akka.stream.{ActorMaterializer, Materializer}

import scala.concurrent.ExecutionContext

trait Context {
  implicit val actorSystem: ActorSystem = ActorSystem()
  implicit val executionContext = actorSystem.dispatcher
  implicit val materializer: Materializer = ActorMaterializer()
}
``` 

Create a Client Http object or trait with the post method.
```scala
object ClientHttp extends Context{
  def postRequest(targetUrl: String)(data: String): Future[HttpResponse] =
    Http(actorSystem).singleRequest(
      HttpRequest(
        HttpMethods.POST,
        targetUrl,
        entity = HttpEntity(ContentTypes.`application/json`, data)
      ))
}
```

Now we can send a request to the Facebook Messenger.
```scala
def send(data: String, apiKey: String): Unit =
  ClientHttp.postRequest(Settings.Facebook.sendUri + apiKey)(data).onComplete {
    case Failure(e)        => println(s"- Failed to post:\n$data\n- Reason:\n$e")
    case Success(response) => println(s"- Server responded with:\n$response")
  }
```

## Contributions and authors

Feel free to create a PR or raise an issue. For more information, contact the developers of this repo:

* [Rodrigo Hernández Mota](https://www.linkedin.com/in/rhdzmota/)

## License

To be defined. 
