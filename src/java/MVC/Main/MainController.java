package MVC.Main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by NESOY on 2017-02-04.
 */
@Controller
@RequestMapping(value = "/")
public class MainController {

    @RequestMapping(value = {"", "index"}, method = RequestMethod.GET)
    public String indexGet(){
        return "index";
    }

    @RequestMapping(value = {"login"}, method = RequestMethod.GET)
    public String loginGet(){
        return "login";
    }
}