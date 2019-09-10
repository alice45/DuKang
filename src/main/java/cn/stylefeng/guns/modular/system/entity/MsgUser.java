package cn.stylefeng.guns.modular.system.entity;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class MsgUser {

    public static FriendBean.ListBean rotInfo() {
        return info(ROT_INFO, FriendBean.ListBean.class);
    }

    public static FriendBean rot() {
        return FriendBean.builder().groupname("rot").id(2).list(Lists.newArrayList(rotInfo())).build();
    }

    public static MsgUser your() {
        MsgUser msgUser = new MsgUser();
        MineBean mine = info(LH_INFO, MineBean.class);
        FriendBean.ListBean your = info(YT_INFO, FriendBean.ListBean.class);
        FriendBean friendBean = FriendBean.builder().groupname("blue").id(1).list(Lists.newArrayList(your)).build();
        msgUser.setMine(mine);
        msgUser.setFriend(Lists.newArrayList(friendBean, rot()));
        return msgUser;
    }

    public static MsgUser me() {
        MsgUser msgUser = new MsgUser();
        MineBean mine = info(YT_INFO, MineBean.class);
        FriendBean.ListBean your = info(LH_INFO, FriendBean.ListBean.class);
        FriendBean friendBean = FriendBean.builder().groupname("green").id(2).list(Lists.newArrayList(your)).build();
        msgUser.setMine(mine);
        msgUser.setFriend(Lists.newArrayList(friendBean, rot()));
        return msgUser;
    }

    public static final String YT_INFO = "{\"username\":\"涛涛\",\"id\":\"1166651122677104642\",\"avatar\":\"http://p1.music.126.net/zGJ7wadxSIlSB5lhqzIteQ==/109951164189359377.jpg?param=180y180\",\"sign\":\"掉下去了\",\"status\":\"online\"}";
    public static final String LH_INFO = "{\"username\":\"慧慧\",\"id\":\"1166983602340884481\",\"avatar\":\"http://p1.music.126.net/y0_5hHJnhO_CZa9Ur1Z4UA==/109951164323533711.jpg?param=45y45\",\"sign\":\"飞起来了\",\"status\":\"online\"}";
    public static final String ROT_INFO = "{\"username\":\"rot\",\"id\":\"-1\",\"avatar\":\"http://p1.music.126.net/5MC85fXJcptaEpg0j1jPKg==/109951163079163859.jpg?param=450y300\",\"sign\":\"12345\",\"status\":\"online\"}";

    public static <T> T info(String info, Class<T> nClass) {
        try {
            return JSONObject.parseObject(info, nClass);
        }catch (Exception e) {

        }
        return null;
    }

    private MineBean mine;
    private List<FriendBean> friend;
    private List<GroupBean> group;

    @NoArgsConstructor
    @Data
    @Builder(toBuilder = true)
    @AllArgsConstructor
    public static class MineBean {
        private String username;
        private String id;
        private String status;
        private String sign;
        private String avatar;
    }

    @NoArgsConstructor
    @Data
    @Builder(toBuilder = true)
    @AllArgsConstructor
    public static class FriendBean {

        private String groupname;
        private int id;
        private List<ListBean> list;

        @NoArgsConstructor
        @Data
        @Builder(toBuilder = true)
        @AllArgsConstructor
        public static class ListBean {

            private String username;
            private String id;
            private String avatar;
            private String sign;
            private String status;
        }
    }

    @NoArgsConstructor
    @Data
    public static class GroupBean {
        private String groupname;
        private String id;
        private String avatar;
    }
}
