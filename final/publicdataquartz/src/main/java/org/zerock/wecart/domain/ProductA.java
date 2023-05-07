package org.zerock.wecart.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class ProductA {
	
	private ProductB result;
	private String resultCode;
	private String resultMsg;
} //end class
