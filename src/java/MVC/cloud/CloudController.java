package MVC.cloud;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by NESOY on 2017-02-04.
 */
@Controller
@RequestMapping(value = "/cloud")
public class CloudController {

    @RequestMapping(value = {"/cloud"}, method = RequestMethod.GET)
    public String cloudCloudGet(){
        return "/cloud/cloud";
    }
}