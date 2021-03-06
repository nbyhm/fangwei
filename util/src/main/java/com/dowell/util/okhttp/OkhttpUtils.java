package com.dowell.util.okhttp;


import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

/**
 * 网络请求工具类
 * 
 * @author xucong
 *
 */
public class OkhttpUtils {
	
	private OkHttpClient mOkHttpClient;
	private OkHttpClient.Builder okHttpClientBuilder;
    private int mRetryCount = 0;     

	private OkhttpUtils() {
		okHttpClientBuilder = new OkHttpClient.Builder()
//				.addInterceptor(new HttpLoggingInterceptor())
				.connectTimeout(100, TimeUnit.SECONDS)
				.writeTimeout(100, TimeUnit.SECONDS)
				.readTimeout(100, TimeUnit.SECONDS);
//		okHttpClientBuilder.cookieJar(new CookieJar() {
//			private final HashMap<String, List<Cookie>> cookieStore = new HashMap<String, List<Cookie>>();
//
//			@Override
//			public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
//				cookieStore.put(url.host(), cookies);
//			}
//
//			@Override
//			public List<Cookie> loadForRequest(HttpUrl url) {
//				List<Cookie> cookies = cookieStore.get(url.host());
//				return cookies != null ? cookies : new ArrayList<Cookie>();
//			}
//		});
	}

	public static OkhttpUtils getInstance() {
		return ClientHolder.okHttpUtils;
	}

	private static class ClientHolder {
		static OkhttpUtils okHttpUtils = new OkhttpUtils();
	}

	/**
	 * 获取OkHttpClient对象
	 */
	public OkHttpClient getOkHttpClient() {
		if (null == mOkHttpClient) {
			mOkHttpClient = okHttpClientBuilder.build();
		}
		return mOkHttpClient;
	}

	/**
	 * 获取OkHttpClientBuilder对象
	 */
	public OkHttpClient.Builder getOkHttpClientBuilder() {
		return okHttpClientBuilder;
	}
	
	 /** 超时重试次数 */
    public OkhttpUtils setRetryCount(int retryCount) {
        if (retryCount < 0) {
            throw new IllegalArgumentException("retryCount must > 0");
        }
        mRetryCount = retryCount;
        return this;
    }

    /** 超时重试次数 */
    public int getRetryCount() {
        return mRetryCount;
    }
    /** get请求 */
    public GetRequest get(String url) {
        return new GetRequest(url);
    }

    /** post请求 */
    public PostRequest post(String url) {
        return new PostRequest(url);
    }

}
