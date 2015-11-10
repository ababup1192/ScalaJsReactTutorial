package org.ababup1192

import japgolly.scalajs.react._
import japgolly.scalajs.react.extra.{Listenable, OnUnmount, Px, Reusability}
import japgolly.scalajs.react.vdom.prefix_<^._
import org.scalajs.dom.ext.KeyCode
import org.scalajs.dom.raw.HTMLInputElement

object CommentBox {

  case class Props private[CommentBox](model: CommentModel)

  case class State(comments: Seq[Comment])

  implicit val r = Reusability.fn[Props]((p1, p2) => p1 == p2)

  private val commentList = ReactComponentB[State]("CommentList")
    .render_P { state =>
      val comments = state.comments.map {
        case Comment(CommentId(uuid), NameWithText(name, value)) =>
          s"$name: $value"
      }.mkString("\n")

      <.pre(^.className := "commentList",
        ^.width := "90ex",
        ^.height := "20em",
        comments
      )
    }.build

  class FormBackEnd($: BackendScope[Props, Unit]) extends OnUnmount {
    val inputName = Ref[HTMLInputElement]("inputName")

    case class Callbacks(P: Props) {
      val handleNewTodoKeyDown: ReactKeyboardEventI => Option[Callback] =
        e => Some((e.nativeEvent.keyCode,
          UnfinishedComment(inputName($).get.value, e.target.value).validated)) collect {
          case (KeyCode.Enter, Some(nameWithText)) =>
            Callback(e.target.value = "") >> P.model.addComment(nameWithText)
        }
    }

    val cbs = Px.cbM($.props).map(Callbacks)

    def render(P: Props) = {
      val callbacks = cbs.value()

      <.div(
        <.input(
          ^.id := "inputName",
          ^.ref := inputName,
          ^.placeholder := "Input your name",
          ^.autoFocus := true
        ),
        <.input(
          ^.id := "inputText",
          ^.placeholder := "Input your comment",
          ^.onKeyDown ==>? callbacks.handleNewTodoKeyDown
        )
      )
    }
  }

  private val commentForm = ReactComponentB[Props]("CommentForm")
    .renderBackend[FormBackEnd]
    .build

  class CommentBoxBackEnd($: BackendScope[Props, State]) extends OnUnmount {
    def render(props: Props, state: State) = {
      <.div(^.className := "commentBox",
        <.h1("CommentBox"),
        commentList(state),
        commentForm(props)
      )
    }
  }

  val commentBox = ReactComponentB[Props]("CommentBox")
    .initialState(State(Vector.empty[Comment]))
    .renderBackend[CommentBoxBackEnd]
    .configure(Listenable.install(
      (p: Props) => p.model, $ => (comments: Seq[Comment]) =>
        $.modState(_.copy(comments = comments))))
    .build

  def apply(model: CommentModel) = {
    commentBox(Props(model))
  }
}
