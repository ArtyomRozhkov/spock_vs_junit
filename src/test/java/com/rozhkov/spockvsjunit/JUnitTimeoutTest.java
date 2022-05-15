package com.rozhkov.spockvsjunit;

import com.rozhkov.spockvsjunit.service.TestService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

class JUnitTimeoutTest {

  TestService service = new TestService();

  @Test
  @DisplayName("проверка успешного выполнения операции за указанный таймаут")
  @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
  void test() throws InterruptedException {
    assertThat(service.doComplexCalculations()).isEqualTo(1);
  }
}
