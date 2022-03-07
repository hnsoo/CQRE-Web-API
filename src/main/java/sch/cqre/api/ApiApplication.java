package sch.cqre.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import sch.cqre.api.config.FileStorageConfig;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageConfig.class
})

public class ApiApplication {


	public static void main(String[] args) {

		SpringApplication.run(ApiApplication.class, args);

	}


}
