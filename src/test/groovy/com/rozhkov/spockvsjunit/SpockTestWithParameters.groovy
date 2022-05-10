package com.rozhkov.spockvsjunit

import com.rozhkov.spockvsjunit.service.TestService
import com.rozhkov.spockvsjunit.utils.Pair
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import java.time.DayOfWeek
import java.util.stream.Stream

import static java.util.stream.Collectors.toList

class SpockTestWithParameters extends Specification {
  @Subject
  TestService sut = new TestService()

  @Unroll
  def test1() {
    expect:
    Math.abs(param) >= 0

    where:
    param << [-1, 0, 1]
  }

  @Unroll
  def test2() {
    expect:
    sut.isWorkingDay(dayOfWeek)

    where:
    dayOfWeek << Stream.of(DayOfWeek.values())
      .filter(dayOfWeek -> dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY)
      .collect(toList())
  }

  @Unroll("#pair.getA() in degree #pair.getB() is #result")
  def test3() {
    expect:
    Math.pow(pair.getA(), pair.getB()) == result

    where:
    pair           | result
    new Pair(1, 1) | 1
    new Pair(2, 2) | 2 * 2
    new Pair(3, 3) | 3 * 3 * 3
  }
}