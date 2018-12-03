package com.huang.user.model;

public class User {
	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 密码
	 */
	private String passwd;
	/**
	 * 性别 1 男 2 女 3 未知
	 */
	private String sex;
	/**
	 * 生日 yyyymmdd
	 */
	private String birthday;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	@Override
	public String toString() {
		return "TUser [userId=" + userId + ", userName=" + userName
				+ ", passwd=" + passwd + ", sex=" + sex + ", birthday="
				+ birthday + "]";
	}
	
}