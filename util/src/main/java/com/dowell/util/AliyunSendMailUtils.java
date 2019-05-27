package com.dowell.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * @author: NanBo
 * @description: 阿里云发送邮件工具
 * @date: 2019-05-24
 */
public class AliyunSendMailUtils {

    /**
     * 阿里云发送邮和OSS文件上传使用同一个accessKeyId和accessKeySecret
     */
    private static String accessKeyId = "XXXXXXXXXXX";
    private static String accessKeySecret = "XXXXXXXXXXXXXXXXXXXXXX";


    /**
     * 单一发信接口
     * @param accountName   管理控制台中配置的发信地址
     * @param fromAlias     发信人昵称
     * @param tagName       标签
     * @param toAddress     目标地址，多个 email 地址可以用逗号分隔，最多100个地址
     * @param subject       邮件主题，建议填写
     * @param context       邮件 html 正文
     * @return
     */
    public static Boolean singleSendTextMail(String accountName,String fromAlias,String tagName,String toAddress,String subject,String context){
        // 如果是除杭州region外的其它region（如新加坡、澳洲Region），需要将下面的"cn-hangzhou"替换为"ap-southeast-1"、或"ap-southeast-2"。
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        //使用https加密连接
        //profile.getHttpClientConfig().setProtocolType(com.aliyuncs.http.ProtocolType.HTTPS);
        // 如果是除杭州region外的其它region（如新加坡region）， 需要做如下处理
        //try {
        //DefaultProfile.addEndpoint("dm.ap-southeast-1.aliyuncs.com", "ap-southeast-1", "Dm",  "dm.ap-southeast-1.aliyuncs.com");
        //} catch (ClientException e) {
        //e.printStackTrace();
        //}
        IAcsClient client = new DefaultAcsClient(profile);
        SingleSendMailRequest request = new SingleSendMailRequest();
        try {
            //request.setVersion("2017-06-22");// 如果是除杭州region外的其它region（如新加坡region）,必须指定为2017-06-22
            request.setAccountName(accountName);
            request.setFromAlias(fromAlias);
            request.setAddressType(1);
            request.setTagName(tagName);
            request.setReplyToAddress(true);
            request.setToAddress(toAddress);
            //可以给多个收件人发送邮件，收件人之间用逗号分开，批量发信建议使用BatchSendMailRequest方式
            //request.setToAddress("邮箱1,邮箱2");
            request.setSubject(subject);
            request.setTextBody(context);
            SingleSendMailResponse httpResponse = client.getAcsResponse(request);

            System.out.println("发送邮件成功！");
        } catch (ServerException e) {
            e.printStackTrace();
        }
        catch (ClientException e) {
            e.printStackTrace();
        }

        return true;
    }


    public static void main(String[] args) {
        AliyunSendMailUtils.singleSendTextMail("test@service.aliyun.com","test",
                "test","1163213732@qq.com","邮件主题","这是测试数据");
    }
}
