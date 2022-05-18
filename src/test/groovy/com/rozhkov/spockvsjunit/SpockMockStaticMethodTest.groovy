package com.rozhkov.spockvsjunit

import spock.lang.Specification

class SpockMockStaticMethodTest extends Specification {

  def "мок статического метода без параметров"() {
    given:
    UUID expectedUUID = UUID.randomUUID()
    UUID.metaClass.static.randomUUID = { -> expectedUUID }

    expect:
    UUID.randomUUID() == expectedUUID
  }

  def "мок статического метода с параметрами"() {
    given:
    Integer.metaClass.static.valueOf = { String s -> 6 }

    expect:
    Integer.valueOf("шесть") == 6
  }
}

