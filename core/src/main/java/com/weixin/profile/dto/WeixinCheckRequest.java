package com.weixin.profile.dto;

import javax.servlet.http.HttpServletRequest;




import lombok.Data;

@Data
public class WeixinCheckRequest {
	
	
	public WeixinCheckRequest(HttpServletRequest request) {
		this.signature = request.getParameter("signature");
		this.timestamp = request.getParameter("timestamp");
		this.nonce = request.getParameter("nonce");
		this.echostr = request.getParameter("echostr");
	}

	private String signature;//微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
	
	private String timestamp;//时间戳
	
	private String nonce;//随机数
	
	private String echostr;//随机字符串

	public WeixinCheckRequest() {
		// TODO Auto-generated constructor stub
	}
}
