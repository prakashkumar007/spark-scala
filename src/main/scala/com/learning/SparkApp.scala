package com.learning

import com.learning.processor.{AppProcessor, ProcessRequest}

object SparkApp extends Context with App {

  val appProcessorUtil = new ProcessRequest

  val appProcessor =
    new AppProcessor(appProcessorUtil.readSource, appProcessorUtil.validate)

  appProcessor.process()

}
