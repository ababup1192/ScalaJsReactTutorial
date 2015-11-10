package org.ababup1192

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.all._
import org.scalajs.dom.document

import scala.scalajs.js


object ScalaJSMain extends js.JSApp {

  case class Comment(author: String, text: String)

  override def main(): Unit = {

    val comment = ReactComponentB[Comment]("Comment")
      .render_P(comment =>
        div(className := "comment",
          h2(className := "commentAuthor",
            comment.author
          ),
          p(
            comment.text
          )
        )
      ).build

    val commentList = ReactComponentB[Unit]("CommentList")
      .render(_ =>
        div(className := "commentList",
          comment(Comment("uni", "hello!")),
          comment(Comment("kani", "Yeah!")),
          comment(Comment("uni", "Foo!"))
        )
      ).buildU

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
          commentList(),
          commentForm()
        )
      ).buildU


    ReactDOM.render(
      commentBox(), document.getElementById("example")
    )
  }
}