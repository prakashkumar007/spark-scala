package com.learning.processor

import com.learning.AppConfig.RawQueries
import com.learning.Context
import com.learning.exception.SchemaValidationException
import org.apache.spark.sql.{DataFrame, SaveMode}
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.StructType

object AppProcessorUtil extends Context with SchemaProcessor {

  def readSource(tableName: String): DataFrame =
    sparkSession.read
      .format("org.apache.spark.sql.cassandra")
      .options(Map("table" -> tableName, "keyspace" -> "test"))
      .schema(schemaProcessor(tableName))
      .load()

  def validate(data: DataFrame, tableName: String): Unit = {
    //sparkSession.sql(RawQueries.useSchemaQuery)
    val isContact: Boolean = sparkSession.sqlContext.tableNames
      .contains("contact")
    val maxTimeOfSource =
      data.select(max(col("created_at"))).rdd.first().getTimestamp(0)
    if (tableName.equals("contact") && isContact) {
      val extractTimeStamp =
        sparkSession.sql(RawQueries.maxTimeQuery).first().getTimestamp(0)
      writeToSource(
        data
          .filter(
            col("created_at") < extractTimeStamp && (col("target") > maxTimeOfSource)
          )
          .select(),
        SaveMode.Append
      )

    } else if (tableName.equals("contact") && !isContact) {
      writeToSource(
        data.filter(col("created_at") < maxTimeOfSource).select(),
        SaveMode.Overwrite
      )
    } else {
      writeToSource(data.select(), SaveMode.Overwrite)
    }
  }

  def writeToSource(df: DataFrame, saveMode: SaveMode): Unit = {
    addPartitionByColumn(renameAndWriteToSource(df))
      .coalesce(10)
      .write
      .mode(saveMode)
      .format("hive")
      .insertInto("hello")
  }

  private def schemaProcessor(str: String): StructType = {
    str match {
      case "contact" => contactSchema
      case _ =>
        throw SchemaValidationException(
          s"Cannot find the schema type!Schema type needs to be declared for $str"
        )
    }
  }

  private def addPartitionByColumn(df: DataFrame): DataFrame = {
    df.withColumn("firstname", year(col("firstname")))
  }

  private def renameAndWriteToSource(df: DataFrame) = {
    df.withColumn("firstname", lit("first_name"))
  }
}
