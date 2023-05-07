package org.zerock.wecart.domain;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class StandardA {
	private StandardB result;
	private String resultCode;
	private String resultMsg;
} //end class
