package curso.servico;

import curso.dominio.Livro;
import curso.excecoes.BadRequestException;
import curso.mapeador.LivroMapper;
import curso.repositorio.LivroRepositorio;
import curso.requisicoes.LivroPostRequest;
import curso.requisicoes.LivroPutRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

//Onde estão as lógicas dos métodos de LivroRepositorio.

@Service
@RequiredArgsConstructor
//Agora é um Spring bin, e pode ser injetado.
public class LivroServico {
    //Usado até antes da implementação do repositório JPA.
//    //Essa lista estática é criada da seguinte maneira, pois nao há banco de dados nessa aplicação.
//    private static List<Livro> livros;
//
//    static{
//        livros = new ArrayList<>(List.of(new Livro(1L, "Crime e Castigo"),
//                new Livro(2L, "A Metamorfose")));
//    }
    private final LivroRepositorio livroRepositorio;

    public List<Livro> listarTodos(){
        return livroRepositorio.findAll();
    }

    public Livro porId(long id){
        return livroRepositorio.findById(id)
                .orElseThrow(() -> new BadRequestException("Livro não Existe"));

        //Código sem jpa:
        //livros.stream().filter(livro -> livro.getId().equals(id))
        //        .findFirst()
        //        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Livro Não Existe"));
    }

    public List<Livro> porNome(String nome){
        return livroRepositorio.encontrarPorNome(nome);
    }

    public Livro salvar(LivroPostRequest livroPostRequest){
        //Sem jpa:
        //livro.setId(ThreadLocalRandom.current().nextLong(3, 999_999));
        //livros.add(livro);
        //return livro;

        //Para evitar problemas de mapeamento na criação dos endpoints, foi criado esse builder que
        //leva o nome dado em LivroPostRequest para o livro.
        //Livro livro = Livro.builder().nome(livroPostRequest.getNome()).build();
        //return livroRepositorio.save(livro);

        //O mapper pode simplificar ainda mais o código:
        return livroRepositorio.save(LivroMapper.CRIADOR.paraLivro(livroPostRequest));
    }

    public void deletar(long id){
        livroRepositorio.delete(porId(id));
        //Sem jpa: livros.remove(porId(id));
    }

    public void substituir(LivroPutRequest livroPutRequest){
        //Sem uso do JPA:
        //      deletar(livro.getId());
        //        livros.add(livro)

        //É necessário ter certeza que o id existe antes de fazer a substituição:
        Livro livroId = porId(livroPutRequest.getId());
//        Livro livro = Livro.builder()
//                .nome(livroPutRequest.getNome())
//                .id(livroPutRequest.getId())
//                .build();
//        livroRepositorio.save(livro);

        //O mapper também pode simplificar esse put:

        livroRepositorio.save(LivroMapper.CRIADOR.paraLivro(livroPutRequest)).setId(livroId.getId());
    }
}
