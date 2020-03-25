package com.learning.processor

import com.learning.AppConfig.RawQueries
import com.learning.Context
import org.apache.spark.sql.{DataFrame, SaveMode}

class ProcessRequest extends Context with SchemaProcessor {

  def readSource(): DataFrame =
    sparkSession.read
      .format("org.apache.spark.sql.cassandra")
      .options(Map("table" -> "tableName", "keyspace" -> "test"))
      .load()

  def validate(data: DataFrame): Unit = {
    writeToSource(data.sqlContext.sql(RawQueries.maxTimeQuery), SaveMode.Append)
  }

  def writeToSource(df: DataFrame, saveMode: SaveMode): Unit = {
    df.write
      .mode(saveMode)
      .format("hive")
      .saveAsTable("hello")
  }

}
