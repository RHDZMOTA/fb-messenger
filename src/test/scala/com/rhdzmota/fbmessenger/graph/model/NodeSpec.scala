package com.rhdzmota.fbmessenger.graph.model

import com.rhdzmota.fbmessenger.send.model.message.quickreply.QuickReply
import org.scalatest.{FlatSpec, Matchers}
import io.circe.parser.decode
import io.circe.syntax._

class NodeSpec extends FlatSpec with Matchers {

  /*


   */
  "We" should "be able to create a simple Graph (nodes)" in {
    /*
    Simple graph definition (survey example):

                       (A)             (B)
                /       |     \
            /           |       \
         (A.A)        (A.B)   (A.C)
        /     \         |
    (A.A.A) (A.A.B)  (A.B.A)

    Where:
    - A : start survey)
    - B : quit
    - A.A : < 18
    - A.B : 18 ~ 30
    - A.C : > 30
    - A.A.A : Male
    - A.A.B : Female
    - A.B.A : Retry
     */

    val graph: Option[Node] = for {
      // main menu
      startSurveyQR <- QuickReply.textType("Start Survey",  "main menu : start survey")
      quitQR        <- QuickReply.textType("Quit", "main manu : quit")

      // main menu : survey
      // main menu : survey : age
      lessThanEighteenQR          <- QuickReply.textType("< 18", "main menu : start survey : age (< 18) ")
      betweenEighteenAndThirtyQR  <- QuickReply.textType("18 ~ 30", "main menu : start survey : age (18 ~ 30)")
      moreThanThirtyQR            <- QuickReply.textType("> 30", "main manu : start survey : age (>30)")
      // main menu : survey : age (< 18) : gender
      femaleQR  <- QuickReply.textType("Female", "main menu : start survey : age (< 18) : gender (female)")
      maleQR    <- QuickReply.textType("Male",   "main menu : start survey : age (< 18) : gender (male)")
      // main menu : survey : age (18 ~ 30) : next
      retryQR   <- QuickReply.textType("Retry", "main manu : start survey : age (18 ~ 30) : next (retry)")
      quitQR    <- QuickReply.textType("Quit",  "main menu : start survey : age (18 ~ 30) : next (quit)")
    } yield {


      def endSurveyNode: Node = Node(
        messages = List(TextType("Got it! Thanks.")),
        answers  = None
      )

      def genderQuestionNode: Node = Node(
        messages = List(TextType("Please identify your gender:")),
        answers  = Some(List(
          Answer(femaleQR, Some(endSurveyNode)),
          Answer(maleQR,   Some(endSurveyNode))
        ))
      )

      def nextOptionsNode(mainMenuNode: Option[Node]): Node = Node(
        messages = List(TextType("Great!")),
        answers  = Some(List(
          Answer(retryQR, mainMenuNode),
          Answer(quitQR, Some(endSurveyNode))
        ))
      )

      def startSurveyNode(mainMenuNode: Option[Node]): Node = Node(
        messages = List(TextType("Please select you age: ")),
        answers = Some(List(
          Answer(lessThanEighteenQR, Some(genderQuestionNode)),
          Answer(betweenEighteenAndThirtyQR, Some(nextOptionsNode(mainMenuNode))),
          Answer(moreThanThirtyQR, Some(endSurveyNode))
        ))
      )

      def mainNode(recMain: Option[Node]): Node = Node(
        messages = List(TextType("Returning to main menu!")),
        answers = Some(List(
          Answer(startSurveyQR, Some(startSurveyNode(recMain))),
          Answer(quitQR, Some(endSurveyNode))
        ))
      )
      mainNode(Some(mainNode(None)))
    }
    graph match {
      case None => fail("Graph == None")
      case Some(g) =>
    }
  }

}
