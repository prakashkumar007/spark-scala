package com.learning.processor

import com.datastax.spark.connector._
import com.datastax.spark.connector.rdd.CassandraTableScanRDD
import com.learning.AppConfig
import com.learning.exception.TableValidationException
import org.apache.spark.SparkContext

class AppProcessor(appConfig: AppConfig,
                   sparkContext: SparkContext,
                   readInput: Unit => CassandraTableScanRDD[CassandraRow],
                   validate: CassandraTableScanRDD[CassandraRow] => Boolean,
                   writeToSource: Boolean => String) {

  implicit val sc = sparkContext

  def readInput: CassandraTableScanRDD[CassandraRow] =
    AppProcessorUtil.readSource

  def validateInput(data: CassandraTableScanRDD[CassandraRow]): Boolean = {
    if (validate(data)) writeToSource
    else TableValidationException("Table Name Error")
  }

  def writeToSource: String

}
