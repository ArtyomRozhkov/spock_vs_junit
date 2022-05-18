package com.rozhkov.spockvsjunit;

import com.rozhkov.spockvsjunit.repository.ClientRepository;
import com.rozhkov.spockvsjunit.repository.NotificationService;
import com.rozhkov.spockvsjunit.service.ClientService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JUnitMockTest {

  @Captor
  ArgumentCaptor<List<String>> phonesCaptor;

  @Mock
  ClientRepository clientRepository;
  @Mock
  NotificationService notificationService;

  @InjectMocks
  ClientService service;

  @Test
  @DisplayName("проверка отсутствия взаимодействия с моками")
  void test1() {
    assertTrue(service.isFuncAvailable());
    verifyNoInteractions(clientRepository, notificationService);
  }

  @Test
  @DisplayName("задание значения, которое должен возвращать мок")
  void test2() {
    // given
    var clientId = "1";
    String expectedName = "Ratibor";
    when(clientRepository.fetchClientName(clientId))
      .thenReturn(expectedName);

    // when
    String actualName = service.fetchClientName(clientId);

    // then
    assertThat(actualName).isEqualTo(expectedName);
  }

  @Test
  @DisplayName("генерация исключения при вызове метода мока")
  void test3() {
    doThrow(new RuntimeException("boom!"))
      .when(clientRepository).fetchClientName(any());

    assertThatThrownBy(() -> service.fetchClientName("client_id"))
      .isInstanceOf(RuntimeException.class)
      .hasMessage("boom!");
  }

  @Test
  @DisplayName("возврат моком цепочки значений")
  void test4() {
    // given
    var clientId = "1";
    String firstExpectedName = "Ratibor";
    String secondExpectedName = "Mansur";

    when(clientRepository.fetchClientName(clientId))
      .thenReturn(firstExpectedName, secondExpectedName)
      .thenThrow(new RuntimeException());

    // when
    String actualName = service.fetchClientName(clientId);
    // then
    assertThat(actualName).isEqualTo(firstExpectedName);

    // when
    actualName = service.fetchClientName(clientId);
    // then
    assertThat(actualName).isEqualTo(secondExpectedName);

    // expect
    assertThatThrownBy(() -> service.fetchClientName(clientId))
      .isInstanceOf(RuntimeException.class);
  }

  @Test
  @DisplayName("вычисление результата метода мока на основе входных параметров")
  void test5() {
    String expectedName = "Ratibor";
    when(clientRepository.fetchClientName(any()))
      .thenAnswer(invocation -> {
        if (invocation.getArgument(0) == null) {
          throw new IllegalArgumentException();
        } else {
          return expectedName;
        }
      });

    // when
    String actualName = service.fetchClientName("1");
    // then
    assertThat(actualName).isEqualTo(expectedName);

    // expect
    assertThatThrownBy(() -> service.fetchClientName(null))
      .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  @DisplayName("проверка вызова мока с определенными значениями")
  void test6() {
    var clientName = "Ratibor";

    service.addNewClient(clientName);

    verify(clientRepository)
      .addNewClient(eq(clientName), argThat(p -> p.size() == 0));
    verify(notificationService).sendNotification(clientName, "add");
    verifyNoMoreInteractions(clientRepository, notificationService);

//  verify(clientRepository).addNewClient(eq(clientName), phonesCaptor.capture());
//  assertThat(phonesCaptor.getValue()).isEmpty();
  }
}