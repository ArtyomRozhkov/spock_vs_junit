package com.rozhkov.spockvsjunit

import org.apache.logging.log4j.util.Strings
import spock.lang.Specification

class SpockTrickTest extends Specification {

  def "вызов статического метода на экземпляре класса"() {
    given:
    var str1 = "1"
    var str2 = "2"

    when:
    String resultStr = Strings.concat(str1, str2)

    then:
    resultStr.size() == str1.size() + str2.size() // trick
  }
}
