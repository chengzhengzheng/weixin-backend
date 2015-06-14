package com.weixin.profile.service;

import org.springframework.stereotype.Service;

import com.weixin.profile.domain.Message;
import com.weixin.profile.domain.Reply;
import com.weixin.profile.dto.WeixinCheckRequest;
import com.weixin.util.WeixinConstants;
import com.weixin.util.WeixinUtil;

@Service
public class WeixinService {

	public String WeixinSha1(String key) {
		return WeixinUtil.sha1(key);
	}
	
	public void save(Message message) {

		
	}

	public boolean checkWeixinRequest(WeixinCheckRequest request) {
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
			String pwd = WeixinSha1(key);
			return pwd.equals(signature);
		} else {
			return false;
		}
	}

}
