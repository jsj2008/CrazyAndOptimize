package com.netease.enums;

/**
 * 证件类型
 * @author hzliyong
 *
 */
public enum CredentialType {

	NULL(-1, "NULL"),
	
	IDENTIFICATION(0, "身份证");
	
	private int value;
	
	private String desc;
	
	private CredentialType(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}
	
	public int getValue() {
		return value;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public CredentialType getEnumByIntValue(int intValue) {
		for (CredentialType type : CredentialType.values()) {
			if (type.getValue() == intValue) {
				return type;
			}
		}
		return NULL;
	}
}
