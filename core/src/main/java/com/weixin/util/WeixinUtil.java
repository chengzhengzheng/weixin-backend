package com.weixin.util;

import java.io.InputStream;
import java.io.Writer;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.weixin.profile.dto.Message;
import com.weixin.profile.dto.Reply;



public class WeixinUtil {

	/**
	 * 将回复消息对象转换成xml字符串
	 * 
	 * @param reply
	 *            回复消息对象
	 * @return 返回符合微信接口的xml字符串
	 */
	private static XStream xstream = new XStream(new XppDriver() {
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				// 对所有xml节点的转换都增加CDATA标记
				boolean cdata = true;

				@SuppressWarnings("unchecked")
				public void startNode(String name, Class clazz) {
					super.startNode(name, clazz);
				}

				protected void writeText(QuickWriter writer, String text) {
					if (cdata) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}
				}
			};
		}
	});

	public static String replyToXml(Reply reply) {
		xstream.alias("xml", reply.getClass());
		xstream.autodetectAnnotations(true); 
		return xstream.toXML(reply);
	}

	public static void main(String[] args) {
		Reply reply = new Reply();
		reply.setContent(Reply.WELCOME_CONTENT);
		System.out.println(replyToXml(reply));
	}

	public static Message mapToMessage(Map<String, String> map) {
		if (map == null)
			return null;


		Message message = new Message();

		message.setToUserName(map.get("ToUserName"));

		message.setFromUserName(map.get("FromUserName"));


		message.setMsgType(map.get("MsgType"));

		message.setMsgId(map.get("MsgId"));

		message.setContent(map.get("Content"));
		
		return message;
	}

	/**
	 * 解析request中的xml 并将数据存储到一个Map中返回
	 * 
	 * @param request
	 */
	public static Map<String, String> parseXml(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			InputStream inputStream = request.getInputStream();
			
			SAXReader reader = new SAXReader();
			
			Document document = reader.read(inputStream);
			
			Element root = document.getRootElement();
			
			@SuppressWarnings("unchecked")
			List<Element> elementList = root.elements();
			
			for (Element e : elementList)
				map.put(e.getName(), e.getText());
			
			inputStream.close();
			
			inputStream = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	public static String sha1(String key) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA1");
			md.update(key.getBytes());
			String pwd = new BigInteger(1, md.digest()).toString(16);
			return pwd;
		} catch (Exception e) {
			e.printStackTrace();
			return key;
		}
	}

}
