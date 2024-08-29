package curso.excecoes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
//Anotação utilizada para retornar o status do HTTP
public class BadRequestException extends RuntimeException {
    // Em APIs, é mais importante seguir um padrão no tratamento de exceções do que uma fórmula exata
    //de uso de exceções.
    public BadRequestException(String message){
        super(message);
    }
}
