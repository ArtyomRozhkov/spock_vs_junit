package com.rozhkov.spockvsjunit

import org.apache.logging.log4j.util.Strings
import spock.lang.Specification
import spock.lang.Title

@Title("Spock")
class SpockDescriptiveNameTest extends Specification {

  def "сумма длин строк равна длине объединенной строки"() {
    given:
    var str1 = "1"
    var str2 = "2"

    when:
    String resultStr = Strings.concat(str1, str2)

    then:
    resultStr.length() == str1.length() + str2.length()
  }
}
