package com.rozhkov.spockvsjunit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JUnitStructuredTest {

  @Test
  @DisplayName("максимальное число между 1 и 2 равно 2")
  void test1() {
    // expect
    assertThat(Math.max(1, 2)).isEqualTo(2);
  }

  @Test
  @DisplayName("проверка конкатенации двух строк через пробел")
  void test2() {
    // given
    var str1 = "hello";
    var str2 = "world";

    // when
    String actual = String.join(" ", str1, str2);

    // then
    assertThat(actual).isEqualTo("hello world");
  }
}
