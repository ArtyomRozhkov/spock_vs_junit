package com.rozhkov.spockvsjunit;

import com.rozhkov.spockvsjunit.repository.ClientRepository;
import com.rozhkov.spockvsjunit.repository.ContractRepository;
import com.rozhkov.spockvsjunit.service.ClientService;
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
class JUnitTestWithMock {

  @Captor
  ArgumentCaptor<List<String>> phonesCaptor;

  @Mock
  ClientRepository clientRepository;
  @Mock
  ContractRepository contractRepository;

  @InjectMocks
  ClientService sut;

  @Test
  void test1() {
    assertTrue(sut.isFuncAvailable());
    verifyNoInteractions(clientRepository, contractRepository);
  }

  @Test
  void test2() {
    // given
    var clientId = "1";
    String expectedName = "Ratibor";
    when(clientRepository.fetchClientName(clientId))
      .thenReturn(expectedName);

    // when
    String actualName = sut.fetchClientName(clientId);

    // then
    assertThat(actualName).isEqualTo(expectedName);
  }

  @Test
  void test3() {
    doThrow(new RuntimeException("boom!"))
      .when(clientRepository).fetchClientName(any());

    assertThatThrownBy(() -> sut.fetchClientName("client_id"))
      .isInstanceOf(RuntimeException.class)
      .hasMessage("boom!");
  }

  @Test
  void test4() {
    // given
    var clientId = "1";
    String firstExpectedName = "Ratibor";
    String secondExpectedName = "Mansur";
    when(clientRepository.fetchClientName(clientId))
      .thenReturn(firstExpectedName, secondExpectedName)
      .thenThrow(new RuntimeException());

    // when
    String actualName = sut.fetchClientName(clientId);
    // then
    assertThat(actualName).isEqualTo(firstExpectedName);

    // when
    actualName = sut.fetchClientName(clientId);
    // then
    assertThat(actualName).isEqualTo(secondExpectedName);

    // expect
    assertThatThrownBy(() -> sut.fetchClientName(clientId))
      .isInstanceOf(RuntimeException.class);
  }

  @Test
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
    String actualName = sut.fetchClientName("1");
    // then
    assertThat(actualName).isEqualTo(expectedName);

    // expect
    assertThatThrownBy(() -> sut.fetchClientName(null))
      .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void test6() {
    var clientName = "Ratibor";

    sut.addNewClient(clientName);

    verify(clientRepository)
      .addNewClient(eq(clientName), argThat(p -> p.size() == 0));

//  verify(clientRepository).addNewClient(eq(clientName), phonesCaptor.capture());
//  assertThat(phonesCaptor.getValue()).isEmpty();

    verifyNoMoreInteractions(clientRepository, contractRepository);
  }
}