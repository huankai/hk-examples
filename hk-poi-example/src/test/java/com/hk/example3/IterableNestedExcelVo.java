/**
 * 
 */
package com.hk.example3;

import java.util.List;

import com.hk.commons.poi.excel.annotations.NestedProperty;
import com.hk.commons.poi.excel.annotations.ReadExcel;
import com.hk.commons.poi.excel.annotations.WriteExcel;
import com.hk.example1.ExcelVo;

/**
 * @author kally
 * @date 2018年1月16日上午10:48:15
 */
public class IterableNestedExcelVo {

	@WriteExcel(index = 5, value = "id")
	@ReadExcel(start = 5)
	private String id;

	@NestedProperty
	private List<ExcelVo> userList;

	/**
	 * 
	 */
	public IterableNestedExcelVo() {

	}

	/**
	 * @param id
	 * @param userList
	 */
	public IterableNestedExcelVo(String id, List<ExcelVo> userList) {
		this.id = id;
		this.userList = userList;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the userList
	 */
	public List<ExcelVo> getUserList() {
		return userList;
	}

	/**
	 * @param userList
	 *            the userList to set
	 */
	public void setUserList(List<ExcelVo> userList) {
		this.userList = userList;
	}

}
