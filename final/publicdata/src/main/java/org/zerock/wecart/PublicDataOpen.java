package org.zerock.wecart;

import org.zerock.wecart.domain.ProductA;
import org.zerock.wecart.domain.StandardA;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class PublicDataOpen {

	public static void main(String[] args) {
		log.trace("main(args) invoked.");
		
		PublicDataAccess data = new PublicDataAccess();
		
		String productEndPoint = "http://openapi.price.go.kr/openApiImpl/ProductPriceInfoService/getProductInfoSvc.do?";
		String retailEndPoint = "http://openapi.price.go.kr/openApiImpl/ProductPriceInfoService/getStoreInfoSvc.do?";
		String unitEndPoint = "http://openapi.price.go.kr/openApiImpl/ProductPriceInfoService/getStandardInfoSvc.do?classCode=UT&";
		String cateEndPoint = "http://openapi.price.go.kr/openApiImpl/ProductPriceInfoService/getStandardInfoSvc.do?classCode=AL&";
		String areaEndPoint = "http://openapi.price.go.kr/openApiImpl/ProductPriceInfoService/getStandardInfoSvc.do?classCode=AR&";
		
		try {
		
//			//상품정보
			String productResult = data.testOpenAPI(productEndPoint);
			ProductA productA = data.getJson(productResult, new ProductA().getClass());
//			System.out.println(productA);
			int affectedLinesProduct = data.saveProduct(productA);
			System.out.println("affectedLinesProduct: " + affectedLinesProduct);
			
			
			
//			//업체정보 ADMIN계정에만
//			String retailResult = data.testOpenAPI(retailEndPoint);
//			RetailA retailA = data.getJson(retailResult, new RetailA().getClass());
//			
//			int affectedLinesRetail = data.saveRetail(retailA);
//			log.info("affectedLinesRetail: {}", affectedLinesRetail);
//			
//			//단위정보
			String unitResult = data.testOpenAPI(unitEndPoint);
			StandardA unitA = data.getJson(unitResult, new StandardA().getClass());			
//			System.out.println(unitA);
			
			int affectedLinesUnit = data.saveUnit(unitA);
			log.info("affectedLinesUnit: {}", affectedLinesUnit);
			
			
			//카테고리 OK
//			String cateResult = data.testOpenAPI(cateEndPoint);
//			StandardA cateA = data.getJson(cateResult, new StandardA().getClass());
//			int affectedLinesCate = data.saveCate(cateA);
//			log.info("affectedLinesCate: {}", affectedLinesCate);

			
//			//지역정보 OK
//			String areaResult = data.testOpenAPI(areaEndPoint);
//			StandardA areaA = data.getJson(areaResult, new StandardA().getClass());
//			int affectedLines = data.saveSiArea(areaA);		//country 테이블 저장
//			int affectedLines2 = data.saveGuArea(areaA);		//city 테이블 저장
//
//			log.info("affectedLines: {}", affectedLines);
//			log.info("affectedLines2: {}", affectedLines2);
			
		} catch(Exception e) {
			e.printStackTrace();
		} //try-catch
		

	} //main

} //end class
