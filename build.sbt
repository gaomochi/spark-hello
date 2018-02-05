name := "hello-tantan"

version := "1.0"

scalaVersion := "2.11.8"



libraryDependencies ++= {
  val sparkV = "2.2.1"
  Seq(
    "org.apache.spark"            %%  "spark-sql"                   % sparkV withSources() withJavadoc(),
    "org.apache.spark"            %%  "spark-core"                   % sparkV withSources() withJavadoc(),
    "org.apache.spark"            %%  "spark-streaming"   % sparkV withSources() withJavadoc(),
    "org.apache.spark"            %%  "spark-streaming-kafka-0-8"   % sparkV withSources() withJavadoc()

  )
}

mergeStrategy in assembly := {
  case m if m.toLowerCase.endsWith("manifest.mf")          => MergeStrategy.discard
  case m if m.toLowerCase.matches("meta-inf.*\\.sf$")      => MergeStrategy.discard
  case "log4j.properties"                                  => MergeStrategy.discard
  case m if m.toLowerCase.startsWith("meta-inf/services/") => MergeStrategy.filterDistinctLines
  case "reference.conf"                                    => MergeStrategy.concat
  case _                                                   => MergeStrategy.first
}