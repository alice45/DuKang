package cn.stylefeng.guns.modular.system.model.response;

import cn.stylefeng.guns.core.common.constant.Code;
import com.alibaba.fastjson.JSON;
import lombok.Data;

@Data
public class SimpleResponse<T> {

    private Integer code;

    private String msg;

    private T data;

    public static String successJson() {
        return JSON.toJSONString(success());
    }

    public static String successJson(Object data) {
        return JSON.toJSONString(success(data));
    }

    public static SimpleResponse success() {
        return success(null);
    }

    public static SimpleResponse success(Object data) {
        SimpleResponse response = new SimpleResponse();
        response.setData(data);
        response.setCode(Code.SUCCESS);
        response.setMsg(Code.SUCCESS_MSG);
        return response;
    }

}
