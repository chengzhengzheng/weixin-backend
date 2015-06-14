package com.weixin.profile.facade;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weixin.profile.domain.Message;
import com.weixin.profile.domain.Reply;
import com.weixin.profile.dto.WeixinCheckRequest;
import com.weixin.profile.service.WeixinService;
import com.weixin.util.WeixinUtil;

@Service
public class WeixinFacade {
	private Logger logger = LoggerFactory.getLogger(WeixinFacade.class);
	@Autowired
	private WeixinService weixinService;

	
	public String replayMessage(HttpServletRequest request) {
		WeixinCheckRequest check = new WeixinCheckRequest(request);
		if (weixinService.checkWeixinRequest(check)) {
			// 将请求参数XML格式解析成map格式
			Map<String, String> requestMap = WeixinUtil.parseXml(request);
			logger.warn("start war:");
			for(String str : requestMap.keySet()){
				logger.warn(str+":"+requestMap.get(str));
			}
			logger.warn("end war");
			logger.warn("get the client data 客户端消息:"+request.getParameter("Content"));
			// 赋值给Message
			Message message = WeixinUtil.mapToMessage(requestMap);
			
			// 拼装回复消息
			Reply reply = new Reply();
			reply.setToUserName(message.getFromUserName());
			reply.setFromUserName(message.getToUserName());
			reply.setCreateTime(message.getCreateTime());
			reply.setMsgType(Reply.TEXT);
			
			reply.setContent(message.getContent() + Reply.TEXT);

			//保存消息信息
			weixinService.save(message);
			// 将回复消息序列化为xml形式
			return WeixinUtil.replyToXml(reply);
		}
		return "error";
	}

	
	
	public String checkSignature(WeixinCheckRequest request) {
		String echostr = request.getEchostr();
		if (weixinService.checkWeixinRequest(request) && echostr != null)
			return echostr;
		return "error";
	}

}
