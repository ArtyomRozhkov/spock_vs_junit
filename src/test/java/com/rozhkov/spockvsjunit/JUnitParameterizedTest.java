package com.rozhkov.spockvsjunit;

import com.rozhkov.spockvsjunit.service.TestService;
import com.rozhkov.spockvsjunit.utils.Pair;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.DayOfWeek;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

class JUnitParameterizedTest {
  private final TestService service = new TestService();

  @ParameterizedTest
  @ValueSource(ints = {-2, -1, 0, 1, 2})
  @DisplayName("модуль любого числа должен быть >= 0")
  void test1(int param) {
    assertThat(Math.abs(param)).isNotNegative();
  }

  @ParameterizedTest
  @EnumSource(
    value = DayOfWeek.class,
    names = {"SATURDAY", "SUNDAY"},
    mode = EnumSource.Mode.EXCLUDE)
  @DisplayName("все дни кроме субботы и воскресенья - рабочие")
  void test2(DayOfWeek dayOfWeek) {
    assertThat(service.isWorkingDay(dayOfWeek)).isTrue();
  }

  @ParameterizedTest(name = "{0}, {1} - {2}")
  @MethodSource("test3Params")
  @DisplayName("проверка вхождения в map заданных пар")
  void test3(Map<Integer, Integer> map, Pair pair, boolean contains) {
    assertThat(Objects.equals(map.get(pair.getKey()), pair.getValue()))
      .isEqualTo(contains);
  }

  static Stream<Arguments> test3Params() {
    return Stream.of(
      Arguments.of(Map.of(1, 1, 2, 2), new Pair(1, 1), true),
      Arguments.of(Map.of(1, 2, 2, 1), new Pair(2, 2), false),
      Arguments.of(Map.of(4, 4), new Pair(3, 3), false),
      Arguments.of(Map.of(), new Pair(4, 4), false)
    );
  }
}
