package org.ababup1192

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.all._
import org.scalajs.dom.document

import scala.scalajs.js


object ScalaJSMain extends js.JSApp {

  case class Comment(author: String, text: String)

  override def main(): Unit = {

    val comments = List(
      Comment("uni", "hello"),
      Comment("kani", "Yeah!"),
      Comment("uni", "Foo!")
    )

    val comment = ReactComponentB[Comment]("Comment")
      .render($ =>
        div(className := "comment",
          h2(className := "commentAuthor",
            $.props.author
          ),
          p(
            $.props.text
          )
        )
      ).build

    val commentList = ReactComponentB[List[Comment]]("CommentList")
      .render { $ =>
        val commentNodes = $.props.map { c =>
          comment(c)
        }

        div(className := "commentList",
          commentNodes
        )
      }.build

    val commentForm = ReactComponentB[Unit]("CommentForm")
      .render(_ =>
        div(className := "commentForm",
          "Hello, world! I am a CommentForm."
        )
      ).buildU

    val commentBox = ReactComponentB[Unit]("CommentBox")
      .render($ =>
        div(className := "commentBox",
          h1("Components"),
          commentList(comments),
          commentForm()
        )
      ).buildU


    ReactDOM.render(
      commentBox(), document.getElementById("example")
    )
  }
}