package com.rozhkov.spockvsjunit

import com.rozhkov.spockvsjunit.repository.ClientRepository
import com.rozhkov.spockvsjunit.repository.ContractRepository
import com.rozhkov.spockvsjunit.service.ClientService
import org.apache.logging.log4j.util.Strings
import spock.lang.Specification

class SpockTestWithSmell extends Specification {

  def "calling static methods on class instances"() {
    given:
    var str1 = "1"
    var str2 = "2"

    when:
    String resultStr = Strings.concat(str1, str2)

    then:
    resultStr.size() == str1.size() + str2.size() // wtf
  }

  def "method stub with counting the number of calls"() {
    given:
    String clientId = "1"
    String expectedName = "Ratibor"

    ClientRepository clientRepository = Mock()
    clientRepository.fetchClientName(clientId) >> expectedName

    ClientService service = new ClientService(clientRepository, Stub(ContractRepository))

    when:
    String actualName = service.fetchClientName(clientId)

    then:
    actualName == expectedName
    1 * clientRepository.fetchClientName(clientId)
  }

  def "stub of a non-existent method"() {
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
