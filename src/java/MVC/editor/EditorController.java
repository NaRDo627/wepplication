package MVC.editor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by NESOY on 2017-02-04.
 */
@Controller
@RequestMapping(value = "/editor")
public class EditorController {

    @RequestMapping(value = {"/image_editor"}, method = RequestMethod.GET)
    public String editorImageEditorGet(){
        return "/editor/image_editor";
    }

    @RequestMapping(value = {"/video_editor"}, method = RequestMethod.GET)
    public String editorVideoEditorGet(){
        return "/editor/video_editor";
    }
}