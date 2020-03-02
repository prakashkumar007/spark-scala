package com.learning.processor

import com.datastax.spark.connector._
import com.datastax.spark.connector.rdd.CassandraTableScanRDD
import com.learning.AppConfig
import org.apache.spark.SparkContext

class AppProcessor(
  appConfig: AppConfig,
  sparkContext: SparkContext,
  readInput: SparkContext => CassandraTableScanRDD[CassandraRow],
  validate: CassandraTableScanRDD[CassandraRow] => Boolean,
  writeToSource: Boolean => String
) {

  implicit val sc: SparkContext = sparkContext

  def process: Nothing = ???

}
