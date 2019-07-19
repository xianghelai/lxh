package com.lai.fasthttpclient.test;

import io.itit.itf.okhttp.FastHttpClient;
import io.itit.itf.okhttp.Response;
import io.itit.itf.okhttp.callback.Callback;
import io.itit.itf.okhttp.callback.DownloadFileCallback;
import io.itit.itf.okhttp.callback.StringCallback;
import io.itit.itf.okhttp.interceptor.DownloadFileInterceptor;
import io.itit.itf.okhttp.util.FileUtil;
import okhttp3.Call;
import org.json.JSONObject;
import org.junit.Test;
import java.io.File;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

/**
 * @Author: bbdw
 * @Date: 2019/7/11 19:02
 * @Version 1.0
 */
public class FastHttpclientTest {

    //同步：当执行某个操作时，只有当其完全执行结束，才回去执行下面的操作，缺点：如果遇到耗时操作，会比较卡
    //异步：多个任务同时执行
    /**
     * 同步get请求,访问百度首页,自动处理https单向认证
     */
    @Test
    public void get() throws Exception {
        String string = FastHttpClient.get().url("https://www.baidu.com").build().execute().string();
        System.out.println("sndhjashd");
        System.out.println(string);
        String string2 = FastHttpClient.get().url("http://localhost:8081/alipaytest/test")
                .addParams("username","lailais")
                .addParams("pass","72384")
                .build().execute().string();
        System.out.println(string2);
    }

    /**
     * 异步get请求
     * @throws Exception
     */
    @Test
    public void getsync() throws Exception {

        Callback callback = new StringCallback() {
            @Override
            public void onFailure(Call call, Exception e, int i) {
                System.out.println(e.getMessage());
            }

            @Override
            public void onSuccess(Call call, String response, int id) {
                super.onSuccess(call, response, id);
                System.out.println(response);
            }
        };
         FastHttpClient.get().url("https://www.baidu.com").build().executeAsync(callback);
    }
    /**
     * 同步post请求
     */
    @Test
    public void post()throws Exception{
        String s = "username=hsdaj&pass=3493";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username","lasd");
        jsonObject.put("pass","217328");
        Response res = FastHttpClient.post().url("http://localhost:8081/alipaytest/test").addParams("username","lasdh").addParams("pass","7238").build().execute();
        System.out.println(res.string());
    }

    /**
     *异步POST请求
     * @throws InterruptedException
     */
    @Test
    public void testPostAsync() throws Exception{
        FastHttpClient.post().url("http://localhost:8081/alipaytest/test").
                addParams("username", "lai").
                addParams("pass", "627").
                build().
                executeAsync(new Callback() {
                    @Override
                    public void onFailure(Call call, Exception e, int id) {
                    }
                    @Override
                    public void onResponse(Call call, Response response, int id) {
                        try {
                            System.out.println(response.string());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        //System.exit(0);
                    }
                });
        Thread.sleep(2000);
    }

    /**
     * 下载文件
     * @throws InterruptedException
     */
    @Test
    public void testDownloadFile() throws InterruptedException{
        FastHttpClient.newBuilder().
                addNetworkInterceptor(new DownloadFileInterceptor(){
                    @Override
                    public void updateProgress(long downloadLenth, long totalLength, boolean isFinish) {
                        //下载进度
                        System.out.println(isFinish);
                    }
                }).
                build().
                get().
                url("http://e.hiphotos.baidu.com/image/pic/item/faedab64034f78f0b31a05a671310a55b3191c55.jpg").
                build().
                executeAsync(new DownloadFileCallback("D:\\tmp\\1.jpg") {
                    @Override
                    public void onFailure(Call call, Exception e, int id) {
                        logger.error(e.getMessage(),e);
                    }
                    @Override
                    public void onSuccess(Call call, File file, int id) {
                        logger.info("filePath:"+file.getAbsolutePath());
                    }
                    @Override
                    public void onSuccess(Call call, InputStream fileStream, int id) {
                        logger.info("onSuccessWithInputStream");
                    }
                });
        Thread.sleep(3000);
    }

    /**
     * 上传文件
     * @throws Exception
     */
    @Test
    public void testUploadFile() throws Exception{
        byte[] imageContent= FileUtil.getBytes("D:\\tmp\\1.jpg");
        Response response = FastHttpClient.newBuilder().
                connectTimeout(10, TimeUnit.SECONDS).
                build().
                post().url("http://localhost:8081/alipaytest/upload").
                addFile("file1", "a.txt", "123").
                addFile("file2", "b.jpg", imageContent).
                build().
                execute();
    }

    /**
     * 添加请求头
     * @throws Exception
     */
    @Test
    public void testAddHeader() throws Exception{
        String url="http://www.baidu.com";
        Response response=FastHttpClient.
                get().
                addHeader("Referer","http://news.baidu.com/").
                addHeader("cookie", "uin=test;skey=111111;").
                url(url).
                build().
                execute();
        System.out.println(response.string());
    }
}
