//file:noinspection GroovyAssignabilityCheck
package com.rozhkov.spockvsjunit

import com.rozhkov.spockvsjunit.service.TestService
import com.rozhkov.spockvsjunit.utils.Pair
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import java.time.DayOfWeek

class SpockParameterizedTest extends Specification {
  @Subject
  TestService service = new TestService()

  def "модуль любого числа должен быть >= 0"() {
    expect:
    Math.abs(param) >= 0

    where:
    param << (-2..2)
  }

  def "все дни кроме субботы и воскресенья - рабочие"() {
    expect:
    service.isWorkingDay(dayOfWeek)

    where:
    dayOfWeek << DayOfWeek.values()
      .findAll { it != DayOfWeek.SATURDAY && it != DayOfWeek.SUNDAY }
  }

  @Unroll("#map, pair = (#pair.getKey(), #pair.getValue()) - #contains")
  def "проверка вхождения в map заданных пар"() {
    expect:
    (map.get(pair.getKey()) == pair.getValue()) == contains

    where:
    map          | pair           || contains
    [1: 1, 2: 2] | new Pair(1, 1) || true
    [1: 2, 2: 1] | new Pair(2, 2) || false
    [4: 4]       | new Pair(3, 3) || false
    [:]          | new Pair(4, 4) || false
  }
}
