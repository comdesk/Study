package org.zerock.wecart;

import org.zerock.wecart.domain.StandardA;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class PublicDataOpen {

	public static void main(String[] args) {
		
		PublicDataAccess data = new PublicDataAccess();
		
		String productEndPoint = "http://openapi.price.go.kr/openApiImpl/ProductPriceInfoService/getProductInfoSvc.do?";
		String retailEndPoint = "http://openapi.price.go.kr/openApiImpl/ProductPriceInfoService/getStoreInfoSvc.do?";
		String unitEndPoint = "http://openapi.price.go.kr/openApiImpl/ProductPriceInfoService/getStandardInfoSvc.do?classCode=UT&";
		String cateEndPoint = "http://openapi.price.go.kr/openApiImpl/ProductPriceInfoService/getStandardInfoSvc.do?classCode=AL&";
		String areaEndPoint = "http://openapi.price.go.kr/openApiImpl/ProductPriceInfoService/getStandardInfoSvc.do?classCode=AR&";
		
		try {
		
//			//상품정보
//			String productResult = data.testOpenAPI(productEndPoint);
//			data.getJson(productResult, new ProductA().getClass());
//			
//			//업체정보
//			String retailResult = data.testOpenAPI(retailEndPoint);
//			data.getJson(retailResult, new RetailA().getClass());
//			
//			//단위정보
//			String unitResult = data.testOpenAPI(unitEndPoint);
//			data.getJson(unitResult, new StandardA().getClass());			
			
			//카테고리
//			String cateResult = data.testOpenAPI(cateEndPoint);
//			data.getJson(cateResult, new StandardA().getClass());
			
//			//지역정보
			String areaResult = data.testOpenAPI(areaEndPoint);
			StandardA standardA = data.getJson(areaResult, new StandardA().getClass());
			int affectedLines = data.saveSiArea(standardA);		//country 테이블 저장
			int affectedLines2 = data.saveGuArea(standardA);		//city 테이블 저장

//			log.info("affectedLines: {}", affectedLines);
			log.info("affectedLines2: {}", affectedLines2);
			
		} catch(Exception e) {
			e.printStackTrace();
		} //try-catch
		

	} //main

} //end class
