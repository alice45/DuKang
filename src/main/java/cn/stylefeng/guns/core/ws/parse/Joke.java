package cn.stylefeng.guns.core.ws.parse;

import com.alibaba.fastjson.JSONObject;

import java.util.Optional;

public class Joke implements ResParse{

    public static final String TITLE = "title";

    public static final String CONTENT = "content";

    @Override
    public String parse(JSONObject jsonObject) {
        if (hasKey(jsonObject, TITLE)) {
            return Optional.ofNullable(jsonObject.getString(CONTENT)).orElse("~~");
        }
        return null;
    }

}
