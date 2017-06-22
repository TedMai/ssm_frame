package com.yingjun.ssm.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.yingjun.ssm.common.enums.TrueFalseEnum;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * 用户
 * @author  yingjun
 *
 */
public class User extends PersistentObject implements Serializable {
	private static final long serialVersionUID = 1106412532325860697L;

//	private String id;
	private String account;
	private String password;
	private String nickname;
	private String phone;
	private String email;
	private Integer score;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Timestamp createTime;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Timestamp lastLoginTime;
	private String lastLoginIp;
	private Integer loginCount;
	private Boolean isEnable = Boolean.valueOf(true);

	public User() {
	}

	public User(String account, String password) {
	}

//	public String getId() {
//		return id;
//	}
//	public void setId(String id) {
//		this.id = id;
//	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Timestamp lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	public Integer getLoginCount() {
		return loginCount;
	}
	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}
	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}
	public String getIsEnableStr() {
		return (isEnable != null && isEnable) ? TrueFalseEnum.TRUE.getLabel() : TrueFalseEnum.FALSE.getLabel();
	}

	@Override
	public String toString() {
		return "User{" +
				"account='" + account + '\'' +
				", password='" + password + '\'' +
				", nickname='" + nickname + '\'' +
				", phone='" + phone + '\'' +
				", email='" + email + '\'' +
				", score=" + score +
				", createTime=" + createTime +
				", lastLoginTime=" + lastLoginTime +
				", lastLoginIp='" + lastLoginIp + '\'' +
				", loginCount=" + loginCount +
				", isEnable=" + isEnable +
				'}';
	}
}
