package com.learning.processor

import org.apache.spark.sql.types._

trait SchemaProcessor {

  lazy val contactSchema: StructType = StructType(
    Array(
      StructField("firstname", StringType),
      StructField("lastname", StringType),
      StructField("state", StringType),
      StructField("age", IntegerType),
      StructField("created_at", TimestampType, nullable = false)
    )
  )
}
