package br.com.edeploy.listacidades.service;

import br.com.edeploy.listacidades.domain.dto.CidadeDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bruno on 06/01/18.
 */
public interface CidadeService {
    List<CidadeDTO> listarTodasCidades();
    List<CidadeDTO> buscarCidades(String nome, String estado);
    Long buscarPontuacao(String nome, String estado);


}
