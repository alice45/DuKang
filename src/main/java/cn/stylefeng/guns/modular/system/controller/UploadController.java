package cn.stylefeng.guns.modular.system.controller;

import cn.stylefeng.guns.modular.system.model.response.SimpleResponse;
import cn.stylefeng.guns.modular.system.service.FileInfoService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/upload")
public class UploadController extends BaseController {

    @Autowired
    private FileInfoService fileInfoService;

    /**
    *头像上传
    *@date 2019/8/29
    *@author yangt
    */
    @RequestMapping(value = "/img",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "上传文件", notes = "上传用户头像")
    public String uploadImg(@RequestParam("file") MultipartFile multipartFile) {
        fileInfoService.uploadImg(multipartFile);
        return SimpleResponse.successJson();
    }

}
