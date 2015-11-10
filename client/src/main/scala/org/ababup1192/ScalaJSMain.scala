package org.ababup1192

import japgolly.scalajs.react._
import org.scalajs.dom.document

import scala.scalajs.js

object ScalaJSMain extends js.JSApp {


  override def main(): Unit = {

    val model = new CommentModel()

    ReactDOM.render(
      CommentBox(model), document.getElementById("example")
    )
  }
}