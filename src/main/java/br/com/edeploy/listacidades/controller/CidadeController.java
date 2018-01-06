package br.com.edeploy.listacidades.controller;

import br.com.edeploy.listacidades.domain.dto.CidadeDTO;
import br.com.edeploy.listacidades.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    String lista(Model model){

        List<CidadeDTO> cidades = cidadeService.listarTodasCidades();

        model.addAttribute("cidades", cidades);

//        System.out.println(jsonResponse.getBody().toString());
        return "cidade/lista";
    }

    @RequestMapping(value = "/busca")
    String busca(Model model,
                 @RequestParam(value = "nome", required = false) String nome,
                 @RequestParam (value= "estado", required = false) String estado){
        System.out.println("Nome: " + nome);
        System.out.println("Estado: " + estado);
        List<CidadeDTO> cidades = cidadeService.buscarCidades(nome, estado);
//
        model.addAttribute("cidades", cidades);

//        System.out.println(jsonResponse.getBody().toString());
        return "cidade/lista";
    }

}
