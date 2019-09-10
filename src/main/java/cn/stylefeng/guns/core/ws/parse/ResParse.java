package cn.stylefeng.guns.core.ws.parse;

import com.alibaba.fastjson.JSONObject;

import java.util.Optional;

public interface ResParse {

    String TYPE = "type";

    String GOOD = "haohua";

    String QIANYU = "qianyu";

    String SHIYI = "shiyi";

    String JIEQIAN = "jieqian";

    String ZHUSHI = "zhushi";

    String JIESHUO = "jieshuo";

    String JIEGUO = "jieguo";

    String SENTENCE = "%s\r\n%s\r\n%s\r\n%s";

    String SENTENCE_MONEY = "%s\r\n%s\r\n%s\r\n%s\n%s";

    String parse(JSONObject jsonObject);

    default boolean hasKey(JSONObject object, String key){
        return Optional.ofNullable(object).map(x -> x.containsKey(key)).orElse(false);
    }

    default String parseQ(JSONObject object) {
        String good = object.getString(GOOD);
        String qianyu = object.getString(QIANYU);
        String shiyi = object.getString(SHIYI);
        String jieqian = object.getString(JIEQIAN);
        return String.format(SENTENCE, good, qianyu, shiyi, jieqian);
    }

    default String parseM(JSONObject object) {
        String qianyu = object.getString(QIANYU);
        String zhushi = object.getString(ZHUSHI);
        String jieqian = object.getString(JIEQIAN);
        String jieshuo = object.getString(JIESHUO);
        String jieguo = object.getString(JIEGUO);
        return String.format(SENTENCE_MONEY, qianyu, zhushi, jieqian, jieshuo, jieguo);
    }

}
