package com.weixin.profile.domain;

import lombok.Data;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
@Data
public class Article {

	@XStreamOmitField
	private int id;//数据库存储的id
	
    // 图文消息名称  
	@XStreamAlias("Title")
    private String title;  
    // 图文消息描述  
	@XStreamAlias("Description")
    private String description;  
    // 图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80，限制图片链接的域名需要与开发者填写的基本资料中的Url一致  
	@XStreamAlias("PicUrl")
    private String picUrl;  
    // 点击图文消息跳转链接  
	@XStreamAlias("Url")
    private String url;
	
	@XStreamOmitField
	private int replyId;
	
	private Reply reply;
	
	public Article() {}
	
	public Article(String title, String description, String picUrl, String url) {
		this.title = title;
		this.description = description;
		this.picUrl = picUrl;
		this.url = url;
	}
	

}
