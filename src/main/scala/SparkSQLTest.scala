
import org.apache.spark.sql.SparkSession

object SparkSQLTest {
  def main(args: Array[String]) {
    val sparkSession = SparkSession.builder.master("yarn").appName("spark session example").getOrCreate()
    sparkSession.catalog.listDatabases().show(false)
    sparkSession.catalog.setCurrentDatabase("default")
    sparkSession.catalog.listTables().show(false)
    sparkSession.sql("select * from core_users limit 10").show(false)

  }

}

