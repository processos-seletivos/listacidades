package br.com.edeploy.listacidades.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by bruno on 1/7/18.
 */
@Controller
@RequestMapping(path = "/")
public class IndexController {

    @RequestMapping(path = "/", produces = "text/html")
    ModelAndView index() {
        return new ModelAndView("redirect:/cidade/lista");
    }
}
