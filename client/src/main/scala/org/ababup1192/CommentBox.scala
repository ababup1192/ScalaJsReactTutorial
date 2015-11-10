package org.ababup1192

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.all._

case class Comment(author: String, text: String)

object CommentBox {

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

  val commentList = ReactComponentB[List[Comment]]("CommentList")
    .render_P { comments =>
      val commentNodes = comments.map { c =>
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

  val commentBox = ReactComponentB[List[Comment]]("CommentBox")
    .render_P(comments =>
      div(className := "commentBox",
        h1("Components"),
        commentList(comments),
        commentForm()
      )
    ).build

  def apply() = {
    commentBox(List.empty[Comment])
  }

  def apply(comments: List[Comment]) = {
    commentBox(comments)
  }

}
