package curso.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//Serve como modelo do que está no banco de dados.
@Data
@AllArgsConstructor
@Entity
//O domínio é transformado em entidade para que represente a tabela do banco de dados.
@NoArgsConstructor
@Builder
//Necessário, por padrão, para uma entidade.
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Esses dois valores são usados para a criação autoincrementada do Id.
    private Long id;
    private String nome;

    //Desnecessário adicionar getters e setters manualmente, assim como construtores, devido às anotações
    //do lombok.
    /*
    public Livro(String nome){
        this.nome = nome;
    }

    //É necessário criar o get e o set, pois o jackson, que é o serializador do spring, cria os atributos
    //json através desses métodos.
    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }
     */
}
