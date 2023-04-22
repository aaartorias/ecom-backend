package com.artorias.ecommerce;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.impl.StaticLoggerBinder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@Slf4j
public class EcommerceApplication {


	public static void main(String[] args) {
		final StaticLoggerBinder binder = StaticLoggerBinder.getSingleton();
		log.info("a----" + binder.getLoggerFactory().toString());
		log.info("b----" +binder.getLoggerFactoryClassStr());
		SpringApplication.run(EcommerceApplication.class, args);
//	static Map<Integer, Integer> mp1 = new HashMap<>();
//	static Map<Integer, Integer> mp2 = new HashMap<>();
//	mp1.put(1,10);
//		mp1.put(4,40);
//		mp1.put(5,900);
//
//		print(mp1);
//
//		mp2.put(5,50);
//		mp2.put(8,80);
//
////		mp2.putAll(mp1);
//		mp1.putAll(mp2);

//		print(mp2);
//		print(mp1);

	}

	static <T>void print(T message) {
		log.info((String)message);
	}

}
