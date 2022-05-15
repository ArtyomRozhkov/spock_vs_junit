package com.rozhkov.spockvsjunit

import com.rozhkov.spockvsjunit.service.TestService
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Timeout

import java.util.concurrent.TimeUnit

class SpockTimeoutTest extends Specification {

  @Subject
  TestService service = new TestService()

  @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
  def "проверка успешного выполнения операции за указанный таймаут"() { // throws секция не нужна. Спасибо Groovy
    expect:
    service.doComplexCalculations() == 1
  }
}
