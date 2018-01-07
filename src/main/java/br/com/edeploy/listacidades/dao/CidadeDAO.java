package br.com.edeploy.listacidades.dao;

import br.com.edeploy.listacidades.domain.Cidade;

import java.util.List;

/**
 * Created by bruno on 06/01/18.
 */

public interface CidadeDAO {

    /**
     * Retorna uma lista com todas as cidades cadastradas no webservice
     * @return Lista das cidades cadastradas.
     */
    List<Cidade> listarTodasCidades();

    /**
     * Busca cidades cujos nomes contenham o nome passado como parâmetro e/ou cujo estado contenha o estado passado como
     * parâmetro.
     * @param nome Nome ou parte do nome da cidade que se deseja buscar.
     * @param estado Nome ou parte do nome do estado que se deseja buscar.
     * @return Lista das cidades que atendam os critérios acima mencionados.
     */
    List<Cidade> buscarCidades(String nome, String estado);

    /**
     * Busca a pontuação de uma determinada cidade.
     * @param nome Nome da cidade que se deseja buscar a pontuação.
     * @param estado Estado da cidade que se deseja buscar a pontuação.
     * @return Pontuação da cidade no momento (muda a cada chamada).
     */
    Long buscarPontuacao(String nome, String estado);
}
