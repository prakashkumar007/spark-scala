package com.learning.processor

import com.learning.Context
import org.apache.spark.sql.DataFrame

class AppProcessor(readInput: String => DataFrame,
                   validate: (DataFrame, String) => Boolean)
    extends Context {

  def process(tableName: String): Unit = {
    validate(readInput(tableName), tableName)
  }

}
