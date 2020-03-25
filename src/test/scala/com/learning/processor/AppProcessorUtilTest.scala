package com.learning.processor

import com.holdenkarau.spark.testing.{
  DataFrameSuiteBase,
  DataframeGenerator,
  SharedSparkContext
}
import org.apache.spark.sql.DataFrame
import org.scalatest.BeforeAndAfterAll
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class AppProcessorUtilTest
    extends AnyFlatSpec
    with SharedSparkContext
    with DataFrameSuiteBase
    with SchemaProcessor
    with Matchers
    with BeforeAndAfterAll {

  override implicit def reuseContextIfPossible: Boolean = true

  protected val appProcessorUtil: AppUtility = new AppUtility

  def beforeAll(configMap: Map[String, Any]): DataFrame =
    sqlContext.sql("create table hello as select * from hello")

  it should "Counts based on various ranges" in {
    testCounts
  }

  private def testCounts = {
    spark.sql(
      "CREATE DATABASE IF NOT EXISTS test_db LOCATION '/tmp/test_db.db'"
    )
  }

  it should "append the table when the table name is contact" in {

    val df: DataFrame = DataframeGenerator
      .arbitraryDataFrame(sqlContext, contactSchema)
      .arbitrary
      .sample
      .get

    df.show()

    appProcessorUtil.validate(df, "contact")
  }
}
