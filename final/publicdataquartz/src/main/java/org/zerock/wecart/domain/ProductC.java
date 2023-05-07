package org.zerock.wecart.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class ProductC {

	private String goodId;
	private String goodName;
	private String productEntpCode;
	private String goodUnitDivCode;
	private String goodBaseCnt;
	private String goodSmlclsCode;
	private String detailMean;
	private String goodTotalCnt;
	private String goodTotalDivCode;
} //end class
