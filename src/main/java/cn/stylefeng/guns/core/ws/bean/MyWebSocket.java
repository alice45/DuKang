package cn.stylefeng.guns.core.ws.bean;

import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.core.shiro.ShiroUser;
import cn.stylefeng.guns.modular.system.entity.User;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.arronlong.httpclientutil.common.HttpHeader;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@ServerEndpoint(value = "/websocket") //接受websocket请求路径
@Component
public class MyWebSocket {

//    public static final String URL = "http://i.xiaoi.com/robot/webrobot";

//    public static final List<String> cookies = new ArrayList<>();

    //保存所有在线socket连接
    private static Map<String, MyWebSocket> webSocketMap = new LinkedHashMap<>();

    //记录当前在线数目
    private static int count=0;

    //当前连接（每个websocket连入都会创建一个MyWebSocket实例
    private Session session;


//    static {
//        cookies.add("cnonce=808116;sig=0c3021aa5552fe597bb55448b40ad2a90d2dead5;XISESSIONID=hlbnd1oiwar01dfje825gavcn;nonce=273765;hibext_instdsigdip2=1;");
//        cookies.add("sig=0c3021aa5552fe597bb55448b40ad2a90d2dead5");
//        cookies.add("XISESSIONID=hlbnd1oiwar01dfje825gavcn");
//        cookies.add("nonce=273765");
//        cookies.add("hibext_instdsigdip2=1");
//    }

    //处理连接建立
    @OnOpen
    public void onOpen(Session session){
        this.session=session;
        String id = StringUtils.substringBetween(session.getUserPrincipal().getName(), "id=", ",");
        webSocketMap.put(id, this);
        addCount();
        log.info("新的连接加入：{}",session.getId());
    }

    //接受消息
    @OnMessage
    public void onMessage(String message,Session session){
        log.info("收到客户端{}消息：{}",session.getId(),message);
        try{
            Msg msg = JSONObject.parseObject(message, Msg.class);
            String targetId = msg.getTo().getId();
            MyWebSocket socket = webSocketMap.get(targetId);
            socket.sendMessage(JSONObject.toJSONString(MsgReply.reply(msg)));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private String getReply(String message) {
//        Map<String, Object> param = new HashMap<>(8);
//        String data = "{'sessionId':'09e2aca4d0a541f88eecc77c03a8b393','robotId':'webbot','userId':'462d49d3742745bb98f7538c42f9f874','body':{'content':'" + message + "'}," + "'type':'txt'}";
//        param.put("callback", "__webrobot_processMsg");
//        param.put("data", data);
//        param.put("ts", "1529917589648");
//        try {
//            String s = HttpClientUtils.get(URL, param, obtainHeader());
//        } catch (Exception e) {
//            log.info("调用回复接口错误", e);
//        }
        return "嗷哦~";
    }

    private Header[] obtainHeader() {
        return HttpHeader.custom()
                .other("cnonce", "808116")
                .other("sig", "0c3021aa5552fe597bb55448b40ad2a90d2dead5")
                .other("XISESSIONID", "hlbnd1oiwar01dfje825gavcn")
                .other("nonce", "273765")
                .other("hibext_instdsigdip2", "1")
                .build();
    }

    //处理错误
    @OnError
    public void onError(Throwable error,Session session){
        log.info("发生错误{},{}",session.getId(),error.getMessage());
    }

    //处理连接关闭
    @OnClose
    public void onClose(){
        String id = StringUtils.substringBetween(session.getUserPrincipal().getName(), "id=", ",");
        webSocketMap.remove(id);
        reduceCount();
        log.info("连接关闭:{}",this.session.getId());
    }

    //群发消息

    //发送消息
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    //广播消息
    public static void broadcast(){
        MyWebSocket.webSocketMap.forEach((k,v)->{
            try{
                v.sendMessage("这是一条测试广播");
            }catch (Exception e){
            }
        });
    }

    //获取在线连接数目
    public static int getCount(){
        return count;
    }

    //操作count，使用synchronized确保线程安全
    public static synchronized void addCount(){
        MyWebSocket.count++;
    }

    public static synchronized void reduceCount(){
        MyWebSocket.count--;
    }
}
