package com.rozhkov.spockvsjunit;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class JUnitSetupAndTurnDownTest {

  @BeforeAll
  static void setupTest() {
    log.info("setup test");
  }

  @AfterAll
  static void cleanupTest() {
    log.info("cleanup test");
  }

  @BeforeEach
  void setup() {
    log.info("before each");
  }

  @AfterEach
  void cleanup() {
    log.info("cleanup after each");
  }

  @Test
  void test() {
    log.info("test");
    assertThat(Math.abs(-1)).isEqualTo(1);
  }
}
