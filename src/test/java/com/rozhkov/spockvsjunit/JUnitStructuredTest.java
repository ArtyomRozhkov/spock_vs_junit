package com.rozhkov.spockvsjunit;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JUnitStructuredTest {

  @Test
  void test1() {
    // expect
    assertThat(Math.max(1, 2)).isEqualTo(2);
  }

  @Test
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
