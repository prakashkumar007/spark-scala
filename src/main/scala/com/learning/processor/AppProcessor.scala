package com.learning.processor

import com.learning.Context
import org.apache.spark.sql.DataFrame

class AppProcessor(readInput: () => DataFrame, validate: DataFrame => Unit)
    extends Context {

  def process(): Unit = {
    validate(readInput())
  }

}
