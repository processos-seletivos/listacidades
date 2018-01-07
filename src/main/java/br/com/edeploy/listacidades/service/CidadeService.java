package br.com.edeploy.listacidades.service;

import br.com.edeploy.listacidades.domain.dto.CidadeDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bruno on 06/01/18.
 */
public interface CidadeService {
    /**
     * Lista todas as cidades cadastradas.
     * @return Lista contendo todas as cidades cadastradas.
     */
    List<CidadeDTO> listarTodasCidades();

    /**
     * Busca todas as cidades que possuam o nome e/ou o estado informados.
     * @param nome Nome ou parte do nome da cidade que se deseja buscar.
     * @param estado Nome ou parte do nome do estado que se deseja buscar.
     * @return Lista de cidades que atendam os critérios acima mencionados.
     */
    List<CidadeDTO> buscarCidades(String nome, String estado);
    Long buscarPontuacao(String nome, String estado);


}
