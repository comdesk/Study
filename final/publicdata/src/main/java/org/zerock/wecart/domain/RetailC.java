package org.zerock.wecart.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@Data
public class RetailC {

	private String entpId;
	private String entpName;
	private String entpTypeCode;
	private String entpAreaCode;
	private String areaDetailCode;
	private String entpTelno;
	private String postNo;
	private String plmkAddrBasic;
	private String plmkAddrDetail;
	private String roadAddrBasic;
	private String roadAddrDetail;
	private String xMapCoord;
	private String yMapCoord;
	
} //end class
