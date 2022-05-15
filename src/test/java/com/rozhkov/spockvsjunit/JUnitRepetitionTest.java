package com.rozhkov.spockvsjunit;

import com.rozhkov.spockvsjunit.service.TestService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import static org.assertj.core.api.Assertions.assertThat;

class JUnitRepetitionTest {

  TestService service = new TestService();

  @DisplayName("проверка возврата одинакового значения при вызове идемпотентной операции")
  @RepeatedTest(value = 10, name = "Iteration {currentRepetition} of {totalRepetitions}")
  void test() {
    assertThat(service.doIdempotentOperation(1)).isEqualTo(4);
  }
}
