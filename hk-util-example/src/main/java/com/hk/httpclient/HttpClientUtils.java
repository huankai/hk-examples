package com.hk.httpclient;

import com.hk.commons.http.AbstractHttpExecutor;
import com.hk.commons.http.HttpExecutor;
import com.hk.commons.http.get.SimpleGetHttpExecutor;
import org.apache.http.HttpHeaders;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicHeader;

import java.io.IOException;
import java.util.Map;

/**
 * @author huangkai
 * @date 2019-3-27 9:15
 */
public class HttpClientUtils {

    public static void main(String[] args) throws IOException {
        BasicCookieStore cookieStore = new BasicCookieStore();
        cookieStore.addCookie(new BasicClientCookie("JSESSIONID", "B1E11D8F634E5D31204D4D24A5D14FDC"));
        HttpExecutor<String, Map<String, Object>> executor = new SimpleGetHttpExecutor(HttpClients
                .custom()
                .setDefaultRequestConfig(AbstractHttpExecutor.DEFAULT_REQUEST_CONFIG)
                .setDefaultCookieStore(cookieStore)
                .build());/*{
//            @Override
//            protected Header[] generateHeaders() {
//                return new Header[]{
//                        new BasicHeader(HttpHeaders.CONTENT_ENCODING, Consts.UTF_8.name()),
//                        new BasicHeader(HttpHeaders.USER_AGENT, "Android-APP")
//                };
//            }
        }*/
        ;
        String result = executor.addHeaders(new BasicHeader(HttpHeaders.USER_AGENT, "Android-APP"))
                .execute("http://192.168.1.28:8081/index/myBonusPoints.html?app=true", null);
        System.out.println("---------->" + result);
    }
}
