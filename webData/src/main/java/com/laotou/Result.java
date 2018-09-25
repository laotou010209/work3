package com.laotou;

/**
 * 处理结果的工具类
 * @author dell
 *
 */
public class Result {
	/**
	 * 结果状态
	 */
	private boolean state;
	/**
	 * 该结果的对应的提示信息
	 */
	private String message;
	/**
	 * 获取结果数据
	 * @return
	 */
	private Object data;
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
