package cn.stylefeng.guns.core.ws.parse;

import com.alibaba.fastjson.JSONObject;

public class Guanyin implements ResParse{

    public static final String NAME = "观音灵签";

    @Override
    public String parse(JSONObject jsonObject) {
        if (hasKey(jsonObject, TYPE)) {
            if (jsonObject.getString(TYPE).equals(NAME)) {
                return parseQ(jsonObject);
            }
        }
        return null;
    }

}
