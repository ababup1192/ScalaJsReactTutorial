package org.ababup1192

import japgolly.scalajs.react._
import org.scalajs.dom.document

import scala.scalajs.js

object ScalaJSMain extends js.JSApp {


  override def main(): Unit = {

    val comments = List(
      Comment("uni", "hello"),
      Comment("kani", "Yeah!"),
      Comment("uni", "Foo!")
    )

    ReactDOM.render(
      CommentBox(comments), document.getElementById("example")
    )
  }
}