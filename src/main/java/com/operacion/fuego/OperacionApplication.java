package com.operacion.fuego;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.api.server.spi.config.Api;

/**
 * Add your first API methods in this class, or you may create another class. In that case, please
 * update your web.xml accordingly.
**/
@Api(name = "skeleton-api",
    version = "v1")
@SpringBootApplication
public class OperacionApplication {

	public static void main(String[] args) {
		SpringApplication.run(OperacionApplication.class, args);
	}

}
