package com

import pureconfig._
import pureconfig.error.ConfigReaderFailures
import pureconfig.generic.auto._

package object learning {

  case class SparkConfig(masterUrl: String, appName: String)

  case class CassandraConfig(host: String)

  case class AppConfig(sparkConfig: SparkConfig,
                       cassandraConfig: CassandraConfig)

  object AppConfig {
    val appConfig = ConfigSource.default
      .load[AppConfig] match {
      case Right(conf) => conf
      case Left(failures) =>
        throw new Exception(
          s"Unable to load the configuration ${failures.toList.mkString("\n")}"
        )
    }

  }

}
