package curso.requisicoes;

import lombok.Data;

@Data
public class LivroPutRequest {
    //Essa classe foi criada pois não é uma boa prática utilizar uma classe que também serve como uma
    //tabela do banco de dados como requestBody. Padrões como DTO geralmente são utilizados para corrigir
    //esse problema.
    private Long id;
    private String nome;
}
