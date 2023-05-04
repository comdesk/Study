package org.zerock.wecart.mapper;

import org.zerock.wecart.domain.GuAreaDTO;
import org.zerock.wecart.domain.SiAreaDTO;

public interface DataMapper {
	
	//1. 상품 저장
	public abstract int saveProduct();
	
	//2. 업체 저장
	public abstract int saveRetail();
	
	//3. 단위 저장
	public abstract int saveUnit();
	
	//4. 카테고리 저장
	public abstract int saveCate();
	
	//5. 시도 지역 저장
	public abstract int saveSiArea(SiAreaDTO dto);
	
	//6. 시군구 지역 저장
	public abstract int saveGuArea(GuAreaDTO dto);
	
} //end interface
