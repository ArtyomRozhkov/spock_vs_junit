package com.rozhkov.spockvsjunit

import com.rozhkov.spockvsjunit.service.TestService
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class SpockRepetitionTest extends Specification {

  @Subject
  TestService service = new TestService()

  @Unroll("Iteration #i of 10")
  def "проверка возврата одинакового значения при вызове идемпотентной операции"() { // или def "Iteration #i of 5"() {
    expect:
    service.doIdempotentOperation(1) == 4

    where:
    i << (1..10)
  }
}
