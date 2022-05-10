package com.rozhkov.spockvsjunit

import spock.lang.FailsWith
import spock.lang.Specification

class SpockExpectingExceptionTest extends Specification {

  @FailsWith(ArithmeticException)
  def test1() {
    expect:
    1 / 0
  }

  def test2() {
    when:
    throw new IllegalStateException("boom!", new RuntimeException())

    then:
    def exception = thrown(IllegalStateException)
    exception.getMessage() == "boom!"
    exception.getCause() instanceof RuntimeException
  }
}
