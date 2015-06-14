package com.weixin.profile.dto;

import java.util.Date;

import lombok.Data;

import com.thoughtworks.xstream.annotations.XStreamAlias;



@Data
public class Reply {
	public static final String TEXT = "text";
	
	
	public static final String WELCOME_CONTENT = "welcome to the student commiunication:";

	
	// 开发者微信号  
	@XStreamAlias("ToUserName")
    private String toUserName;  
    // 发送方帐号（一个OpenID）  
	@XStreamAlias("FromUserName")
    private String fromUserName;  
    // 消息创建时间 
	@XStreamAlias("CreateTime")
    private long createTime;  
    // 消息类型（text/music/news）
	@XStreamAlias("MsgType")
    private String msgType;  
    
    //回复的消息内容,长度不超过2048字节 (文本消息专有)
	@XStreamAlias("Content")
    private String content;
    
  
	
    

	

}
