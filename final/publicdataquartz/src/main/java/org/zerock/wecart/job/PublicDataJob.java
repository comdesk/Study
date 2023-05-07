package org.zerock.wecart.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.zerock.wecart.domain.ProductA;
import org.zerock.wecart.domain.RetailA;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class PublicDataJob implements Job{

	@Override
	public void execute(JobExecutionContext ctx) throws JobExecutionException {
		log.trace("execute({}) invoked.", ctx);
		
		PublicDataAccess data = new PublicDataAccess();
		
		String productEndPoint = "http://openapi.price.go.kr/openApiImpl/ProductPriceInfoService/getProductInfoSvc.do?";
		String retailEndPoint = "http://openapi.price.go.kr/openApiImpl/ProductPriceInfoService/getStoreInfoSvc.do?";
		
		
		try {
		
//			//상품정보
			String productResult = data.testOpenAPI(productEndPoint);
			ProductA productA = data.getJson(productResult, new ProductA().getClass());
			
			int affectedLinesProduct = data.saveProduct(productA);
			System.out.println("affectedLinesProduct: " + affectedLinesProduct);
			
//			//업체정보
			String retailResult = data.testOpenAPI(retailEndPoint);
			RetailA retailA = data.getJson(retailResult, new RetailA().getClass());
			
			int affectedLinesRetail = data.saveRetail(retailA);
			log.info("affectedLinesRetail: {}", affectedLinesRetail);
			
			//상품평균가
			int affectedLinesAvg = data.saveAvgPrice();
			log.info("affectedLinesAvg: {}", affectedLinesAvg);

			
		} catch(Exception e) {
			e.printStackTrace();
			
			throw new JobExecutionException(e);
		} //try-catch
		
	} //execute

} //end class
