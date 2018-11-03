package MVC.sample;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by NESOY on 2017-02-04.
 */
@Controller // 이해를 돕기위한 샘플 컨트롤러, 프로토타입 게시전에 파기 예정
@RequestMapping(value = "/sample")
public class SampleController {

    @RequestMapping(value = {"/dashboard"}, method = RequestMethod.GET)
    public String sampleDashboardGet(){
        return "/sample/dashboard";
    }

    @RequestMapping(value = {"/grid"}, method = RequestMethod.GET)
    public String sampleGridGet(){
        return "/sample/grid";
    }

    @RequestMapping(value = {"/forms"}, method = RequestMethod.GET)
    public String sampleFormsGet(){
        return "/sample/forms";
    }

    @RequestMapping(value = {"/tables"}, method = RequestMethod.GET)
    public String sampleTablesGet(){
        return "/sample/tables";
    }

    @RequestMapping(value = {"/blank"}, method = RequestMethod.GET)
    public String sampleBlankGet(){
        return "/sample/blank";
    }

    @RequestMapping(value = {"/buttons"}, method = RequestMethod.GET)
    public String sampleButtonsGet(){
        return "/sample/buttons";
    }

    @RequestMapping(value = {"/flot"}, method = RequestMethod.GET)
    public String sampleFlotGet(){
        return "/sample/flot";
    }

    @RequestMapping(value = {"/morris"}, method = RequestMethod.GET)
    public String sampleMorrisGet(){
        return "/sample/morris";
    }

    @RequestMapping(value = {"/notifications"}, method = RequestMethod.GET)
    public String sampleNotificationsGet(){
        return "/sample/notifications";
    }

    @RequestMapping(value = {"/panels-wells"}, method = RequestMethod.GET)
    public String samplePanelsWellsGet(){
        return "/sample/panels-wells";
    }

    @RequestMapping(value = {"/typography"}, method = RequestMethod.GET)
    public String sampleTypographyGet(){
        return "/sample/typography";
    }

    @RequestMapping(value = {"/icons"}, method = RequestMethod.GET)
    public String sampleIconsGet(){
        return "/sample/icons";
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String sampleLoginGet(){
        return "/sample/login";
    }
}