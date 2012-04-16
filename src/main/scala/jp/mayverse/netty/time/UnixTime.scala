package jp.mayverse.netty.time

import java.util.Date

class UnixTime(val value: Int) {
  override def toString(): String = {
    new Date(value * 1000L).toString()
  }
}
