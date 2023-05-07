package org.zerock.wecart.domain;

import lombok.Data;

@Data
public class ProductDTO {

	private String goods_name;
	private Integer capacity;
	private String capacity_unit_id;
	private Integer category_id;
} //end class
