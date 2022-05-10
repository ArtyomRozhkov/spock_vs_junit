package com.rozhkov.spockvsjunit;

import com.rozhkov.spockvsjunit.service.TestService;
import com.rozhkov.spockvsjunit.utils.Pair;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.DayOfWeek;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class JUnitTestWithParameters {
  // sut - subject under test
  private final TestService sut = new TestService();

  @ParameterizedTest
  @ValueSource(ints = {-1, 0, 1})
  void test1(int param) {
    assertThat(Math.abs(param)).isNotNegative();
  }

  @ParameterizedTest
  @EnumSource(
    value = DayOfWeek.class,
    names = {"SATURDAY", "SUNDAY"},
    mode = EnumSource.Mode.EXCLUDE)
  void test2(DayOfWeek dayOfWeek) {
    assertThat(sut.isWorkingDay(dayOfWeek)).isTrue();
  }

  @ParameterizedTest(name = "a in degree b ({0}) is {1}")
  @MethodSource("test3Params")
  void test3(Pair pair, int result) {
    assertThat(Math.pow(pair.getA(), pair.getB()))
      .isEqualTo(result);
  }

  static Stream<Arguments> test3Params() {
    return Stream.of(
      Arguments.of(new Pair(1, 1), 1),
      Arguments.of(new Pair(2, 2), 2 * 2),
      Arguments.of(new Pair(3, 3), 3 * 3 * 3)
    );
  }
}
