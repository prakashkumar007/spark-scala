package com.learning

import com.learning.processor.{AppProcessor, AppProcessorUtil}
import org.apache.spark.{SparkConf, SparkContext}

object SparkApp extends App {
  val appConfig: AppConfig = AppConfig.appConfig

  val conf = new SparkConf()
    .setMaster(appConfig.sparkConfig.masterUrl)
    .setAppName(appConfig.sparkConfig.appName)
    .set("spark-logConf", "false")
    .set("spark.cassandra.connection.host", appConfig.cassandraConfig.host)

  implicit val sc: SparkContext = new SparkContext(conf)

  val appProcessor =
    new AppProcessor(
      appConfig,
      sc,
      AppProcessorUtil.readSource,
      AppProcessorUtil.validate(AppProcessorUtil.readSource),
      AppProcessorUtil.writeToSource
    )

}
