package com.weixin.profile.service;

import org.springframework.stereotype.Service;

import com.weixin.util.WeixinUtil;

@Service
public class WeixinService {

	public String WeixinSha1(String key) {
		return WeixinUtil.sha1(key);
	}

}
