package com.movision.utils.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;


/**
 * 淘宝阿里大于短信继集成类
 */
public class SDKSendTaoBaoSMS {

    private static Logger log = LoggerFactory.getLogger(SDKSendTaoBaoSMS.class);

    private static final String TAOBAO_CLIENT_URL = "http://gw.api.taobao.com/router/rest";
    private static final String APPKEY = "23696382";
    private static final String SECRET = "27717070d06e98f5e4bd982293b0d77f";


    /**
     * 发送短信
     *
     * @param mobile       短信接收号码 (支持单个或多个手机号码) 英文逗号分隔 最多200个
     * @param params       短信模板参数
     * @param templateCode 短信模板编码
     * @return 成功失败 {true|false}
     */
    public static Boolean sendSMS(String mobile, String params, String templateCode){
        TaobaoClient client = new DefaultTaobaoClient(TAOBAO_CLIENT_URL, APPKEY, SECRET);
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        try {
            req.setExtend("123456");
            req.setSmsType("normal");
            req.setSmsFreeSignName("美番");   //申请的签名名称
            req.setSmsParamString(params);
            req.setRecNum(mobile);  //接受的手机号码
            req.setSmsTemplateCode(templateCode);   //模板code
            AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
            log.info("send sms response:" + rsp.getBody());
            return rsp.getResult().getSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("发送短信异常");
            return false;
        }

    }


    /**
     * 发送 登录app短信验证码
     * @param mobile
     * @param checkCode
     * @throws ApiException
     */
    /*public static void snedAppLoginSms(String mobile, String checkCode) throws ApiException {
        log.info("send app login sms mobile =  " + mobile + " checkcode = " + checkCode);
        TaobaoClient client = new DefaultTaobaoClient(TAOBAO_CLIENT_URL, APPKEY, SECRET);
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setExtend("123456");
        req.setSmsType("normal");
        req.setSmsFreeSignName("美番");
        req.setSmsParamString("{\"code\":\"" + checkCode + "\",\"product\":\"美番\"}");
        req.setRecNum(mobile);
        req.setSmsTemplateCode("SMS_54600056");
        AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
    }*/


    /**
     * * 用户注册时发送的短信验证码    支持，一次最多可提交200个手机号码；（温馨提示：手机号以英文逗号分开）
     *
     * @param mobile    手机号
     * @param checkCode 验证码
     * @param time      过期时间
     * @throws ApiException
     *//*
    public static void sendRegisterSMS(String mobile, String checkCode, String time) throws ApiException {
        log.info("send register sms mobile =  " + mobile + " checkcode = " + checkCode);
        TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "23361295", "752b6bcb411e07baf34e11e0b4ddb767");
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setExtend("123456");
        req.setSmsType("normal");
        req.setSmsFreeSignName("movision");
        req.setSmsParamString("{\"code\":\"" + checkCode + "\",\"time\":\"" + time + "\"}");
        req.setRecNum(mobile);
        req.setSmsTemplateCode("SMS_8440019");
        AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
    }

    *//**
     * 找回密码时发送的短信验证码  一次最多可提交200个手机号码；（温馨提示：手机号以英文逗号分开）
     *
     * @param mobile
     * @param checkCode
     * @param time
     * @throws ApiException
     *//*
    public static void sendFindPwdSMS(String mobile, String checkCode, String time) throws ApiException {
        log.info("send find pwd sms mobile =  " + mobile + " checkcode = " + checkCode);
        TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "23361295", "752b6bcb411e07baf34e11e0b4ddb767");
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setExtend("123456");
        req.setSmsType("normal");
        req.setSmsFreeSignName("movision");
        req.setSmsParamString("{\"code\":\"" + checkCode + "\",\"time\":\"" + time + "\"}");
        req.setRecNum(mobile);
        req.setSmsTemplateCode("SMS_8440021");
        AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
    }

    *//**
     * 修改绑定的手机短信验证码 一次最多可提交200个手机号码；（温馨提示：手机号以英文逗号分开）
     *
     * @param mobile
     * @param checkCode
     * @param time
     * @throws ApiException
     *//*
    public static void sendModifyBindMobileSMS(String mobile, String checkCode, String time) throws ApiException {
        log.info("send modify bind mobile sms mobile =  " + mobile + " checkcode = " + checkCode);
        TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "23361295", "752b6bcb411e07baf34e11e0b4ddb767");
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setExtend("123456");
        req.setSmsType("normal");
        req.setSmsFreeSignName("movision");
        req.setSmsParamString("{\"code\":\"" + checkCode + "\",\"time\":\"" + time + "\"}");
        req.setRecNum(mobile);
        req.setSmsTemplateCode("SMS_8440020");
        AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
    }


    *//**
     * * 申请专家支持时发送的短信验证码    支持，一次最多可提交200个手机号码；（温馨提示：手机号以英文逗号分开）
     *
     * @param mobile    手机号
     * @param checkCode 验证码
     * @param time      过期时间
     * @throws ApiException
     *//*
    public static void sendExpertSupportSMS(String mobile, String checkCode, String time) throws ApiException {
        log.info("send expertSupport sms mobile =  " + mobile + " checkcode = " + checkCode);
        TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "23361295", "752b6bcb411e07baf34e11e0b4ddb767");
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setExtend("123456");
        req.setSmsType("normal");
        req.setSmsFreeSignName("movision");
        req.setSmsParamString("{\"code\":\"" + checkCode + "\",\"time\":\"" + time + "\"}");
        req.setRecNum(mobile);
        req.setSmsTemplateCode("SMS_8440019");
        AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
    }

    *//**
     * * 专家培训课程下单发送的短信验证码    支持，一次最多可提交200个手机号码；（温馨提示：手机号以英文逗号分开）
     *
     * @param mobile    手机号
     * @param checkCode 验证码
     * @param time      过期时间
     * @throws ApiException
     *//*
    public static void sendExpertTrainSMS(String mobile, String checkCode, String time) throws ApiException {
        log.info("send expertTrain sms mobile =  " + mobile + " checkcode = " + checkCode);
        TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "23361295", "752b6bcb411e07baf34e11e0b4ddb767");
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setExtend("123456");
        req.setSmsType("normal");
        req.setSmsFreeSignName("movision");
        req.setSmsParamString("{\"code\":\"" + checkCode + "\",\"time\":\"" + time + "\"}");
        req.setRecNum(mobile);
        req.setSmsTemplateCode("SMS_8440019");
        AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
    }

    *//**
     * * 开课申请发送验证码   支持，一次最多可提交200个手机号码；（温馨提示：手机号以英文逗号分开）
     * @param mobile  手机号
     * @param checkCode 验证码
     * @param time  过期时间
     * @throws ApiException
     *//*
    public static void sendExpertClassSMS(String mobile,String checkCode,String time) throws ApiException
    {
        log.info("send expertClass sms mobile =  "+mobile+" checkcode = "+checkCode);
        TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "23361295", "752b6bcb411e07baf34e11e0b4ddb767");
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setExtend("123456");
        req.setSmsType("normal");
        req.setSmsFreeSignName("movision");
        req.setSmsParamString("{\"code\":\""+checkCode+"\",\"time\":\""+time+"\"}");
        req.setRecNum(mobile);
        req.setSmsTemplateCode("SMS_8440019");
        AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
    }*/

}
