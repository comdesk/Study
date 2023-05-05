package org.zerock.wecart.mapper;

import org.zerock.wecart.domain.CateDTO;
import org.zerock.wecart.domain.GuAreaDTO;
import org.zerock.wecart.domain.ProductDTO;
import org.zerock.wecart.domain.RetailDTO;
import org.zerock.wecart.domain.SiAreaDTO;
import org.zerock.wecart.domain.UnitDTO;

public interface DataMapper {
	
	//1. 상품 저장
	public abstract int saveProduct(ProductDTO dto);
	
	//2. 업체 저장
	public abstract int saveRetail(RetailDTO dto);
	
	//3. 단위 저장
	public abstract int saveUnit(UnitDTO dto);
	
	//4. 카테고리 저장
	public abstract int saveCate(CateDTO dto);
	
	//5. 시도 지역 저장
	public abstract int saveSiArea(SiAreaDTO dto);
	
	//6. 시군구 지역 저장
	public abstract int saveGuArea(GuAreaDTO dto);
	
} //end interface
