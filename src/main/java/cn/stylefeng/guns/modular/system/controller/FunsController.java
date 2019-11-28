package cn.stylefeng.guns.modular.system.controller;

import cn.stylefeng.guns.core.common.annotion.Permission;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.util.DESEncrypt;
import cn.stylefeng.roses.core.base.controller.BaseController;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;


@Controller
@RequestMapping("/funs")
public class FunsController extends BaseController {

    private static final String PREFIX = "/modular/music/";
//
//    @Autowired
//    private MarkService markService;

    @RequestMapping("/music")
    public String music() {
        return PREFIX + "music.html";
    }

    @RequestMapping("/diary")
    public String diary() {
        return PREFIX + "diary.html";
    }

//    @RequestMapping("/init/mark")
//    @ResponseBody
//    public List<ZTreeNode> tree() {
//        List<ZTreeNode> tree = markService.tree();
//        tree.add(ZTreeNode.createParent());
//        return tree;
//    }

//    @RequestMapping("/decrypt")
//    @ResponseBody
//    public String decrypt(String content) {
//        if (Strings.isEmpty(content)) {
//            return "空的~解密啥哪";
//        }
//        return DESEncrypt.decrypt(content);
//    }


    @RequestMapping("/decrypt")
    public ModelAndView decrypt() {
        ModelAndView mv = new ModelAndView("input.html");
        return mv;
    }

    @RequestMapping("/decryptKey")
    @ResponseBody
    public String decryptKey(String content) {
        if (Strings.isEmpty(content)) {
            return "空的~解密啥哪";
        }
        return Optional.ofNullable(DESEncrypt.decrypt(content)).orElse("啥都没有，是不是整错了");
    }


    @RequestMapping(value = "/list")
    @Permission
    @ResponseBody
    public Object list(@RequestParam(value = "condition", required = false) String condition,
                       @RequestParam(value = "deptId", required = false) Long deptId) {
//        Page<Map<String, Object>> list = this.null.list(condition, deptId);
//        Page<Map<String, Object>> wrap = new DeptWrapper(list).wrap();
        return LayuiPageFactory.createPageInfo(null);
    }
}
