package com.rozhkov.spockvsjunit

import com.rozhkov.spockvsjunit.repository.ClientRepository
import com.rozhkov.spockvsjunit.repository.NotificationService
import com.rozhkov.spockvsjunit.service.ClientService
import spock.lang.Ignore
import spock.lang.Specification

@Ignore("для работы тестов необходимы доработки")
class SpockSmellTest extends Specification {

  def "заглушка метода с подсчетом количества вызовов"() {
    given:
    String clientId = "1"
    String expectedName = "Ratibor"

    ClientRepository clientRepository = Mock() {
      fetchClientName(clientId) >> expectedName
    }

    ClientService service = new ClientService(clientRepository, Stub(NotificationService))

    when:
    String actualName = service.fetchClientName(clientId)

    then:
    actualName == expectedName
    1 * clientRepository.fetchClientName(clientId)
  }

  def "проверка вызова мока с определенными значениями"() {
    given:
    ClientRepository clientRepository = Mock()
    ClientService service = new ClientService(clientRepository, Stub(NotificationService))

    when:
    service.addNewClient("Ratibor")

    then:
    //interaction
    String expectedName = "Ratibor"
    1 * clientRepository.addNewClient(expectedName, _)
  }

  def "заглушка на несуществующий метод"() {
    given:
    String clientId = "1"
    String expectedName = "Ratibor"

    ClientRepository clientRepository = Mock()
    clientRepository.fetchClientName(clientId, List.of()) >> expectedName

    ClientService service = new ClientService(
      clientRepository,
      Stub(NotificationService)
    )

    when:
    String actualName = service.fetchClientName(clientId)

    then:
    actualName == expectedName
  }
}
