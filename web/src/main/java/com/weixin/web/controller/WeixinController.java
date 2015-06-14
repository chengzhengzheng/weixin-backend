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

import com.weixin.profile.dto.WeixinCheckRequest;
import com.weixin.profile.facade.WeixinFacade;

@Controller
public class WeixinController {
	@Autowired
	private WeixinFacade weixinFacade;
	private Logger logger = LoggerFactory.getLogger(WeixinController.class);
	
	// 微信公众平台验证url是否有效使用的接口
	@RequestMapping(value = "/weixin", method = RequestMethod.GET,produces="text/xml;charset=UTF-8")
	@ResponseBody
	public String initWeixinURL(WeixinCheckRequest request) {
		return weixinFacade.checkSignature(request);
	}

	
	@RequestMapping(value = "/weixin",method = RequestMethod.POST,produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String replayMessage(HttpServletRequest request){
		
		WeixinCheckRequest r = new WeixinCheckRequest(request);
		logger.warn(r.toString());
		
		return weixinFacade.replayMessage(request);
		
	}
}
