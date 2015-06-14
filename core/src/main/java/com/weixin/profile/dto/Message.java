package com.weixin.profile.dto;

import java.util.Date;

import lombok.Data;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

@Data
public class Message {
	public static final String TEXT = "text";
	
	@XStreamOmitField
	private int id;//数据库存储的id
	
	// 开发者微信号  
    private String toUserName;  
    // 发送方帐号（一个OpenID）  
    private String fromUserName;  
    // 消息创建时间    
    private long createTime;  
    // 消息类型（text/image/location/link）  
    private String msgType;  
    // 消息id，64位整型  
    private String msgId;
    
    // 消息内容 (文本消息专有)
    private String content;
        
   
    
    
}
