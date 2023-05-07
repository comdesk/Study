package org.zerock.wecart.mapper;

import org.zerock.wecart.domain.CateDTO;
import org.zerock.wecart.domain.GuAreaDTO;
import org.zerock.wecart.domain.ProductDTO;
import org.zerock.wecart.domain.RetailDTO;
import org.zerock.wecart.domain.SiAreaDTO;
import org.zerock.wecart.domain.UnitDTO;

public interface DataMapper {
	
	//1. 상품 업데이트
	public abstract int saveProduct(ProductDTO dto);
	
	//2. 업체 업데이트
	public abstract int saveRetail(RetailDTO dto);
	
	//3. 상품 평균가
	public abstract int saveAvgPrice();
	
} //end interface
