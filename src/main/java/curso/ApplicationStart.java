//package curso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableAutoConfiguration //É um Spring Bin; agora, a aplicação é iniciada.

//@ComponentScan //Esse indica que também serão considerado um endpoint válido a classe LivroEndpoint. Só é necessário  o basePackages
//se applicationStart não está no root do projeto,.

//@Configuration //Também transforma a classe em um spring bin, mas coloca a aplicação numa filter chain,
//o que é importante quando se desenvolve a segurança do projeto.
//Essas três anotações são o básico para a criação e escaneamento do projeto Spring. Juntando todas,
//existe a @SpringBootApplication.

//Essa classe foi comentada pois SpringbootCursoApplication existe.

/*
@SpringBootApplication
public class ApplicationStart {
    public static void main(String[] args) {
        //Isso indica que essa classe é um projeto Spring. Apenas isso, porém, resulta em erros, pois
        //configurações de propriedades e classes não foi definida.
        SpringApplication.run(ApplicationStart.class, args);
    }
}
*/