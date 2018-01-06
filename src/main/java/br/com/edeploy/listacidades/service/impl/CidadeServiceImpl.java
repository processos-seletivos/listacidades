package br.com.edeploy.listacidades.service.impl;

import br.com.edeploy.listacidades.dao.CidadeDAO;
import br.com.edeploy.listacidades.domain.Cidade;
import br.com.edeploy.listacidades.domain.dto.CidadeDTO;
import br.com.edeploy.listacidades.domain.entity.CidadeEntity;
import br.com.edeploy.listacidades.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by bruno on 06/01/18.
 */
@Service
public class CidadeServiceImpl implements CidadeService{

    private CidadeDAO cidadeDAO;

    @Autowired
    public CidadeServiceImpl(CidadeDAO cidadeDAO) {
        this.cidadeDAO = cidadeDAO;
    }

    @Override
    public List<CidadeDTO> listarTodasCidades() {
        List<Cidade> entidades = cidadeDAO.listarTodasCidades();
        List<CidadeDTO> cidades = entidades.stream()
                .map(CidadeDTO::new)
                .collect(Collectors.toList());
        return cidades;
    }

    @Override
    public List<CidadeDTO> buscarCidades(String nome, String estado) {
        List<Cidade> entidades = cidadeDAO.buscarCidades(nome, estado);


        List<CidadeDTO> cidades = entidades.stream()
                .map(CidadeDTO::new)
                .collect(Collectors.toList());
        return cidades;
    }
}
