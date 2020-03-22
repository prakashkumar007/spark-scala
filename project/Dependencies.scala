import sbt._

object Dependencies {

  /**
    * All project dependencies are tracked here
    */
  lazy val sparkVersion = "2.4.5"

  /**
    * Project Dependencies
    */
  val sparkSql = "org.apache.spark" %% "spark-sql" % sparkVersion
  val sparkCassandraConnector = "com.datastax.spark" %% "spark-cassandra-connector" % "2.4.2"
  val pureConfig = "com.github.pureconfig" %% "pureconfig" % "0.12.3"
  val sparkHive = "org.apache.spark" %% "spark-hive" % sparkVersion

  /**
    * The following variables is used to expose in build.sbt
    */
  val backendDependencies =
    Seq(sparkSql, sparkCassandraConnector, pureConfig, sparkHive)
}
