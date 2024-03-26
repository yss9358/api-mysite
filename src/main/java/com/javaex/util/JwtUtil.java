package com.javaex.util;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtUtil {
	private static final String SECRET_KEY = "1234"; // 비밀키, 실제 환경에서는 보다 복잡하고 안전한 키 사용 권장
	private static final long EXPIRE_TIME = 1000 * 60 * 60; // 토큰 유효 시간(여기서는 1시간으로 설정)

	
	/***토큰을 생성하고 응답 헤더에 추가하는 메소드 ***/
    public static void createTokenAndSetHeader(HttpServletResponse response, String tokenSubject) {
        //토큰생성
    	String token = createToken(tokenSubject);
        
    	//응답해더에 등록
    	addResponseHeaderToken(response, token);
    }

	// 토큰 생성
	private static String createToken(String tokenSubject) {
		return JWT.create().withSubject(""+tokenSubject) // 토큰의 주체, 여기서는 사용자 no
				.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_TIME)) // 만료 시간 설정
				.sign(Algorithm.HMAC512(SECRET_KEY)); // 서명 알고리즘과 비밀키를 사용하여 서명
	}
	
	// 토큰 응답해더 등록
	private static void addResponseHeaderToken(HttpServletResponse response, String token) {
		response.addHeader("Authorization", "Bearer " + token);
	}

	////////////////////////////////////////////////////////
    
	/***해더에서 사용자 no 추출 ***/
	public static int getNoFromHeader(HttpServletRequest request) {
		String token= getTokenByHeader(request);
		
		if(token != null) {
			boolean check = checkToken(token);
			
			if(check) {
				return Integer.parseInt(getSubjectFromToken(token));
			}
		}
		
		return -1;
	}
	
	
	//해더에서 토큰꺼내기
	private static String getTokenByHeader(HttpServletRequest request) {
    	String authorization = request.getHeader("Authorization");
    	
        // 'Authorization' 헤더 값에서 실제 토큰 값만 추출
        if (authorization != null && authorization.startsWith("Bearer ")) {
            return authorization.substring(7); // "Bearer " 이후의 문자열(토큰) 반환
        
        }else {
        	System.out.println("요청문서에 토큰없음");
        	return null; // 토큰이 없거나 Bearer 타입이 아닐 경우
        }
        
        
    }
	
    // 토큰이 유효한지 검사
	private static boolean checkToken(String token) {
 		
 		try {
 			JWTVerifier verifier = JWT.require(Algorithm.HMAC512(SECRET_KEY)).build();
 			verifier.verify(token); // 토큰 검증
 			System.out.println("토큰이 유효합니다.");
 			return true; // 유효한 경우 true 반환
 		} catch (JWTVerificationException exception) {
 			// 토큰 검증 실패(유효하지 않은 토큰, 만료된 토큰 등)시 처리
 			System.out.println("토큰이 유효하지 않습니다.");
 			return false;
 		}
 	}
 	
 
	//토큰에서 주체 꺼내기
	public static String getSubjectFromToken(String token) {
		
		DecodedJWT decodedJWT = JWT.decode(token);
		return decodedJWT.getSubject();
	}

	
}
