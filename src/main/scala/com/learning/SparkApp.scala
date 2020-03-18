package com.learning

import com.learning.processor.{AppProcessor, AppProcessorUtil}

object SparkApp extends Context with App {

  val appProcessorUtil = AppProcessorUtil

  val appProcessor =
    new AppProcessor(appProcessorUtil.readSource, appProcessorUtil.validate)

  appProcessor.process(args(0))

}
