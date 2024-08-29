package curso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootCursoApplication {
//Essa classe foi criada através do start.spring.io.
	public static void main(String[] args) {
		SpringApplication.run(SpringbootCursoApplication.class, args);
	}

}
//elementos yml a serem adicionados após a aula de jpa:
/*
spring.jpa.show-sql = true
spring.jpa.generate-ddl = true
spring.jpa.hibernate.ddl.auto
spring.jpa.properties.hibernate.dialect = org.hibernante.dialect.MySQL8Dialect
 */