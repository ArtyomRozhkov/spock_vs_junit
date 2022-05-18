//file:noinspection GroovyUnreachableStatement
package com.rozhkov.spockvsjunit

import spock.lang.FailsWith
import spock.lang.Specification

class SpockExpectingExceptionTest extends Specification {

  @FailsWith(NumberFormatException)
  def "генерация исключения при делении на 0"() {
    expect:
    Integer.parseInt("six")
  }

  def "генерация исключения с заданными параметрами"() {
    when:
    throw new IllegalStateException("boom!", new RuntimeException())

    then:
    def exception = thrown(IllegalStateException)
    exception.getMessage() == "boom!"
    exception.getCause() instanceof RuntimeException
  }
}
