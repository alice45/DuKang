package cn.stylefeng.guns.modular.system.controller;

import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.core.shiro.ShiroUser;
import cn.stylefeng.guns.core.shiro.service.UserAuthService;
import cn.stylefeng.guns.core.shiro.service.impl.UserAuthServiceServiceImpl;
import cn.stylefeng.guns.core.util.CacheUtil;
import cn.stylefeng.guns.modular.system.entity.User;
import cn.stylefeng.guns.modular.system.model.response.SimpleResponse;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.SpringContextHolder;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import net.sf.ehcache.CacheManager;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

import static cn.stylefeng.guns.core.common.constant.cache.CacheKey.LOGIN_USER;
import static cn.stylefeng.guns.core.common.exception.BizExceptionEnum.USER_ERROR_LOGIN_OUT;

@Controller
@RequestMapping("/cache")
public class CacheController extends BaseController {

    @RequestMapping("count")
    @ResponseBody
    public String userCount() {
        CacheManager cacheManager = SpringContextHolder.getBean(CacheManager.class);
        return SimpleResponse.successJson(Optional.ofNullable(cacheManager.getCache(LOGIN_USER)).map(x -> x.getSize()).orElse(0));
    }


    @RequestMapping("clear")
    @ResponseBody
    public String clearLoginCache() {
        SimpleAuthenticationInfo authenticationInfo = UserAuthServiceServiceImpl.me().info();
        Optional.ofNullable(authenticationInfo).orElseThrow(() -> new ServiceException(USER_ERROR_LOGIN_OUT));
        String userKey = String.valueOf(authenticationInfo.getCredentials());
        CacheUtil.remove(LOGIN_USER, userKey);
        return SimpleResponse.successJson();
    }


}
