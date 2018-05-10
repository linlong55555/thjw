package lbw.yht.adv.bean;

/**
 * 服务类返回消息
 * 
 * @author 裴本桢
 * 
 */
public class Message {
	// 消息码
	private int code;
	// 消息文本
	private String text;
	// 字段标志
	private String field;
	// 版本
	private String version;

	private Object items;

	public Object getItems() {
		return items;
	}

	public void setItems(Object items) {
		this.items = items;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Message() {

	}

	public Message(int code, String text) {
		this.code = code;
		this.text = text;
	}

	public Message(int code, String text, String field) {
		this.code = code;
		this.text = text;
		this.field = field;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

}
