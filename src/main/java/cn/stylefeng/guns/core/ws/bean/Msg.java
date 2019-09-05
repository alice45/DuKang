package cn.stylefeng.guns.core.ws.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Msg {


    /**
     * mine : {"username":"纸飞机","avatar":"/system/previewAvatar","id":"100000","mine":true,"content":"123"}
     * to : {"username":"山根万理奈","id":"100001","avatar":"http://p1.music.126.net/zGJ7wadxSIlSB5lhqzIteQ==/109951164189359377.jpg?param=180y180","sign":"嘻嘻","status":"online","name":"山根万理奈","type":"friend"}
     */

    private MineBean mine;
    private ToBean to;

    @NoArgsConstructor
    @Data
    public static class MineBean {
        /**
         * username : 纸飞机
         * avatar : /system/previewAvatar
         * id : 100000
         * mine : true
         * content : 123
         */

        private String username;
        private String avatar;
        private String id;
        private boolean mine;
        private String content;
    }

    @NoArgsConstructor
    @Data
    public static class ToBean {
        /**
         * username : 山根万理奈
         * id : 100001
         * avatar : http://p1.music.126.net/zGJ7wadxSIlSB5lhqzIteQ==/109951164189359377.jpg?param=180y180
         * sign : 嘻嘻
         * status : online
         * name : 山根万理奈
         * type : friend
         */

        private String username;
        private String id;
        private String avatar;
        private String sign;
        private String status;
        private String name;
        private String type;
    }


}
