package com.weixin.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weixin.profile.dto.Message;
import com.weixin.profile.dto.WeixinCheckRequest;
import com.weixin.profile.facade.WeixinFacade;
import com.weixin.util.WeixinUtil;

@Controller
public class WeixinController {
	@Autowired
	private WeixinFacade weixinFacade;
	private Logger logger = LoggerFactory.getLogger(WeixinController.class);
	
	// 微信公众平台验证url是否有效使用的接口
	@RequestMapping(value = "/weixin", method = RequestMethod.GET,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String initWeixinURL(WeixinCheckRequest request) {
		String echostr = request.getEchostr();
		if (weixinFacade.checkWeixinRequest(request) && echostr != null)
			return echostr;
		return "error";
	}

	
	@RequestMapping(value = "/weixin",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String replayMessage(HttpServletRequest request){
		WeixinCheckRequest check = new WeixinCheckRequest(request);
		
		if(weixinFacade.checkWeixinRequest(check)){
			Map<String, String> requestMap = WeixinUtil.parseXml(request);

			Message message = WeixinUtil.mapToMessage(requestMap);

			logger.warn("请求参数中的constent:"+message.getContent());
			
			String reply = weixinFacade.replay(message);
			
			logger.warn("返回参数中："+reply);
			return weixinFacade.replay(message);
		}
		return "error";
		
	}
}
