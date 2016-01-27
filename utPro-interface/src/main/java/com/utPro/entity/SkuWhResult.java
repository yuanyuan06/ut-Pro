/**
 * 
 */
package com.utPro.entity;

import java.util.Date;

public class SkuWhResult extends BaseEntity {

	private static final long serialVersionUID = -3062218995604957365L;
	private Long id;
	
	private String skuCode;
	
	private Integer qty;
	
	private String whCode;
	
	private Date processDate;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public String getWhCode() {
		return whCode;
	}

	public void setWhCode(String whCode) {
		this.whCode = whCode;
	}

	public Date getProcessDate() {
		return processDate;
	}

	public void setProcessDate(Date processDate) {
		this.processDate = processDate;
	}
	
}
