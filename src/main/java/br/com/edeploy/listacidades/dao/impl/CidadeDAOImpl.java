package br.com.edeploy.listacidades.dao.impl;

import br.com.edeploy.listacidades.dao.CidadeDAO;
import br.com.edeploy.listacidades.domain.Cidade;
import br.com.edeploy.listacidades.domain.entity.CidadeEntity;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequest;
import com.mashape.unirest.request.HttpRequestWithBody;
import com.mashape.unirest.request.body.MultipartBody;
import com.mashape.unirest.request.body.RequestBodyEntity;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(CidadeDAOImpl.class);

    private static final String URL_BUSCA_TODAS_CIDADES = "http://wsteste.devedp.com.br/Master/CidadeServico.svc/rest/BuscaTodasCidades";
    private static final String URL_BUSCA_PONTUACAO_CIDADE = "http://wsteste.devedp.com.br/Master/CidadeServico.svc/rest/BuscaPontos";

    @Override
    public List<Cidade> listarTodasCidades() {
        List<Cidade> cidades = new ArrayList<>();
        try {
            GetRequest request = Unirest.get(URL_BUSCA_TODAS_CIDADES).
                    header("accept", "application/json");
            HttpResponse<JsonNode> jsonResponse = jsonResponse = request.asJson();
            JSONArray cidadesObtidas = jsonResponse.getBody().getArray();
            logger.info("Número de cidades obtidas: " + cidadesObtidas.length());
            for(int i = 0; i < cidadesObtidas.length();i++) {
                JSONObject innerObj = cidadesObtidas.getJSONObject(i);
                Cidade cidade = new CidadeEntity(innerObj.get("Nome").toString(), innerObj.get("Estado").toString());
                logger.debug("Cidade " + cidade.getNome() + " no estado de " + cidade.getEstado());
                cidades.add(cidade);
            }

        } catch (UnirestException e1) {
            logger.error("Não foi possível obter as cidades. Motivo: " + e1.getLocalizedMessage());
        }

        return cidades;
    }

    @Override
    public List<Cidade> buscarCidades(String nome, String estado) {
        List<Cidade> todasCidades = listarTodasCidades();
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
                return true;
            }
        }).collect(Collectors.toList());
        return cidadesEncontradas;
    }

    @Override
    public Long buscarPontuacao(String nome, String estado) {
        Long pontuacao = 0L;
        JSONWriter jsonWriter = new JSONStringer();
        String body = jsonWriter.object().key("nome").value(nome).key("estado").value(estado).endObject().toString();

        RequestBodyEntity request = Unirest.post(URL_BUSCA_PONTUACAO_CIDADE).
                header("Accept", "application/json").header("Content-Type","application/json").body(
                       new JsonNode(body) );
        try {
            pontuacao = Long.parseLong(request.asString().getBody());
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return pontuacao;
    }
}

