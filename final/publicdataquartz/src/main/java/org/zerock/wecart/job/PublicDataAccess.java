package org.zerock.wecart.job;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Objects;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.zerock.wecart.domain.CateDTO;
import org.zerock.wecart.domain.GuAreaDTO;
import org.zerock.wecart.domain.ProductA;
import org.zerock.wecart.domain.ProductB;
import org.zerock.wecart.domain.ProductC;
import org.zerock.wecart.domain.ProductDTO;
import org.zerock.wecart.domain.RetailA;
import org.zerock.wecart.domain.RetailB;
import org.zerock.wecart.domain.RetailC;
import org.zerock.wecart.domain.RetailDTO;
import org.zerock.wecart.domain.SiAreaDTO;
import org.zerock.wecart.domain.StandardA;
import org.zerock.wecart.domain.StandardB;
import org.zerock.wecart.domain.StandardC;
import org.zerock.wecart.domain.UnitDTO;
import org.zerock.wecart.mapper.DataMapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2
public class PublicDataAccess {
//이미 URL Encoding 되어 있는 서비스 키
	private String serviceKey = "za%2FI4esx8hY%2F2ai3R759VtWOcO05iDsE%2FKbg9YkvCliD84cTEvJGnFUWgOxzA2nuAnLJc3dD5EMP1fU0uWsIdA%3D%3D";
	
	//URL Encoding 되어 있지 않은 서비스 키
	private String originalServiceKey = "za/I4esx8hY/2ai3R759VtWOcO05iDsE/Kbg9YkvCliD84cTEvJGnFUWgOxzA2nuAnLJc3dD5EMP1fU0uWsIdA==";

	private SqlSessionFactory factory;
	
	//API 데이터 리턴
	public String testOpenAPI(String endPoint) throws IOException {
		log.trace("testOpenAPI() invoked.");

		String queryString = 
				String.format("ServiceKey=%s", 
						URLEncoder.encode(originalServiceKey, "UTF-8")
				);
		log.info("0. queryString: {}", queryString);

		URL url = new URL(endPoint + queryString);
		Objects.requireNonNull(url);
		log.info("1. url: {}", url);
		
		@Cleanup("disconnect")
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		Objects.requireNonNull(conn);
		log.info("2. conn: {}", conn);
		log.info("3. type: {}", conn.getClass().getName());
		
		conn.setConnectTimeout(5000); //milliseconds
		conn.setUseCaches(false); //캐시 사용하지 말라! => 매번 실제 접속하라(기본값: true => 최초 접속하고 캐시를 쓰고 다시 접속 안 함.)
		conn.setDoInput(true);		//HTTP response 메시지는, 우리가 직접 InputStream을 열어서 읽어내기로 함. 요청을 보낼 때, 응답을 받을 때를 고려한 자바 입출력 스트림의 사용 여부를 결정
		conn.setDoOutput(false);		//HTTP request 메시지는 기본 구현에 맡김.
		conn.setRequestMethod("GET"); //HTTP method 설정
		conn.setReadTimeout(3000);		//지정된 밀리초 시간 내에, HTTP response의 BODY(JSON이 들어있음)를 읽어낼 수 있어야 한다는 의미

		conn.connect();			//연결시도
		
		log.info("2. conn: {}", conn);
		log.info("3. type: {}", conn.getClass().getName());

		int statusCode = conn.getResponseCode();
		String responseMessage = conn.getResponseMessage();
		log.info("4. statusCode: {}", statusCode);
		log.info("5. responseMsg: {}", responseMessage);	//200 OK일 때, 응답 바디에 있는 거 끄집어내면 됨.

		if(statusCode == 200) {	
			@Cleanup
			InputStream is = conn.getInputStream();	//if conn.setDoInput(true)..

			Charset charset = Charset.forName("UTF-8");	//2nd. method
			
			@Cleanup
			BufferedReader br = new BufferedReader(new InputStreamReader(is, charset));	//Charset 객체로 지정
			
//			String line = null;
//			while((line = br.readLine()) != null) {	//if null, it means "EOF"
//				System.out.println(line);			
//			} //while
			
			String line = null;
			StringBuffer result = new StringBuffer();
			while((line = br.readLine()) != null) {	//if null, it means "EOF"
			    result.append(line);
			    result.append("\n"); // 각 줄 끝에 개행 문자를 추가하여 줄바꿈을 유지
			} //while
			
			return result.toString();
		} //if
		
//			conn.disconnect();		//연결해제
		return null;
	} //testOpenAPI
	
//	XML -> JSON -> Parser
	public <T> T getJson(String result, Class<T> clazz) {
		try {
			XmlMapper xmlMapper = new XmlMapper();			
			JsonNode jsonNode = xmlMapper.readTree(result);

			String jsonString = jsonNode.toString();

			ObjectMapper objectMapper = new ObjectMapper();
			T jsonToClass = objectMapper.readValue(jsonString, clazz);
			
//			System.out.print(jsonToClass);
			
			return jsonToClass;
			
			
		} catch (JsonProcessingException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		} //try-catch
		
		return null;
		
	} //getJson
	
