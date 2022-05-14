package com.rozhkov.spockvsjunit

import com.rozhkov.spockvsjunit.repository.ClientRepository
import com.rozhkov.spockvsjunit.repository.ContractRepository
import com.rozhkov.spockvsjunit.service.ClientService
import spock.lang.Specification
import spock.lang.Subject

class SpockTestWithMock extends Specification {

  ClientRepository clientRepository = Mock()
  ContractRepository contractRepository = Mock()

  @Subject
  ClientService service = new ClientService(clientRepository, contractRepository)

  def "проверка отсутствия взаимодействия с моками"() {
    when:
    def available = service.isFuncAvailable()

    then:
    available
    0 * _
  }

  def "задание значения, которое должен возвращать мок"() {
    given:
    def clientId = "1"
    String expectedName = "Ratibor"
    clientRepository.fetchClientName(clientId) >> expectedName

    when:
    String actualName = service.fetchClientName(clientId)

    then:
    actualName == expectedName
  }

  def "генерация исключения при вызове метода мока"() {
    given:
    clientRepository.fetchClientName(_) >>
      { throw new RuntimeException("boom!") }

    when:
    service.fetchClientName("client_id")

    then:
    def e = thrown(RuntimeException)
    e.message == "boom!"
  }

  def "возврат моком цепочки значений"() {
    given:
    def clientId = "1"
    String firstExpectedName = "Ratibor"
    String secondExpectedName = "Mansur"

    clientRepository.fetchClientName(clientId) >>>
      [firstExpectedName, secondExpectedName] >>
      { throw new RuntimeException() }

    when: "1 вызов мока"
    String actualName = service.fetchClientName(clientId)
    then: "ожидаем первое значение"
    actualName == firstExpectedName

    when: "2 вызов мока"
    actualName = service.fetchClientName(clientId)
    then: "ожидаем второе значение"
    actualName == secondExpectedName

    when: "3 вызов мока"
    service.fetchClientName(clientId)
    then: "3 и последующие вызовы будут генерировать исключение"
    thrown(RuntimeException)
  }

  def "вычисление результата метода мока на основе входных параметров"() {
    given:
    String expectedName = "Ratibor"
    clientRepository.fetchClientName(_) >> { String id ->
      if (id == null) {
        throw new IllegalArgumentException()
      } else {
        return expectedName
      }
    }

    when:
    String actualName = service.fetchClientName("1")
    then:
    actualName == expectedName

    when:
    service.fetchClientName(null)
    then:
    thrown(IllegalArgumentException)
  }

  def "проверка вызова мока с определенными значениями"() {
    given:
    def clientName = "Ratibor"

    when:
    service.addNewClient(clientName)

    then:
    1 * clientRepository
      .addNewClient(clientName, { it.size() == 0 })
    0 * _
  }
}
