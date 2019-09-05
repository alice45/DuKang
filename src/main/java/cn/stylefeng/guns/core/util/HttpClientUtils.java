package cn.stylefeng.guns.core.util;

import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.builder.HCB;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.common.SSLs;
import com.arronlong.httpclientutil.exception.HttpProcessException;
import org.apache.http.Header;
import org.apache.http.client.HttpClient;

import java.util.Map;

public class HttpClientUtils {

    private static HttpClient httpClient;

    static {
        try {
            HCB hcb = HCB.custom()
                    .timeout(1000) //超时
                    .pool(100, 10)
                    .sslpv(SSLs.SSLProtocolVersion.TLSv1_2)
                    .ssl()
                    .retry(5);
            httpClient = hcb.build();
        } catch (HttpProcessException e) {

        }
    }

    private static HttpConfig defaultConfig() {
        return HttpConfig.custom().client(httpClient).encoding("utf-8");
    }

    public static String get(String url) throws Exception{
        return  get(url, null, null);
    }

    public static String get(String url, Map<String, Object> param) throws Exception{
        return get(url, param, null);
    }

    public static String get(String url, Header[] headers) throws Exception{
        return get(url, null, headers);
    }

    public static String get(String url, Map<String, Object> param, Header[] headers) throws Exception{
        return HttpClientUtil.get(defaultConfig().url(url).map(param).headers(headers));
    }

}
