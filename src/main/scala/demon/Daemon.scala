package demon

import java.time.Duration

class Daemon(f: => Unit) extends EveryStep with TimeUnitStep with DaemonStep {
  private var _length = 0
  private var _duration = Duration.ZERO

  override def every(length: Int): TimeUnitStep = {
    _length = length
    this
  }

  override def every(duration: Duration): Daemon = {
    _duration = duration
    this
  }

  override def hours: Daemon = setDuration(Duration.ofHours)
  override def minutes: Daemon = setDuration(Duration.ofMinutes)
  override def seconds: Daemon = setDuration(Duration.ofSeconds)

  private def setDuration(f: Long => Duration): Daemon = {
    _duration = f(_length.toLong)
    this
  }

  override def eval(): Unit = f
  override def durationInMillis: Long = _duration.toMillis
}

trait EveryStep {
  def every(duration: Int): TimeUnitStep
  def every(duration: Duration): Daemon
}

trait TimeUnitStep {
  def hours: Daemon
  def minutes: Daemon
  def seconds: Daemon
}

trait DaemonStep {
  def eval(): Unit
  def durationInMillis: Long
}

object Daemon {
  def run(f: => Unit): EveryStep = new Daemon(f)
}
