package com.learning.processor

import com.learning.Context
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._

object AppProcessorUtil extends Context {

  def readSource(): DataFrame =
    sparkSession.read
      .format("org.apache.spark.sql.cassandra")
      .options(Map("table" -> "contact", "keyspace" -> "test"))
      .load()

  def validate(data: DataFrame): Boolean = {
    data.show()
    "data.tableName" == "contact"
  }

  def writeToSource(isValid: Boolean) = "Hello"

}
