package com.rozhkov.spockvsjunit

import spock.lang.Specification

class SpockStructuredTest extends Specification {

  def "максимальное число между 1 и 2 равно 2"() {
    expect:
    Math.max(1, 2) == 2
  }

  def "проверка конкатенации двух строк через пробел"() {
    given: // или setup:
    var str1 = "hello"
    var str2 = "world"

    when:
    String actual = String.join(" ", str1, str2)

    then:
    actual == "hello world"

    cleanup:
    println("clean up")
  }
}
