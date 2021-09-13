package com.example.springboot2test;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.springboot2test.util.VerificationCode;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.util.Calendar;
import java.util.HashMap;

//@SpringBootTest
class Springboot2testApplicationTests {
//	@Autowired
//	private CourseRepository repository;

	@Test//令牌的获取
	void contextLoads() {
		HashMap<String,Object>map=new HashMap<>();
		Calendar instance=Calendar.getInstance();
		instance.add(Calendar.SECOND,120);
		String token = JWT.create()
				.withHeader(map) //header
				.withClaim("userId",21) //payload
				.withClaim("username","xiaolin")
				.withExpiresAt(instance.getTime())//指定令牌的过期时间
				.sign(Algorithm.HMAC256("!Q@W#E$R"));//签名
		System.out.println(token);
	}

	@Test
	public void test(){

		JWTVerifier jwtVerifier=JWT.require(Algorithm.HMAC256("!Q@W#E$R")).build();
		DecodedJWT verify=jwtVerifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MTgwMzAwMzAsInVzZXJJZCI6MjEsInVzZXJuYW1lIjoieGlhb2xpbiJ9.Zfc-IHZZb-SM2yn04TQRGo0diPqsu1ZgE80HFXVlyu8\n");
		System.out.println(verify.getClaims().get("userId").asLong());
		System.out.println(verify.getClaims().get("username").asString());
	}

	@Test
	public void verifyCode() {
		VerificationCode code = new VerificationCode();
		BufferedImage image = code.getImage();
		String text = code.getText();
//		session.setAttribute("verify_code",text);
//		VerificationCode.output(image,resp.getOutputStream());
		System.out.println(image);
		System.out.println(text);
	}
}
