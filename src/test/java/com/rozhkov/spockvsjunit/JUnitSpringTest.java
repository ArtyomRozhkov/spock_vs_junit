package com.rozhkov.spockvsjunit;

import com.rozhkov.spockvsjunit.api.controller.ClientController;
import com.rozhkov.spockvsjunit.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class JUnitSpringTest {

  @MockBean
  ClientRepository clientRepository;

  @Autowired
  ClientController controller;

  @Autowired
  private MockMvc mvc;

  @Test
  void contextLoads() {
    assertThat(controller).isNotNull();
  }

  @Test
  void testGetClient() throws Exception {
    String clientId = "123";
    String expectedName = "Murat";
    when(clientRepository.fetchClientName(clientId))
      .thenReturn(expectedName);

    mvc.perform(get("/client/{id}/name", clientId))
      .andExpect(status().isOk())
      .andExpect(content().string(expectedName));
  }
}
