package curso.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//A injeção de dependências é uma inversão de controle, em que a classe não mais precisa criar ou
//buscar objetos de que depende. Isso permite o desacoplamento entre as classes; o controle agora
//passa para quem instanciar a classe, não para a classe em si, que injetará a dependência. Pra isso
//serve o autowired, anotação do Spring (inicializando no construtor, que é a boa prática).
//Quando se quer injetar uma classe, deve-se indicar isso ao spring bin, através de:
//Component, Service (forma mais especializada de Component)
//ou Repository (indica-se quando utilizar o SpringData, injetar por essa classe).
@Component
public class DataUtil {
    public String DataAtualParaDataAmericana(LocalDateTime ldt){
        return DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss").format(ldt);
    }
}
