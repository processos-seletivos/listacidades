package br.com.edeploy.listacidades;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by bruno on 05/01/18.
 */
@Controller
@RequestMapping(value = "cidade")
public class CidadeController {

    @RequestMapping(value = "/lista")
    String lista(){
        return "cidade/lista";
    }

}
