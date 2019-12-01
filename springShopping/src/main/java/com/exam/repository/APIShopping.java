package com.exam.repository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import lombok.extern.log4j.Log4j;

@Log4j
public class APIShopping {
	public String json;
	String clientId = "su3K__oFgBWGioCEsPnf";//애플리케이션 클라이언트 아이디값";
    String clientSecret = "vlQe0bJb_W";//애플리케이션 클라이언트 시크릿값";
    public APIShopping(String sSearch) throws Exception {
    	int display = 40;
    	String text = URLEncoder.encode(sSearch, "UTF-8");
        String apiURL = "https://openapi.naver.com/v1/search/shop?query="+ text + "&display=" + display; // json 결과
        //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 결과
        URL url = new URL(apiURL);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("X-Naver-Client-Id", clientId);
        con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
        int responseCode = con.getResponseCode();
        BufferedReader br;
        if (responseCode == 200) { // 정상 호출
            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        } else { // 에러 발생
            br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
        }
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = br.readLine()) != null) {
            response.append(inputLine);
        }
        json = response.toString();
//      br.close();
//      System.out.println(response.toString());
    } 
	
    public static void main(String[] args) {
        try {
			new APISearch();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
