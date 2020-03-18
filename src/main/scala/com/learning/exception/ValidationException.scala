package com.learning.exception

trait ValidationException extends Exception {
  val message: String
}

case class TableValidationException(msg: String) extends ValidationException {
  override val message: String = msg
}

case class SchemaValidationException(msg: String) extends ValidationException {
  override val message: String = msg
}
