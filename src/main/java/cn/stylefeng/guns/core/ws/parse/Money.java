package cn.stylefeng.guns.core.ws.parse;

import com.alibaba.fastjson.JSONObject;

public class Money implements ResParse{

    public static final String NAME = "财神爷灵签";

    @Override
    public String parse(JSONObject jsonObject) {
        if (hasKey(jsonObject, TYPE)) {
            if (jsonObject.getString(TYPE).equals(NAME)) {
                return parseM(jsonObject);
            }
        }
        return null;
    }

}
