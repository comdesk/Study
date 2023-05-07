package org.zerock.wecart.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class RetailB {

	@JsonProperty("iros.openapi.service.vo.entpInfoVO")
	private List<RetailC> entpInfoVO;
} //end class
