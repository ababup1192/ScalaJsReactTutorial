package org.ababup1192

import java.util.UUID

case class CommentId(id: UUID)


object CommentId {
  def random = CommentId(UUID.randomUUID)
}

case class NameWithText(name: String, value: String)

case class Comment(id: CommentId, nameWithText: NameWithText)

case class UnfinishedComment(name: String, value: String) {
  private def validatedStr(str: String): Option[String] = if (str.trim.isEmpty) None else Some(str.trim)

  def validated: Option[NameWithText] =
    (validatedStr(name), validatedStr(value)) match {
      case (Some(n), Some(v)) => Some(NameWithText(n, v))
      case _ => None
    }

}

