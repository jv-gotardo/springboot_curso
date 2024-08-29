package curso.repositorio;

//O repositório serve como uma conexão com o banco de dados.

import curso.dominio.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepositorio extends JpaRepository<Livro, Long> {
    //O repositório extende as funcionalidades do repositório Jpa, passando o objeto e o tipo do ID.
    //O JPARepository tem métodos já prontos, envolvendo várias funcionalidades prontas do banco de dados.

    //RequestParams - São modificações da URL para obter um determinado resultado
    List<Livro> encontrarPorNome(String nome);
}
