package com.zhuhuibao.web;

import java.io.IOException;

import com.zhuhuibao.common.JsonResult;
import com.zhuhuibao.common.MsgCodeConstant;
import com.zhuhuibao.security.EncodeUtil;
import com.zhuhuibao.utils.JsonUtils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录
 * @author caijl@20160303
 */
@Controller
public class AuthenticationController {
    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    @RequestMapping(value = "/rest/web/authc", method = RequestMethod.GET)
    @ResponseBody
    public void isLogin(HttpServletRequest req,HttpServletResponse response) throws JsonGenerationException, JsonMappingException, IOException{
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode(200);
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession(false);
        if(null == session){
        	jsonResult.setMsgCode(0);
            jsonResult.setMessage("you are rejected!");
            
        }else{
        	Object principal = session.getAttribute("member");
        	if(null == principal){
            	jsonResult.setMsgCode(0);
                jsonResult.setMessage("you are rejected!");
        	}else{
            	jsonResult.setMsgCode(1);
                jsonResult.setMessage("welcome you!");
        	}
        }
        response.setContentType("application/json;charset=utf-8");
      	response.getWriter().write(JsonUtils.getJsonStringFromObj(jsonResult));
      	log.debug("caijl:/rest/web/authc is called,msgcode=["+jsonResult.getMsgCode()+"],Message=["+jsonResult.getMessage()+"].");
    }
    
}
