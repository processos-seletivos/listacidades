package br.com.edeploy.listacidades.controller;

import br.com.edeploy.listacidades.domain.dto.CidadeDTO;
import br.com.edeploy.listacidades.service.CidadeService;
import org.json.JSONStringer;
import org.json.JSONWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bruno on 05/01/18.
 */
@Controller
@RequestMapping(value = "cidade")
public class CidadeController {

    private CidadeService cidadeService;

    @Autowired
    public CidadeController(CidadeService cidadeService) {
        this.cidadeService = cidadeService;
    }

    /**
     * Faz a listagem de todas as cidades cadastradas, ou se forem informados o nome da cidade e/ou o estado, lista
     * somente as cidades que tenham o nome e/ou estado informados.
     * @param nome Nome ou parte do nome da cidade que se deseja buscar.
     * @param estado Nome ou parte do nome do estado que se deseja buscar.
     * @return Página com a lista de cidades que atendam os critérios.
     */
    @RequestMapping(value = "/lista")
    ModelAndView lista(@RequestParam(value = "nome", required = false) String nome,
                       @RequestParam (value= "estado", required = false) String estado){
        List<CidadeDTO> cidades = cidadeService.buscarCidades(nome, estado);
        ModelAndView mav = new ModelAndView("cidade/lista");
        mav.addObject("cidades", cidades);
        mav.addObject("cidade", new CidadeDTO());

        return mav;
    }

    /**
     * Obtém a pontuação da cidade que foi informada como parâmetro.
     * @param nome Nome ou parte do nome da cidade que se deseja obter a pontuação.
     * @param estado Nome ou parte do nome do estado que se deseja obter a pontuação.
     * @return Json no formato {pontuacao: X}, onde X é a pontuação da cidade (Long)
     */
    @RequestMapping(value = "/pontuacao", produces = "application/json")
    @ResponseBody String pontuacao(@RequestParam(value = "nome", required = false) String nome,
                                   @RequestParam (value= "estado", required = false) String estado){
        Long pontuacao = cidadeService.buscarPontuacao(nome, estado);
        JSONWriter jsonWriter = new JSONStringer();
        String resposta = jsonWriter.object().key("pontuacao").value(pontuacao).endObject().toString();
        return resposta;
    }

}
