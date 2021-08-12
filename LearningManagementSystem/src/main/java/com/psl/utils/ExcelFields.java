package com.psl.utils;

/*
 * POJO TO STORE EXCEL FIELDS DEFINITION
 */
public class ExcelFields {
	private String fieldName;
	private String fieldValue;
	private int fieldType;
	public ExcelFields() {
		super();
	}
	public ExcelFields(String fieldName, String fieldValue, int fieldType) {
		super();
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
		this.fieldType = fieldType;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getFieldValue() {
		return fieldValue;
	}
	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}
	public int getFieldType() {
		return fieldType;
	}
	public void setFieldType(int fieldType) {
		this.fieldType = fieldType;
	}
	
}
