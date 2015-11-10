package org.ababup1192

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.all._
import org.scalajs.dom.document

import scala.scalajs.js


object ScalaJSMain extends js.JSApp {

  override def main(): Unit = {
    val commentList = ReactComponentB[Unit]("CommentList")
      .render(_ =>
        div(className := "commentList",
          "Hello, world! I am a CommentList."
        )
      ).build

    val commentForm = ReactComponentB[Unit]("CommentForm")
      .render(_ =>
        div(className := "commentForm",
          "Hello, world! I am a CommentForm."
        )
      ).build

    val commentBox = ReactComponentB[Unit]("CommentBox")
      .render($ =>
        div(className := "commentBox",
          h1("Components"),
          commentList($),
          commentForm($)
        )
      ).buildU


    ReactDOM.render(
      commentBox(), document.getElementById("example")
    )
  }
}