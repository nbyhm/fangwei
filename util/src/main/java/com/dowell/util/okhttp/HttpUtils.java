package com.dowell.util.okhttp;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
* @author xucong
* @version 创建时间：2017年4月2日 下午6:12:39
* 
*/
public class HttpUtils {

	static Logger logger=LoggerFactory.getLogger(HttpUtils.class);
	
	/** 
	 * 将传递进来的参数拼接成 url
	 */
    public static String createUrlFromParams(String url, Map<String, List<String>> params) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(url);
            if (url.indexOf('&') > 0 || url.indexOf('?') > 0) {
                sb.append("&");
            } else {
                sb.append("?");
            }
            for (Map.Entry<String, List<String>> urlParams : params.entrySet()) {
                List<String> urlValues = urlParams.getValue();
                for (String value : urlValues) {
                    //对参数进行 utf-8 编码,防止头信息传中文
                    String urlValue = URLEncoder.encode(value, "UTF-8");
                    sb.append(urlParams.getKey()).append("=").append(urlValue).append("&");
                }
            }
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            logger.error("HttpUtils",e);
        }
        return url;
    }
    /** 将传递进来的参数拼接成 url */
    public static String createFromParams(Map<String, String> params) {
    	StringBuilder sb = new StringBuilder();
        try {
            
            for (Map.Entry<String, String> urlParams : params.entrySet()) {
                Object obj = urlParams.getValue();
                    //对参数进行 utf-8 编码,防止头信息传中文
                    String urlValue = URLEncoder.encode(obj.toString(), "UTF-8");
                    sb.append(urlParams.getKey()).append("=").append(urlValue).append("&");
            }
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
        }
        return sb.toString();
    }

    /** 通用的拼接请求头 */
    public static Request.Builder appendHeaders(HttpHeaders headers) {
        Request.Builder requestBuilder = new Request.Builder();
        if (headers.headersMap.isEmpty()) {
            return requestBuilder;
        }
        Headers.Builder headerBuilder = new Headers.Builder();
        try {
            for (Map.Entry<String, String> entry : headers.headersMap.entrySet()) {
                //对头信息进行 utf-8 编码,防止头信息传中文,这里暂时不编码,可能出现未知问题,如有需要自行编码
//                String headerValue = URLEncoder.encode(entry.getValue(), "UTF-8");
                headerBuilder.add(entry.getKey(), entry.getValue());
            }
        } catch (Exception e) {
        	logger.error("HttpUtils",e);
        }
        requestBuilder.headers(headerBuilder.build());
        return requestBuilder;
    }
    
    /** 生成类似表单的请求体 */
    public static RequestBody generateMultipartRequestBody(HttpParams params,boolean isMultipart) {
//    	//表单提交，没有文件
//        FormBody.Builder bodyBuilder = new FormBody.Builder();
//        for (String key : params.urlParamsMap.keySet()) {
//            List<String> urlValues = params.urlParamsMap.get(key);
//            for (String value : urlValues) {
//                bodyBuilder.add(key, value);
//            }
//        }
//        return bodyBuilder.build();

        if (params.fileParamsMap.isEmpty() && !isMultipart) {
            //表单提交，没有文件
            FormBody.Builder bodyBuilder = new FormBody.Builder();
            for (String key : params.urlParamsMap.keySet()) {
                List<String> urlValues = params.urlParamsMap.get(key);
                for (String value : urlValues) {
                    bodyBuilder.addEncoded(key, value);
                }
            }
            return bodyBuilder.build();
        } else {
            //表单提交，有文件
            MultipartBody.Builder multipartBodybuilder = new MultipartBody.Builder().setType(MultipartBody.FORM);
            //拼接键值对
            if (!params.urlParamsMap.isEmpty()) {
                for (Map.Entry<String, List<String>> entry : params.urlParamsMap.entrySet()) {
                    List<String> urlValues = entry.getValue();
                    for (String value : urlValues) {
                        multipartBodybuilder.addFormDataPart(entry.getKey(), value);
                    }
                }
            }
            //拼接文件
            for (Map.Entry<String, List<HttpParams.FileWrapper>> entry : params.fileParamsMap.entrySet()) {
                List<HttpParams.FileWrapper> fileValues = entry.getValue();
                for (HttpParams.FileWrapper fileWrapper : fileValues) {
                    RequestBody fileBody = RequestBody.create(fileWrapper.contentType, fileWrapper.file);
                    multipartBodybuilder.addFormDataPart(entry.getKey(), fileWrapper.fileName, fileBody);
                }
            }
            return multipartBodybuilder.build();
        }
    }




}
