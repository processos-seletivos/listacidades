package br.com.edeploy.listacidades.dao;

import br.com.edeploy.listacidades.domain.Cidade;

import java.util.List;

/**
 * Created by bruno on 06/01/18.
 */

public interface CidadeDAO {
    List<Cidade> listarTodasCidades();

    List<Cidade> buscarCidades(String nome, String estado);
}
