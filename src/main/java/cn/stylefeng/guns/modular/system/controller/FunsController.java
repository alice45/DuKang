package cn.stylefeng.guns.modular.system.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/funs")
public class FunsController extends BaseController {

    private static final String PREFIX = "/modular/music/";

    @RequestMapping("/music")
    public String music() {
        return PREFIX + "music.html";
    }

}
