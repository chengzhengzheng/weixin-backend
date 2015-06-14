package com.weixin.profile.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;


import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;



@Data
public class Reply {
	public static final String TEXT = "text";
	public static final String MUSIC = "music";
	public static final String NEWS = "news";
	
	@XStreamOmitField
	private int id;//数据库存储id
	
	// 开发者微信号  
	@XStreamAlias("ToUserName")
    private String toUserName;  
    // 发送方帐号（一个OpenID）  
	@XStreamAlias("FromUserName")
    private String fromUserName;  
    // 消息创建时间 
	@XStreamAlias("CreateTime")
    private Date createTime;  
    // 消息类型（text/music/news）
	@XStreamAlias("MsgType")
    private String msgType;  
    
    //回复的消息内容,长度不超过2048字节 (文本消息专有)
	@XStreamAlias("Content")
    private String content;
    
    //音乐链接 (音乐消息专有)
	@XStreamAlias("MusicUrl")
    private String musicUrl;
    //高质量音乐链接，WIFI环境优先使用该链接播放音乐 (音乐消息专有)
	@XStreamAlias("HQMusicUrl")
    private String hQMusicUrl;
    
    //图文消息个数，限制为10条以内  (图文消息专有) 
	@XStreamAlias("ArticleCount")
    private int articleCount; 
    
    //多条图文消息信息，默认第一个item为大图
	@XStreamAlias("Articles")
    private List<Article> articles;
    
  
	
    

	

}
