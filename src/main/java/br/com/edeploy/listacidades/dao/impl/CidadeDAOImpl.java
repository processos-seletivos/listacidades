package br.com.edeploy.listacidades.dao.impl;

import br.com.edeploy.listacidades.dao.CidadeDAO;
import br.com.edeploy.listacidades.domain.Cidade;
import br.com.edeploy.listacidades.domain.entity.CidadeEntity;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by bruno on 06/01/18.
 */
@Repository
public class CidadeDAOImpl implements CidadeDAO{

    private static final String URL_BUSCA_TODAS_CIDADES = "http://wsteste.devedp.com.br/Master/CidadeServico.svc/rest/BuscaTodasCidades";

    @Override
    /**
     * Lista todas as cidades disponíveis no webservice
     */
    public List<Cidade> listarTodasCidades() {
        List<Cidade> cidades = new ArrayList<>();
        try {
            GetRequest request = Unirest.get(URL_BUSCA_TODAS_CIDADES).
                    header("accept", "application/json");
            HttpResponse<JsonNode> jsonResponse = jsonResponse = request.asJson();
            JSONArray cidadesObtidas = jsonResponse.getBody().getArray();
//            System.out.println("Número de cidades obtidas: " + cidadesObtidas.length());
            for(int i = 0; i < cidadesObtidas.length();i++) {
                JSONObject innerObj = cidadesObtidas.getJSONObject(i);
                Cidade cidade = new CidadeEntity(innerObj.get("Nome").toString(), innerObj.get("Estado").toString());
//                System.out.println("Cidade " + cidade.getNome() + " no estado de " + cidade.getEstado());
                cidades.add(cidade);
            }

        } catch (UnirestException e1) {
            System.out.println("Não foi possível obter as cidades.");
        }

        return cidades;
    }

    @Override
    /**
     * Busca as cidades que possuem o nome e/ou o estado informados. Retorna todas caso não sejam informados nem o nome
     * nem o estado.
     */
    public List<Cidade> buscarCidades(String nome, String estado) {
//        List<Cidade> todasCidades = listarTodasCidades();
        List<Cidade> todasCidades = new ArrayList<>();
        todasCidades.add(new CidadeEntity("Aracatuba", "SP"));
        todasCidades.add(new CidadeEntity("Birigui", "SP"));
        todasCidades.add(new CidadeEntity("Tres Lagoas", "MS"));
        List<Cidade> cidadesEncontradas = todasCidades.stream().filter(new Predicate<Cidade>() {
            @Override
            public boolean test(Cidade cidade) {
                if (nome != null && estado != null) {
                    return cidade.getEstado().toLowerCase().contains(estado.toLowerCase())
                            && cidade.getNome().toLowerCase().contains(nome.toLowerCase());
                } else if (nome != null) {
                    return cidade.getNome().toLowerCase().contains(nome.toLowerCase());
                } else if (estado != null) {
                    return cidade.getEstado().toLowerCase().contains(estado.toLowerCase());

                }
                return false;
            }
        }).collect(Collectors.toList());
        return cidadesEncontradas;
    }
}

