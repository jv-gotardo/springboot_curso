package curso.controlador;

import curso.dominio.Livro;
import curso.requisicoes.LivroPostRequest;
import curso.requisicoes.LivroPutRequest;
import curso.servico.LivroServico;
import curso.util.DataUtil;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
//O controlador contém os endpoints.

//Essa anotação adiciona o ResponseBody; todos os retornos desse método serão apenas strings, que é o
//requerido para APIs, retornando apenas o JSON do endpoint.
@RestController
@RequestMapping("livros")
@Log4j2
@AllArgsConstructor
//No nível da classe, é possível separar os controladores de forma mais organizada.
public class LivroEndpoint {
    //Faz a injeção; explicação na classe DataUtil. O recomendado é utilizar um construtor. Pode ser criado
    //pelo AllArgsConstructor, do lombok, que cria um construtor para todo atributo presente nessa classe.
    //O RequiredArgs, por sua vez, cria um construtor com atributos finais apenas. Com apenas um
    // construtor utilizado, não é necessário o autowired.
    //Mais correto ainda seria a criação de uma interface para isso.
    @Autowired
    private DataUtil dateUtil;
    private LivroServico livroServico;
    //HotSwap - Para algumas mudanças, não é necessário reiniciar toda a aplicação, economizando tempo.
    //Isso inclui modificações em métodos, por exemplo.

    //Quando a aplicação for executada, esse endpoint deve retornar o método de listagem.
    @GetMapping(path = "lista")
    //Somente isso, gera um 404. O spring faz a leitura das classes, porém detecta o ApplicationStart
    //no root do projeto, e não numa pasta separada.
    //É possível, quando há apenas um getMapping, suprimir o path. O get retorna já quando acessar /livros.
    public ResponseEntity<List<Livro>> lista(){
        log.info(dateUtil.DataAtualParaDataAmericana(LocalDateTime.now()));
        //O ResponseEntity retorna outras informações, como status da requisição, que nesse caso é o get.
        //Também é necessário modificar a assinatura do método para isso.
        return new ResponseEntity<>(livroServico.listarTodos(), HttpStatus.OK);
        //Não é necessário criar um novo objeto ResponseEntity se for passado como método a requisição
        //retornada por ele.
    }

    //{} é uma path variable. Indica que, quando for passado o endereço, com o id correspondente.
    @GetMapping(path = "/{id}")
    public ResponseEntity<Livro> porId(@PathVariable long id){
        return new ResponseEntity<>(livroServico.porId(id), HttpStatus.OK);
    }
    //Uma exceção retornaria muitas informações que, por questão de segurança, não é boa prática
    //retornar ao front-end. Através do .properties do resources, pode-se modificar a mensagem dada.
    //Always é o padrão, never não retorna nenhuma stracktrace além do básico, on_param retorna apenas
    //se a url for modificada para ?trace=true.

    @PostMapping
    //Se espera, nesse tipo de método criado, que seja recebido um body correspondente a classe a ser
    //criado. Pode-se usar o jackson para fazer o mapeamento automático desse request para a classe:
    //se o .json encontrado for igual ao que está na classe, o mapeamento for feito.
    public ResponseEntity<Livro> salvar(@RequestBody LivroPostRequest livro){
        //O created é a requisição padrão para indicar a criação de um arquivo.
        //A troca de livro por livroPostRequest gerou um problema de mapeamento.
        //
        return new ResponseEntity<>(livroServico.salvar(livro), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Void> deletar(@PathVariable long id){
        livroServico.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    //Idempotência: RFC 7231 - Referência para implementação do HTTP.
    //4.2.1: Métodos safe: get.
    //Métodos idempotentes (requisita o servidor e não alteram o seu estado): put e delete; também são safe.
    //Post não é nenhum dos dois.
    //O delete não seria idempotente se, por exemplo, deletasse sempre o primeiro elemento do servidor, pois
    //isso o modifica.

    @PutMapping
    public ResponseEntity<Void> substituir(@RequestBody LivroPutRequest livro){
        livroServico.substituir(livro);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    //O mesmo processo de troca no requestBody feito no post também é feito aqui. Isso vale para qualquer
    //endpoint que utilize requestBody.

    //Map Struct - Facilita o mapeamento de classes, dizendo o campo da classe origem e onde esse campo
    //deve ser posto, bastante usado em DTOs, mas também pode ser usado em outros casos.
    //

    @GetMapping(path = "/encontrar")
    public ResponseEntity<List<Livro>> porNome(@RequestParam String nome){
        return new ResponseEntity<>(livroServico.porNome(nome), HttpStatus.OK);
    }
    //Antes, o path era {nome}, sendo ambíguo a {id}.
    //A ambiguidade só é notada quando alguma modificação é feita; a aplicação funciona normalmente,
    //porém, isso pode ser facilmente resolvido colocando um /encontrar acima de nome por exemplo.
    //Contudo, se isso for se especializando ainda mais, pode complicar o programa, e por isso existe
    //o request params, para lidar com parâmetros na URL.
    //poderia ser feito dessa maneira: /encontrar?nome=a metamorfose
    //múltiplos request params podem ser feitos, por exemplo, se houvesse o atributo autor e seu requestParam
    // /encontrar?nome=a metamorfose&autor=franz kafka

}