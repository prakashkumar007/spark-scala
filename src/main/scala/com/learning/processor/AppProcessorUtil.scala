package com.learning.processor

import com.datastax.spark.connector._
import com.datastax.spark.connector.rdd.CassandraTableScanRDD
import org.apache.spark.SparkContext

object AppProcessorUtil {

  def readSource(
    sparkContext: SparkContext
  ): CassandraTableScanRDD[CassandraRow] =
    sparkContext.cassandraTable("test", "contact")

  def validate(
    data: CassandraTableScanRDD[CassandraRow]
  )(implicit sparkContext: SparkContext): Boolean =
    data.tableName == "contact"

  def writeToSource(isValid: Boolean) = "Hello"

}
