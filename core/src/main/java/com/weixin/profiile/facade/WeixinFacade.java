package com.weixin.profiile.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weixin.profiile.dto.WeixinRequest;
import com.weixin.profiile.service.WeixinService;
import com.weixin.util.WeixinConstants;
import com.weixin.util.WeixinUtil;
@Service
public class WeixinFacade {
	@Autowired
	private WeixinService weixinService;
	
	public   boolean checkWeixinRequest(WeixinRequest request){
		String signature = request.getSignature();
		String timestamp = request.getTimestamp();
		String nonce = request.getNonce();

		if (signature != null && timestamp != null && nonce != null ) {
			String[] strSet = new String[] { WeixinConstants.TOKEN, timestamp, nonce };
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
