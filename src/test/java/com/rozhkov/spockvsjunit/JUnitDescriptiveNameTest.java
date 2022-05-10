package com.rozhkov.spockvsjunit;

import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("JUnit")
class JUnitDescriptiveNameTest {

  @Test
  @DisplayName("сумма длин строк равна длине объединенной строки")
  void testSumOfStringLengthsEqualToLengthOfCombinedString() {
    var str1 = "1";
    var str2 = "2";

    String resultStr = Strings.concat(str1, str2);

    assertThat(resultStr)
      .hasSize(str1.length() + str2.length());
  }
}
