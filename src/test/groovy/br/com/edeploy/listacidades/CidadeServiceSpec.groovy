package br.com.edeploy.listacidades

import br.com.edeploy.listacidades.dao.impl.CidadeDAOImpl
import br.com.edeploy.listacidades.domain.dto.CidadeDTO
import br.com.edeploy.listacidades.domain.entity.CidadeEntity
import br.com.edeploy.listacidades.service.CidadeService
import br.com.edeploy.listacidades.service.impl.CidadeServiceImpl

/**
 * Created by bruno on 1/7/18.
 */

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import spock.lang.Subject


@SpringBootTest(webEnvironment = NONE)
class CidadeServiceSpec extends Specification {
    @Subject CidadeService service

    CidadeDAOImpl dao = Mock()

    def setup() {
        service = new CidadeServiceImpl(dao)
    }

    def 'Listar todas as cidades deve trazer todas as cidades presentes no webservice'() {
        given: "Um mock do DAO que retorna somente uma cidade"
        1 * dao.listarTodasCidades() >> [new CidadeEntity(nome: "Araçatuba", estado: "São Paulo")]

        when: "Chama o listar todas as cidades"
        def cidades = service.listarTodasCidades()

        then: "A cidade é retornada pelo service"
        cidades.size()== 1

        and: "A classe do objeto é "
        cidades.first().class == CidadeDTO.class
    }
}



