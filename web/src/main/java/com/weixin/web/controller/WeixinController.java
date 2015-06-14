package com.weixin.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weixin.profiile.dto.WeixinRequest;
import com.weixin.profiile.facade.WeixinFacade;

@Controller
public class WeixinController {
	@Autowired
	private WeixinFacade weixinFacade;
	
	// 微信公众平台验证url是否有效使用的接口
	@RequestMapping(value = "/weixin", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String initWeixinURL(WeixinRequest request) {
		String echostr = request.getEchostr();
		if (weixinFacade.checkWeixinRequest(request) && echostr != null)
			return echostr;
		return "error";
	}

}
