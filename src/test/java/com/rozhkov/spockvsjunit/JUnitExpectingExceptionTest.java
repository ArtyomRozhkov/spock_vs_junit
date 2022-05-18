package com.rozhkov.spockvsjunit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JUnitExpectingExceptionTest {

  @Test
  @DisplayName("генерация исключения при делении на 0")
  void test1() {
    assertThrows(
      NumberFormatException.class,
      () -> Integer.parseInt("six")
    );
  }

  @Test
  @DisplayName("генерация исключения с заданными параметрами")
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
