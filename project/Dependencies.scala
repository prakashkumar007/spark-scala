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
  val sparkTest = "com.holdenkarau" %% "spark-testing-base" % "2.4.5_0.14.0" % "test"
  val scalaTest = "org.scalatest" %% "scalatest" % "3.1.1" % "test"

  /**
    * The following variables is used to expose in build.sbt
    */
  val backendDependencies =
    Seq(sparkSql, sparkCassandraConnector, pureConfig, sparkHive)

  val testDependencies = Seq(sparkTest, scalaTest)
}
