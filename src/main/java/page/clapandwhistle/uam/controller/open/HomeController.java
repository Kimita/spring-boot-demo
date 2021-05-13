package page.clapandwhistle.uam.controller.open;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import page.clapandwhistle.uam.config.ProfileTest.EnvStringBean;

@Controller
public class HomeController {

    @Autowired
    EnvStringBean sampleBean;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("bean", this.sampleBean);
        return "index";
    }

    @RequestMapping("/e")
    public String err() {
        return "e";
    }
}
