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

    @RequestMapping(value = "/lista")
    ModelAndView lista(@RequestParam(value = "nome", required = false) String nome,
                       @RequestParam (value= "estado", required = false) String estado){
        List<CidadeDTO> cidades = cidadeService.buscarCidades(nome, estado);
        ModelAndView mav = new ModelAndView("cidade/lista");
        mav.addObject("cidades", cidades);
        mav.addObject("cidade", new CidadeDTO());

        return mav;
    }

    @RequestMapping(value = "/pontuacao", produces = "application/json")
    @ResponseBody String pontuacao(@RequestParam(value = "nome", required = false) String nome,
                                   @RequestParam (value= "estado", required = false) String estado){
        Long pontuacao = cidadeService.buscarPontuacao(nome, estado);
        JSONWriter jsonWriter = new JSONStringer();
        String resposta = jsonWriter.object().key("pontuacao").value(pontuacao).endObject().toString();
        System.out.println("Resposta " + resposta);
        return resposta;
    }

    /*@RequestMapping(value = "/busca"*//*, method = RequestMethod.POST*//*)
    String busca(Model model,
                 @RequestParam(value = "nome", required = false) String nome,
                 @RequestParam (value= "estado", required = false) String estado){
        System.out.println("Nome: " + nome);
        System.out.println("Estado: " + estado);
        List<CidadeDTO> cidades = cidadeService.buscarCidades(nome, estado);
        model.addAttribute("cidades", cidades);
        model.addAttribute("cidade", new CidadeDTO(nome, estado));

        return "redirect:/cidade/lista";
    }*/

}
