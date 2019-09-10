package cn.stylefeng.guns.core.ws.parse;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Set;

public class ParseCreator {

    private ThreadLocal<Set<ResParse>> parseSet = new ThreadLocal<>();

    private ParseCreator() {}

    public static ParseCreator me() {
        return new ParseCreator();
    }

    public ParseCreator register(ResParse parse) {
        if (parseSet.get() == null) {
            parseSet.set(new HashSet<>());
        }
        parseSet.get().add(parse);
        return this;
    }

    public String parse(JSONObject jsonObject) {
        if (parseSet.get() == null) {
            return "";
        }
        for (ResParse parse : parseSet.get()) {
            String res = parse.parse(jsonObject);
            if (StringUtils.isNotEmpty(res)) {
                return res;
            }
        }
        return "";
    }

}
