package com.rozhkov.spockvsjunit

import com.rozhkov.spockvsjunit.repository.ClientRepository
import com.rozhkov.spockvsjunit.repository.ContractRepository
import com.rozhkov.spockvsjunit.service.ClientService
import org.apache.logging.log4j.util.Strings
import spock.lang.Specification

class SpockTestWithSmell extends Specification {

  def "вызов статического метода на экземпляре класса"() {
    given:
    var str1 = "1"
    var str2 = "2"

    when:
    String resultStr = Strings.concat(str1, str2)

    then:
    resultStr.size() == str1.size() + str2.size() // wtf
  }

  def "заглушка метода с подсчетом количества вызовов"() {
    given:
    String clientId = "1"
    String expectedName = "Ratibor"

    ClientRepository clientRepository = Mock() {
      fetchClientName(clientId) >> expectedName
    }

    ClientService service = new ClientService(clientRepository, Stub(ContractRepository))

    when:
    String actualName = service.fetchClientName(clientId)

    then:
    actualName == expectedName
    1 * clientRepository.fetchClientName(clientId)
  }

  def "проверка вызова мока с определенными значениями"() {
    given:
    ClientRepository clientRepository = Mock()
    ClientService service = new ClientService(clientRepository, Stub(ContractRepository))

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

    ClientService service = new ClientService(clientRepository, Stub(ContractRepository))

    when:
    String actualName = service.fetchClientName(clientId)

    then:
    actualName == expectedName
  }
}
