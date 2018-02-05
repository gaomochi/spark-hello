import org.apache.spark.streaming._
import org.apache.spark.streaming.kafka._
import org.apache.spark.SparkConf
object Main {
  def main(args: Array[String]) {
    val zkQuorum = "127.0.0.1:2181"
    val group = "test-consumer-group"
    val topics = "test_spark"
    val numThreads = 2
    val sparkConf = new SparkConf().setAppName("KafkaWordCount").setMaster("local[2]")
    val ssc =  new StreamingContext(sparkConf, Seconds(10))

    val topicpMap = topics.split(",").map((_,numThreads.toInt)).toMap
    val lines = KafkaUtils.createStream(ssc, zkQuorum, group, topicpMap).map(_._2)
    val words = lines.flatMap(_.split(" "))
    val pairs = words.map(word => (word, 1))
    val wordCounts = pairs.reduceByKey(_ + _)
    wordCounts.print()
    System.out.println(123123)
    ssc.start()
    ssc.awaitTermination()
  }
}

