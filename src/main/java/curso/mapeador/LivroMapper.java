package curso.mapeador;

import curso.dominio.Livro;
import curso.requisicoes.LivroPostRequest;
import curso.requisicoes.LivroPutRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
//O componentModel serve para injeção de dependência, se necessário
public abstract class LivroMapper {
    public static final LivroMapper CRIADOR = Mappers.getMapper(LivroMapper.class);
    //O Mappers serve justamente para criação de instâncias que o utilizam.
    //O atributo final serve para garantir a segurança da não-modificação dessa instância.

    public abstract Livro paraLivro(LivroPostRequest lpr);
    public abstract Livro paraLivro(LivroPutRequest lpr);
}
