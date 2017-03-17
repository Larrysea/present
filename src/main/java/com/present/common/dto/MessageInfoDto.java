package com.present.common.dto;

public class MessageInfoDto
{
	/**
	 * 返回状态
	 */
    private int code;
    
	/**
	 * 返回消息
	 */
    private String msg;
    
    public MessageInfoDto()
    {
    }
    
    public MessageInfoDto(int pCode, String pMsg)
    {
		this.code = pCode;
		this.msg = pMsg;
    }

	/**
	 * getter method
	 * @return Returns the code.
	 */
	public int getCode() {
		return code;
	}

	/**
	 * setter method
	 * @param code The code to set.
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * getter method
	 * @return Returns the msg.
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * setter method
	 * @param msg The msg to set.
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	/**
	 * setter method
	 * @param code The code to set.
	 */
	public void setMessageInfo(MessageInfoDto messageInfoDto) {
		this.code = messageInfoDto.getCode();
		this.msg = messageInfoDto.getMsg();
	}
}
