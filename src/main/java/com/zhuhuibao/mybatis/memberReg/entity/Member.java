package com.zhuhuibao.mybatis.memberReg.entity;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * 会员信息结构
 * @author penglong
 *
 */
@Alias("member")
public class Member implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	/**
	 * 账户名
	 */
	private String account;
	
	/**
	 * 会员手机号，登录账户
	 */
	private String mobile;
	
	/**
	 * 手机验证码
	 */
	private String mobileCheckCode;
	
	/**
	 * 会员邮箱，登录账户
	 */
	private String email;
	
	/**
	 * 邮箱图形验证码
	 */
	private String emailCheckCode;
	
	
	/**
	 * 找回密码的图片验证码
	 */
	private String checkCode;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 会员注册时间
	 */
	private String registerTime;
	
	/**
	 * 会员状态 0：未激活，1：注册成功，2：认证成功，3：vip会员
	 */
	private Integer status;
	
	/**
	 * 会员身份 1：企业，2：个人
	 */
	private String identify;
	
	private Integer workType;

	/**
	 * 会员头像
	 */
	private String headShot;

	public String getHeadShot() {
		return headShot;
	}

	public void setHeadShot(String headShot) {
		this.headShot = headShot;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMobileCheckCode() {
		return mobileCheckCode;
	}

	public void setMobileCheckCode(String mobileCheckCode) {
		this.mobileCheckCode = mobileCheckCode;
	}

	public String getEmailCheckCode() {
		return emailCheckCode;
	}

	public void setEmailCheckCode(String emailCheckCode) {
		this.emailCheckCode = emailCheckCode;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
	
	public Integer getWorkType() {
		return workType;
	}

	public void setWorkType(Integer workType) {
		this.workType = workType;
	}

	public String getIdentify() {
		return identify;
	}

	public void setIdentify(String identify) {
		this.identify = identify;
	}
	
}
