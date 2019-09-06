package cn.stylefeng.guns.core.ws.bean;

import com.alibaba.fastjson.JSONObject;
import com.arronlong.httpclientutil.common.HttpHeader;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@Slf4j
@ServerEndpoint(value = "/websocket") //接受websocket请求路径
@Component
public class MyWebSocket {

    //保存所有在线socket连接
    private static Map<String, MyWebSocket> webSocketMap = new LinkedHashMap<>();

    //记录当前在线数目
    private static int count=0;

    //当前连接（每个websocket连入都会创建一个MyWebSocket实例
    private Session session;


    private static ConcurrentHashMap<String, ConcurrentLinkedQueue<MsgReply>> MSG_QUEUE = new ConcurrentHashMap<>();


    //处理连接建立
    @OnOpen
    public void onOpen(Session session){
        this.session=session;
        String id = StringUtils.substringBetween(session.getUserPrincipal().getName(), "id=", ",");
        webSocketMap.put(id, this);
        //消费滞留消息
        addCount();
        log.info("新的连接加入：{}",session.getId());
    }

    private void consumerMsg(String id) {
        if (MSG_QUEUE.containsKey(id)) {
            ConcurrentLinkedQueue<MsgReply> queue = MSG_QUEUE.get(id);
            queue.stream().forEach(msg -> {
                try {
                    this.sendMessage(JSONObject.toJSONString(msg));
                } catch (IOException e) {}
            });
            MSG_QUEUE.remove(id);
        }
    }

    //接受消息
    @OnMessage
    public void onMessage(String message,Session session){
        log.info("收到客户端{}消息：{}",session.getId(),message);
        try{
            Msg msg = JSONObject.parseObject(message, Msg.class);
            String targetId = msg.getTo().getId();
            MsgReply msgReply = MsgReply.reply(msg);
            MyWebSocket socket = webSocketMap.get(targetId);
            if (socket == null) {
                stashMsg(msgReply);
            }else {
                socket.sendMessage(JSONObject.toJSONString(msgReply));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void stashMsg(MsgReply reply) {
        String targetId = reply.getTargetId();
        MSG_QUEUE.computeIfPresent(targetId, (k, v) -> {
            v.add(reply);
            return v;
        });
        if (!MSG_QUEUE.containsKey(targetId)) {
            ConcurrentLinkedQueue<MsgReply> queue = new ConcurrentLinkedQueue<>();
            queue.add(reply);
            MSG_QUEUE.put(targetId, queue);
        }
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
