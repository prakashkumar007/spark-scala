package com.learning.processor

import com.learning.Context
import org.apache.spark.sql.DataFrame

class AppProcessor(readInput: () => DataFrame,
                   validate: DataFrame => Boolean,
                   writeToSource: Boolean => String)
    extends Context {

  def process(): Unit = {
    validate(readInput())
  }

}
