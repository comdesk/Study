package org.zerock.wecart.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class ProductB {
	
	List<ProductC> item;
} //end class
