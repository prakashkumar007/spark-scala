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
    .set("spark.sql.warehouse.dir", "user/hive/warehouse")
    .set("hive.metastore.warehouse.dir", "user/hive/warehouse")
    .set("hive.metastore.uris", "thrift://localhost:9083")
    .set("spark.sql.catalogImplementation", "hive")

  implicit val sparkSession: SparkSession =
    SparkSession
      .builder()
      .config(conf)
      .enableHiveSupport()
      .getOrCreate()

}
