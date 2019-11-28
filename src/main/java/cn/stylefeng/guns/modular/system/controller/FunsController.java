package cn.stylefeng.guns.modular.system.controller;

import cn.stylefeng.guns.core.common.annotion.Permission;
import cn.stylefeng.guns.core.common.node.ZTreeNode;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.util.DESEncrypt;
import cn.stylefeng.guns.modular.system.service.MarkService;
import cn.stylefeng.guns.modular.system.warpper.DeptWrapper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/funs")
public class FunsController extends BaseController {

    private static final String PREFIX = "/modular/music/";

    @Autowired
    private MarkService markService;

    @RequestMapping("/music")
    public String music() {
        return PREFIX + "music.html";
    }

    @RequestMapping("/diary")
    public String diary() {
        return PREFIX + "diary.html";
    }

    @RequestMapping("/init/mark")
    @ResponseBody
    public List<ZTreeNode> tree() {
        List<ZTreeNode> tree = markService.tree();
        tree.add(ZTreeNode.createParent());
        return tree;
    }

    @RequestMapping("/decrypt")
    @ResponseBody
    public String decrypt(String content) {
        if (Strings.isEmpty(content)) {
            return "空的~解密啥哪";
        }
        return DESEncrypt.decrypt(content);
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
