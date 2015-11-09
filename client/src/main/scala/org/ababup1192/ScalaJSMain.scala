package org.ababup1192

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.all._
import org.scalajs.dom.document

import scala.scalajs.js


object ScalaJSMain extends js.JSApp {

  override def main(): Unit = {
    val componentList = ReactComponentB[Unit]("ComponentList")
      .render(_ =>
        div(className := "componentList",
          "Hello, world! I am a CommentList."
        )
      ).build

    val componentForm = ReactComponentB[Unit]("ComponentForm")
      .render(_ =>
        div(className := "componentForm",
          "Hello, world! I am a CommentForm."
        )
      ).build

    val componentBox = ReactComponentB[Unit]("ComponentBox")
      .render($ =>
        div(className := "commentBox",
          h1("Components"),
          componentList($),
          componentForm($)
        )
      ).buildU


    ReactDOM.render(
      componentBox(), document.getElementById("example")
    )
  }
}