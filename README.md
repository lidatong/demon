# demon
A simple dsl for scheduling daemons

```scala
import demon.Schedule._
import demon.Daemon._

import scala.language.postfixOps

object Main {

  def main(args: Array[String]): Unit = {
    schedule (
      run { println("running daemon") } every 2 seconds
    )
  }
}
```
