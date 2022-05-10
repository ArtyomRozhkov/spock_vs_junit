package com.rozhkov.spockvsjunit;

import com.rozhkov.spockvsjunit.service.TestService;
import org.junit.jupiter.api.RepeatedTest;

import static org.assertj.core.api.Assertions.assertThat;

class JUnitRepetitionTest {

  private final TestService sut = new TestService();

  @RepeatedTest(10)
  void test1() {
    assertThat(sut.doIdempotentOperation(1)).isEqualTo(4);
  }

  @RepeatedTest(value = 5, name = "Iteration {currentRepetition} of {totalRepetitions}")
  void test2() {
    assertThat(sut.doIdempotentOperation(1)).isEqualTo(4);
  }
}
