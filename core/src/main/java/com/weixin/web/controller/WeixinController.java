package com.weixin.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


public class WeixinController {
	private static final String TOKEN = "weixin";
	//微信公众平台验证url是否有效使用的接口
		@RequestMapping(value="/weixin",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
		@ResponseBody
		public String initWeixinURL(HttpServletRequest request){
			String echostr = request.getParameter("echostr");
			if (checkWeixinReques(request) && echostr != null) {
				return echostr;
			}else{
				return "error";
			}
		}
		
		
		/**
		 * 根据token计算signature验证是否为weixin服务端发送的消息
		 */
		private static boolean checkWeixinReques(HttpServletRequest request){
			String signature = request.getParameter("signature");
			String timestamp = request.getParameter("timestamp");
			String nonce = request.getParameter("nonce");
			if (signature != null && timestamp != null && nonce != null ) {
				String[] strSet = new String[] { TOKEN, timestamp, nonce };
				java.util.Arrays.sort(strSet);
				String key = "";
				for (String string : strSet) {
					key = key + string;
				}
				String pwd = WeixinUtil.sha1(key);
				return pwd.equals(signature);
			}else {
				return false;
			}
		}
		

}
