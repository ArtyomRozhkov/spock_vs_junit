package com.rozhkov.spockvsjunit;

import com.rozhkov.spockvsjunit.service.TestService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

class JUnitTimeoutTest {

  private final TestService sut = new TestService();

  @Test
  @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
  void test() throws InterruptedException {
    assertThat(sut.doComplexCalculations()).isEqualTo(1);
  }
}
