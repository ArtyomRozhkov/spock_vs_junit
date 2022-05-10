package com.rozhkov.spockvsjunit

import spock.lang.Specification

class SpockStructuredTest extends Specification {

  def test1() {
    expect:
    Math.max(1, 2) == 2
  }

  def test2() {
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
