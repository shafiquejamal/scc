name := """sj-sortable-coding-challenge"""

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
  "org.apache.spark" %% "spark-core" % "2.0.0",
  "org.apache.spark" %% "spark-sql" % "2.0.0",
  "com.databricks" %% "spark-csv" % "1.4.0",
  "log4j" % "log4j" % "1.2.17",
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.7.2",
  "com.typesafe.play" %% "play-json" % "2.5.4",
  "org.scalatest" %% "scalatest" % "2.2.6" % Test,
  "joda-time" % "joda-time" % "2.9.4")

javaOptions in (Test, run) ++= Seq("-Dspark.master=local",
  "-Dlog4j.debug=true",
  "-Dlog4j.configuration=log4j.properties")

outputStrategy := Some(StdoutOutput)

fork := true

coverageEnabled in Test:= true


