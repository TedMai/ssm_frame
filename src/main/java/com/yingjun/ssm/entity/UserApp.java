package com.yingjun.ssm.entity;



import java.io.Serializable;

/**
 * 管理员应用映射
 * 
 * @author Joe
 */
public class UserApp extends PersistentObject implements Serializable {

	private static final long serialVersionUID = 4942358338145288018L;

	/** 应用ID */
	private String appId;
	/** 管理员ID */
	private String userId;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
