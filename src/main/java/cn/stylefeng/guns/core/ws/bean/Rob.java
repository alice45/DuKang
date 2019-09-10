package cn.stylefeng.guns.core.ws.bean;


import cn.stylefeng.guns.core.util.HttpClientUtils;
import cn.stylefeng.guns.core.ws.parse.Guanyin;
import cn.stylefeng.guns.core.ws.parse.Joke;
import cn.stylefeng.guns.core.ws.parse.Money;
import cn.stylefeng.guns.core.ws.parse.ParseCreator;
import com.alibaba.fastjson.JSONObject;
import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Rob {


    private static final String KEY = "3017c6a4f6175671271503d931f1c505";

    private static final String SECRET = "ravhlzcud7lg";


    public static String getMsg(String msg) {
        String url = "http://i.itpk.cn/api.php?api_key=3017c6a4f6175671271503d931f1c505&api_secret=ravhlzcud7lg&question=" + Optional.ofNullable(msg).orElse("");
        HttpConfig httpConfig = HttpConfig.custom().url(url);
        try {
            String res = HttpClientUtil.get(httpConfig);
            return parse(res);
        }catch (Exception e) {}
        return "fail";
    }

    private static String parse(String res) {
        try {
            JSONObject resObject = JSONObject.parseObject(res);
            return ParseCreator.me().register(new Joke()).register(new Guanyin()).register(new Money()).parse(resObject);
        }catch (Exception e) {}
        return res;
    }


    private static Map<String, Object> obtainParam(String msg) {
        HashMap<String, Object> param = Maps.newHashMap();
        param.put("api_key", KEY);
        param.put("api_secret", SECRET);
        param.put("question", msg);
        return param;
    }

}
