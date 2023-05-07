package org.zerock.wecart.domain;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class RetailA {

	private RetailB result;
	private String resultCode;
	private String resultMsg;
} //end class
