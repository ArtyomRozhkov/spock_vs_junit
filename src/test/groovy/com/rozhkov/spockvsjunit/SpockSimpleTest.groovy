package com.rozhkov.spockvsjunit

import spock.lang.Ignore
import spock.lang.Specification

class SpockSimpleTest extends Specification {

  def test() {
    expect:
    Math.sqrt(4) == 2
  }

  @Ignore("некогда разбираться, отключаем")
  def skippedTest() {
    expect:
    Math.random() == 1
  }
}
