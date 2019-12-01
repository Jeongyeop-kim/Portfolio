package com.exam.repository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class APISearch {
	public String json;
	String clientId = "3_ZMCrQanb8rsKfO_xdm";//애플리케이션 클라이언트 아이디값";
    String clientSecret = "MzRZn6hwNk";//애플리케이션 클라이언트 시크릿값";
    public APISearch() throws Exception {
    	int display = 30;
        String text = URLEncoder.encode("경제", "UTF-8");
        String apiURL = "https://openapi.naver.com/v1/search/news?query="+ text + "&display=" + display; // json 결과
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
        } else {  // 에러 발생
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
//      System.out.println(json);
    } 
	
    public static void main(String[] args) {
        try {
			new APISearch();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
