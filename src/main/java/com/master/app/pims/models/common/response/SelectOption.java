package com.master.app.pims.models.common.response;

import java.io.Serializable;
import java.util.Date;

public class SelectOption implements Serializable, Comparable<SelectOption> {
	
	private static final long serialVersionUID = 7991273101659965728L;
	
	private Integer intKey;
	private String strKey;
	private Long longKey;
	private String value;

	public SelectOption(){
		super();
	}

	public SelectOption(Integer intKey, String value) {
		super();
		this.intKey = intKey;
		this.value = value;
	}

	public SelectOption(String strKey, String value) {
		super();
		this.strKey = strKey;
		this.value = value;
	}

	public SelectOption(Long longKey, String value) {
		super();
		this.longKey = longKey;
		this.value = value;
	}

	public SelectOption(Long longKey, String value, Integer intKey, Date fromDate, Date toDate) {
		super();
		this.longKey = longKey;
		this.intKey = intKey;
		this.value = value;
	}

	public Integer getIntKey() {
		return intKey;
	}

	public void setIntKey(Integer intKey) {
		this.intKey = intKey;
	}

	public String getStrKey() {
		return strKey;
	}

	public void setStrKey(String strKey) {
		this.strKey = strKey;
	}

	public Long getLongKey() {
		return longKey;
	}

	public void setLongKey(Long longKey) {
		this.longKey = longKey;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public boolean equals(Object selectOption) {
		if (this == selectOption) {
		    return true;
		}
		if (selectOption instanceof SelectOption) {
			if(this.getLongKey()!=null && ((SelectOption)selectOption).getLongKey()!=null){
				if(this.getLongKey() == ((SelectOption)selectOption).getLongKey() || this.getLongKey().equals(((SelectOption)selectOption).getLongKey())){
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public int compareTo(SelectOption o) {
		if(o.getLongKey() != null){
			return this.longKey.compareTo(o.getLongKey());
		}else{
			return this.strKey.compareTo(o.getStrKey());
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SelectOption [intKey=");
		builder.append(intKey);
		builder.append(", strKey=");
		builder.append(strKey);
		builder.append(", longKey=");
		builder.append(longKey);
		builder.append(", value=");
		builder.append(value);
		builder.append("]");
		return builder.toString();
	}
	
	

}
