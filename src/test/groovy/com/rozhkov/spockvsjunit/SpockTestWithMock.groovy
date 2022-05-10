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
  ClientService sut = new ClientService(clientRepository, contractRepository)

  def test1() {
    when:
    def available = sut.isFuncAvailable()

    then:
    available
    0 * _
  }

  def test2() {
    given:
    def clientId = "1"
    String expectedName = "Ratibor"
    clientRepository.fetchClientName(clientId) >> expectedName

    when:
    String actualName = sut.fetchClientName(clientId)

    then:
    actualName == expectedName
  }

  def test3() {
    given:
    clientRepository.fetchClientName(_) >>
      { throw new RuntimeException("boom!") }

    when:
    sut.fetchClientName("client_id")

    then:
    def e = thrown(RuntimeException)
    e.message == "boom!"
  }

  def test4() {
    given:
    def clientId = "1"
    String firstExpectedName = "Ratibor"
    String secondExpectedName = "Mansur"
    clientRepository.fetchClientName(clientId) >>>
      [firstExpectedName, secondExpectedName] >>
      { throw new RuntimeException() }

    when:
    String actualName = sut.fetchClientName(clientId)
    then:
    actualName == firstExpectedName

    when:
    actualName = sut.fetchClientName(clientId)
    then:
    actualName == secondExpectedName

    when:
    sut.fetchClientName(clientId)
    then:
    thrown(RuntimeException)
  }

  def test5() {
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
    String actualName = sut.fetchClientName("1")
    then:
    actualName == expectedName

    when:
    sut.fetchClientName(null)
    then:
    thrown(IllegalArgumentException)
  }

  def test6() {
    given:
    def clientName = "Ratibor"

    when:
    sut.addNewClient(clientName)

    then:
    1 * clientRepository
      .addNewClient(clientName, { it.size() == 0 })
    0 * _
  }
}
