package com.rozhkov.spockvsjunit

import com.rozhkov.spockvsjunit.service.TestService
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Timeout

import java.util.concurrent.TimeUnit

class SpockTimeoutTest extends Specification {

  @Subject
  TestService sut = new TestService()

  @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
  def test() { // throws секция не нужна
    expect:
    sut.doComplexCalculations() == 1
  }
}
