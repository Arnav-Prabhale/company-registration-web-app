package com.web;

public class CodeValue {
	private int id;
	private String codeSet;
	private String codeLabel;
	private String value;
	private String parentId;
	
	public CodeValue(String codeSet, String codeLabel, String value, String parentId) {
		super();
		this.codeSet = codeSet;
		this.codeLabel = codeLabel;
		this.value = value;
		this.parentId = parentId;
	}

	public CodeValue(int id, String codeSet, String codeLabel, String value, String parentId) {
		super();
		this.id = id;
		this.codeSet = codeSet;
		this.codeLabel = codeLabel;
		this.value = value;
		this.parentId = parentId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodeSet() {
		return codeSet;
	}

	public void setCodeSet(String codeSet) {
		this.codeSet = codeSet;
	}

	public String getCodeLabel() {
		return codeLabel;
	}

	public void setCodeLabel(String codeLabel) {
		this.codeLabel = codeLabel;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Override
	public String toString() {
		return "CodeValue [id=" + id + ", codeSet=" + codeSet + ", codeLabel=" + codeLabel + ", value=" + value
				+ ", parentId=" + parentId + "]";
	}

}
