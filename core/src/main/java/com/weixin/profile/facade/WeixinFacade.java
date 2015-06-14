package com.weixin.profile.facade;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weixin.profile.dto.Message;
import com.weixin.profile.dto.Reply;
import com.weixin.profile.dto.WeixinRequest;
import com.weixin.profile.service.WeixinService;
import com.weixin.util.WeixinConstants;
import com.weixin.util.WeixinUtil;

@Service
public class WeixinFacade {
	@Autowired
	private WeixinService weixinService;

	public boolean checkWeixinRequest(WeixinRequest request) {
		String signature = request.getSignature();
		String timestamp = request.getTimestamp();
		String nonce = request.getNonce();

		if (signature != null && timestamp != null && nonce != null) {
			String[] strSet = new String[] { WeixinConstants.TOKEN, timestamp,
					nonce };
			java.util.Arrays.sort(strSet);
			String key = "";
			for (String string : strSet) {
				key = key + string;
			}
			String pwd = weixinService.WeixinSha1(key);
			return pwd.equals(signature);
		} else {
			return false;
		}
	}

	public String replay(Message message) {
		String replyContent = Reply.WELCOME_CONTENT;
		// 拼装回复消息
		Reply reply = new Reply();
		reply.setToUserName(message.getFromUserName());
		reply.setFromUserName(message.getToUserName());
		reply.setCreateTime(message.getCreateTime());
		reply.setMsgType(Reply.TEXT);
		reply.setContent(replyContent);

		// 将回复消息序列化为xml形式
		String back = WeixinUtil.replyToXml(reply);
		System.out.println(back);
		return back;
	}

}