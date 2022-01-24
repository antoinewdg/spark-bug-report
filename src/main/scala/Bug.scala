import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.row_number

case class BlacklistEntry(id: String)
case class Player(id: String)

object Bug extends App {
  val sparkSession = SparkSession.builder().master("local[*]").getOrCreate()
  import sparkSession.implicits._

  val players = (10 to 20).map(x => Player(id = x.toString)).toDS
  val blacklist = sparkSession
    .emptyDataset[BlacklistEntry]
    .distinct()

  val result = players
    .join(blacklist, Seq("id"), "left_outer")
    .withColumn("rank", row_number().over(Window.partitionBy("id").orderBy("id")))
    .orderBy("id")
    .cache()

  result.show()
  result.explain(true)
}
