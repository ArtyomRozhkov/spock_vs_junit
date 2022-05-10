package com.rozhkov.spockvsjunit

import com.rozhkov.spockvsjunit.service.TestService
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll


class SpockRepetitionTest extends Specification {

  @Subject
  TestService sut = new TestService()

  @Unroll
  def test1() {
    expect:
    sut.doIdempotentOperation(1) == 4

    where:
    i << (1..10)
  }

  @Unroll("Iteration #i of 5")
  def test2() { // или def "Iteration #i of 5"() {
    expect:
    sut.doIdempotentOperation(1) == 4

    where:
    i << (1..5)
  }
}