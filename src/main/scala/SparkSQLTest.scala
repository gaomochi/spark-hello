
import org.apache.spark.sql.SparkSession
import java.io.File

object SparkSQLTest {
  def main(args: Array[String]) {
    val warehouseLocation = new File("spark-warehouse").getAbsolutePath

    val sparkSession = SparkSession
      .builder()
      .appName("Spark Hive Example")
      .config("spark.sql.warehouse.dir", warehouseLocation)
      .enableHiveSupport()
      .getOrCreate()
    sparkSession.catalog.listDatabases().show(false)
    sparkSession.catalog.setCurrentDatabase("default")
    sparkSession.catalog.listTables().show(false)
    sparkSession.sql("select * from core_users limit 10").show(false)

  }

}

