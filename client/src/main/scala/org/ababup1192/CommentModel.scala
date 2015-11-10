package org.ababup1192

import japgolly.scalajs.react._
import japgolly.scalajs.react.extra.Broadcaster

class CommentModel extends Broadcaster[Seq[Comment]] {

  private object State {
    var comments = Seq.empty[Comment]

    def mod(f: Seq[Comment] => Seq[Comment]): Callback = {
      val newComments = f(comments)

      Callback(comments = newComments) >>
        broadcast(newComments)
    }

    def modOne(Id: CommentId)(f: Comment => Comment): Callback =
      mod(_.map {
        case existing@Comment(Id, _) => f(existing)
        case other => other
      })
  }

  def addComment(nameWithText: NameWithText): Callback =
    State.mod(_ :+ Comment(CommentId.random, nameWithText))


}