	//SqlSessionFactory 생성
	public void buildFactory() throws IOException{
		log.trace("buildFactory() invoked.");		
		
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		
		InputStream config = Resources.getResourceAsStream("mybatis-config.xml");
		Objects.nonNull(config);
		this.factory = builder.build(config);
	} // buildFactory
	

		
	//업체 업데이트
	public int saveRetail(RetailA retailA) throws IOException {
		log.trace("saveRetail(retailA) invoked.");
		
		this.buildFactory();
		
		@Cleanup
		SqlSession session = this.factory.openSession();
		
		DataMapper mapper = session.getMapper(DataMapper.class);
		
		RetailB b = retailA.getResult();
		log.info("b: {}", b);
		
		List<RetailC> retailList = b.getEntpInfoVO();
		int affectedLines = 0;
		
		try {
			for(RetailC retail : retailList) {
				String retail_name = retail.getEntpName();
				String retail_addr = retail.getPlmkAddrBasic();
				Integer town_id = Integer.parseInt(retail.getAreaDetailCode());
				
				RetailDTO dto = new RetailDTO();
				dto.setRetail_name(retail_name);
				dto.setRetail_addr(retail_addr);
				dto.setTown_id(town_id);
				
				affectedLines = mapper.saveRetail(dto);
				
			} //enhanced for
			
			session.commit();
		} catch(Exception e) {
			session.rollback();
		}
		
		return affectedLines;
		
	} //saveCate
	

	//상품 업데이트
	public int saveProduct(ProductA productA) throws IOException {
		log.trace("saveProduct(productA) invoked.");
		
		this.buildFactory();
		
		@Cleanup
		SqlSession session = this.factory.openSession();
		
		DataMapper mapper = session.getMapper(DataMapper.class);
		
		ProductB b = productA.getResult();
		
		List<ProductC> productList = b.getItem();
		
		int affectedLines = 0;
		
		try {
			for(ProductC product : productList) {
				String goods_name = product.getGoodName().trim();
				Integer capacity = Integer.parseInt(product.getGoodTotalCnt());
				String capacity_unit_id = product.getGoodUnitDivCode();
				Integer category_id = Integer.parseInt(product.getGoodSmlclsCode());
				
				ProductDTO dto = new ProductDTO();
				dto.setGoods_name(goods_name);
				dto.setCapacity(capacity);
				dto.setCapacity_unit_id(capacity_unit_id);
				dto.setCategory_id(category_id);
				
				affectedLines = mapper.saveProduct(dto);
				
			} //enhanced for
			
			session.commit();
		} catch(Exception e) {
			e.printStackTrace();
			
			session.rollback();
		}
		
		return affectedLines;
		
	} //saveCate	

} //end class









