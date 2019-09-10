package cn.stylefeng.guns.core.ws.bean;

import cn.stylefeng.guns.modular.system.entity.MsgUser;
import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class MsgReply {

    private String targetId;
    private String name;
    private String sourceId;
    private String content;
    private String avatar;


    public static MsgReply reply(Msg msg) {
        if (msg == null) {
            return new MsgReply();
        }
        String targetId = msg.getTo().getId();
        String name = msg.getMine().getUsername();
        String sourceId = msg.getMine().getId();
        String content = msg.getMine().getContent();
        String avatar = msg.getMine().getAvatar();
        return MsgReply.builder().
                targetId(targetId).
                name(name).
                sourceId(sourceId).
                content(content).
                avatar(avatar).
                build();
    }

    public static MsgReply rotReply(String content) {
        MsgUser.FriendBean.ListBean rotInfo = MsgUser.rotInfo();
        return MsgReply.builder().
                targetId(String.valueOf(rotInfo.getId())).
                name(rotInfo.getUsername()).
                sourceId(String.valueOf(rotInfo.getId())).
                content(content).
                avatar(rotInfo.getAvatar()).
                build();
    }
}
