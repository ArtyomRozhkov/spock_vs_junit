package com.rozhkov.spockvsjunit;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JUnitExpectingExceptionTest {

  @Test
  void test1() {
    assertThrows(ArithmeticException.class, () -> {
      int c = 1 / 0;
    });
  }

  @Test
  void test2() {
    assertThatThrownBy(
      () -> {
        throw new IllegalStateException("boom!", new RuntimeException());
      })
      .isInstanceOf(IllegalStateException.class)
      .hasMessage("boom!")
      .hasCauseInstanceOf(RuntimeException.class);
  }
}
