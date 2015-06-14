package com.weixin.profile.domain;

import java.util.Date;

import lombok.Data;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

@Data
public class Message {
	public static final String TEXT = "text";
	public static final String IMAGE = "image";
	public static final String LOCATION = "location";
	public static final String LINK = "link";
	public static final String EVENT = "event";
	
	@XStreamOmitField
	private int id;//数据库存储的id
	
	// 开发者微信号  
    private String toUserName;  
    // 发送方帐号（一个OpenID）  
    private String fromUserName;  
    // 消息创建时间    
    private Date createTime;  
    // 消息类型（text/image/location/link）  
    private String msgType;  
    // 消息id，64位整型  
    private String msgId;
    
    // 消息内容 (文本消息专有)
    private String content;
    
    //图片链接 (图片消息专有)
    private String picUrl;
    
    // 消息标题 (链接消息专有) 
    private String title;  
    // 消息描述 (链接消息专有) 
    private String description;  
    // 消息链接  (链接消息专有)
    private String url;
    
    
    //地理位置纬度 Location_X(地理位置专有)
    private String locationX;
    //地理位置经度 Location_Y(地理位置专有)
    private String locationY;
    // 地图缩放大小  (地理位置专有)
    private String scale;  
    // 地理位置信息  (地理位置专有)
    private String label;
    
    
    //事件类型，subscribe(订阅)、unsubscribe(取消订阅)、CLICK(自定义菜单点击事件) （事件推送专有）
    private String event;
    //事件KEY值，与自定义菜单接口中KEY值对应（事件推送专有）
    private String eventKey;
    
        
   
    
    
}
