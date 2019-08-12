package com.hk.httpclient;

import com.hk.commons.http.get.HttpGetHttpExecutor;

/**
 * @author kevin
 * @date 2019-6-25 13:35
 */
public class Psvm {

    public static void main(String[] args) {
        HttpGetHttpExecutor httpExecutor = new HttpGetHttpExecutor();
//        httpExecutor.setAsync(true);// 设置为异步请求
//        httpExecutor.setFutureCallback(new FutureCallback<HttpResponse>() { // 设置 异步 回调
//
//            /**
//             * 请求完成是回掉
//             * @param result result
//             */
//            @Override
//            public void completed(HttpResponse result) {
//                try {
//                    System.out.println("---->" + AbstractHttpExecutor.UTF8_HANDLER.handleResponse(result));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            /**
//             * 请求失败时回调
//             * @param ex ex
//             */
//            @Override
//            public void failed(Exception ex) {
//                System.out.println(ex.getMessage());
//            }
//
//            /**
//             * 请求取消时回调
//             */
//            @Override
//            public void cancelled() {
//
//            }
//        });
        System.out.println(httpExecutor.execute("https://www.baidu.com", null));
    }
}
