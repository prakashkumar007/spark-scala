package com.learning

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

trait Context {
  val appConfig: AppConfig = AppConfig.appConfig

  val conf: SparkConf = new SparkConf()
    .setMaster(appConfig.sparkConfig.masterUrl)
    .setAppName(appConfig.sparkConfig.appName)
    .set("spark-logConf", "false")
    .set("spark.cassandra.connection.host", appConfig.cassandraConfig.host)

  implicit val sparkSession: SparkSession =
    SparkSession.builder().config(conf).getOrCreate()

}
