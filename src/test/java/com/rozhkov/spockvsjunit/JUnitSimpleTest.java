package com.rozhkov.spockvsjunit;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JUnitSimpleTest {

  @Test
  void test() {
    assertThat(Math.sqrt(4)).isEqualTo(2);
  }

  @Test
  @Disabled("некогда разбираться, отключаем")
  void skippedTest() {
    assertThat(Math.random()).isEqualTo(1);
  }
}
