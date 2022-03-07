package sch.cqre.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.Transactional;
import sch.cqre.api.config.FileStorageConfig;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageConfig.class
})

@Transactional
// @Transactional 추가한 이유 : https://www.whiteship.me/jpa-entitymanager-contains/
public class ApiApplication {


	public static void main(String[] args) {

		SpringApplication.run(ApiApplication.class, args);

	}


}
