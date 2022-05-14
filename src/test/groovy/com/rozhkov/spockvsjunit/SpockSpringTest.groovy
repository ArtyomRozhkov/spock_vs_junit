package com.rozhkov.spockvsjunit


import com.rozhkov.spockvsjunit.api.controller.ClientController
import com.rozhkov.spockvsjunit.repository.ClientRepository
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class SpockSpringTest extends Specification {

  @SpringBean
  ClientRepository clientRepository = Mock()

  @Autowired
  ClientController controller

  @Autowired
  private MockMvc mvc

  def "поднятие всего контекста с проверкой создания бина-контроллера"() {
    expect: "контроллер создан"
    controller
  }

  def "вызов метода контроллера"() { // без "throws Exception". Спасибо Groovy!
    given:
    String clientId = "123"
    String expectedName = "Murat"
    clientRepository.fetchClientName(clientId) >> expectedName

    expect:
    mvc.perform(get("/client/{id}/name", clientId))
      .andExpect(status().isOk())
      .andExpect(content().string(expectedName))
  }
}
