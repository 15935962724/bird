package com.bird.util;

import java.io.Serializable;

/**
 * 系统设置
 * 
 * @author HaoKangHu Team
 * @version 1.0
 */
public class Setting implements Serializable {

	private static final long serialVersionUID = -1478999889661796840L;

	/** 缓存名称 */
	public static final String CACHE_NAME = "setting";

	/** 缓存Key */
	public static final Integer CACHE_KEY = 0;

	/** 分隔符 */
	private static final String SEPARATOR = ",";

//	/** 网站名称 */
//	private String siteName;
//
//	/** 网站网址 */
//	private String siteUrl;
//
//	/** logo */
//	private String logo;
//
//	/** 热门搜索 */
//	private String hotSearch;
//
//	/** 联系地址 */
//	private String address;
//
//	/** 联系电话 */
//	private String phone;
//
//	/** 邮政编码 */
//	private String zipCode;
//
//	/** E-mail */
//	private String email;
//
//	/** 备案编号 */
//	private String certtext;

	/*短信接口*/
	private String shortMessageUrl;

	/*开发者主账号（ACCOUNT SID）。开发者账号唯一标识符*/
	private String accountSid;

	/*子账户ID，具体查看用户中心 短信配置中值，验证码通知和营销不一样*/
	private String accountId;

	/*短信接口秘钥*/
	private String authToken;


	/*会员注册赠送积分*/
	private String memberRegisterPoint;

	/*购买软件赠送积分*/
	private String purchaseProductPoint;

//	public String getSiteName() {
//		return siteName;
//	}
//
//	public void setSiteName(String siteName) {
//		this.siteName = siteName;
//	}
//
//	public String getSiteUrl() {
//		return siteUrl;
//	}
//
//	public void setSiteUrl(String siteUrl) {
//		this.siteUrl = siteUrl;
//	}
//
//	public String getLogo() {
//		return logo;
//	}
//
//	public void setLogo(String logo) {
//		this.logo = logo;
//	}
//
//	public String getHotSearch() {
//		return hotSearch;
//	}
//
//	public void setHotSearch(String hotSearch) {
//		this.hotSearch = hotSearch;
//	}
//
//	public String getAddress() {
//		return address;
//	}
//
//	public void setAddress(String address) {
//		this.address = address;
//	}
//
//	public String getPhone() {
//		return phone;
//	}
//
//	public void setPhone(String phone) {
//		this.phone = phone;
//	}
//
//	public String getZipCode() {
//		return zipCode;
//	}
//
//	public void setZipCode(String zipCode) {
//		this.zipCode = zipCode;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getCerttext() {
//		return certtext;
//	}
//
//	public void setCerttext(String certtext) {
//		this.certtext = certtext;
//	}

	public String getShortMessageUrl() {
		return shortMessageUrl;
	}

	public void setShortMessageUrl(String shortMessageUrl) {
		this.shortMessageUrl = shortMessageUrl;
	}

	public String getAccountSid() {
		return accountSid;
	}

	public void setAccountSid(String accountSid) {
		this.accountSid = accountSid;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getMemberRegisterPoint() {
		return memberRegisterPoint;
	}

	public void setMemberRegisterPoint(String memberRegisterPoint) {
		this.memberRegisterPoint = memberRegisterPoint;
	}

	public String getPurchaseProductPoint() {
		return purchaseProductPoint;
	}

	public void setPurchaseProductPoint(String purchaseProductPoint) {
		this.purchaseProductPoint = purchaseProductPoint;
	}


	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
}