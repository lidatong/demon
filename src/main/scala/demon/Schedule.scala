package demon

class Schedule(daemons: List[Daemon]) {
  def run(): Unit = while (true) {
    daemons.foreach(daemon => {
      Thread.sleep(daemon.durationInMillis) // yea ik this is wrong for > 1
      daemon.eval()
    })
  }
}

object Schedule {
  def schedule(daemons: Daemon*): Unit = new Schedule(daemons.toList).run()
}

