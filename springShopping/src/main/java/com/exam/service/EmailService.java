package com.exam.service;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmailService {
	
	// 아이디 전송
	public void sendId(String toEmail, String toName, String message) {
		SimpleEmail email = new SimpleEmail();
		
		// SMTP 서버 연결 설정
		email.setHostName("smtp.naver.com");
		email.setSmtpPort(465);
		email.setAuthentication("arborlucens", "votmdnjem8!@");
		
		// SMTP SSL, TLS 설정
		email.setSSLOnConnect(true);
		email.setStartTLSEnabled(true);
		String result = "fail";
		try {
			// 보내는 사람 설정
			email.setFrom("arborlucens@naver.com", "관리자", "UTF-8");
			
			// 받는 사람 설정
			email.addTo(toEmail, toName, "UTF-8");
			
			// 제목 설정
			email.setSubject("아이디 확인 메일입니다.");
			
			// 본문 설정
			email.setMsg(message);
			
			// 메일 전송
			result = email.send();
			
		} catch (EmailException e) {
			e.printStackTrace();
		}
	} // sendId
	
	// 비밀번호 전송
	public void sendPasswd(String toEmail, String toName, String message) {
		SimpleEmail email = new SimpleEmail();
		
		// SMTP 서버 연결 설정
		email.setHostName("smtp.naver.com");
		email.setSmtpPort(465);
		email.setAuthentication("arborlucens", "votmdnjem8!@");
		
		// SMTP SSL, TLS 설정
		email.setSSLOnConnect(true);
		email.setStartTLSEnabled(true);
		String result = "fail";
		try {
			// 보내는 사람 설정
			email.setFrom("arborlucens@naver.com", "관리자", "UTF-8");
			
			// 받는 사람 설정
			email.addTo(toEmail, toName, "UTF-8");
			
			// 제목 설정
			email.setSubject("임시 비밀번호 메일입니다.");
			
			// 본문 설정
			email.setMsg(message);
			
			// 메일 전송
			result = email.send();
			
		} catch (EmailException e) {
			e.printStackTrace();
		}
	} // sendPasswd
}
