# PROMPTS: Grupo ____

**Integrantes:**

1. Ana Clara Kajita das Neves
2. Miriana Martins dos Santos
3. Victor Gabriel Arvellos

**Prova:** API em MVC

**Usamos IA?** (X) Sim  ( ) Não

**Qual ferramenta e modelo (se usaram): Gemini**

> Quem não usou IA: troque "prompt" por "passo". Em cada bloco abaixo, descreva o que vocês fizeram naquele passo, na ordem em que aconteceu, e preencha os outros campos do mesmo jeito.

## Registro, na ordem

Copie o bloco abaixo para cada prompt. Escreva o prompt exatamente como foi enviado.

### Prompt 1

**O prompt (cópia exata):**

>  tô numa prova de spring boot com mvc. preciso criar a classe Sala no pacote escola.model e o SalaRepository em escola.repository. a sala tem id, codigo (string unica), nome (string), capacidade (int maior q zero) e reservada (boolean, comeca false), me passa essas duas classes com as anotações do JPA e do Bean Validation

**O que a IA devolveu (uma ou duas linhas): Devolveu a classe Sala com @Entity, @NotBlank, @Min(1) e a interface SalaRepository com findByCodigo..**

**O que fizemos com a resposta:** (X) usamos como veio  ( ) alteramos  ( ) descartamos

**Por quê (o que conferimos, o que mudamos): Copiamos as duas classes pros pacotes criados (escola.model e escola.repository). Tivemos que ajustar os imports do jakarta.validation porque a IA sugeriu javax.validation no primeiro momento.**

### Prompt 2

**O prompt (cópia exata):**

> agora preciso da SalaService em escola.service. ela vai ter as regras da escola: não pode código repetido (dar erro 409), não pode reservar sala que já tá reservada (409), só pode liberar se tiver reservada (409), não pode excluir se tiver reservada (409), e se não achar a sala dá 404. me mostra como fazer essa classe e como retornar esses status HTTP corretos

**O que a IA devolveu (uma ou duas linhas): Devolveu a SalaService criando exceções personalizadas (ex: EntidadeNaoEncontradaException e RegraNegocioException) e sugeriu um @RestControllerAdvice pra capturar tudo.**

**O que fizemos com a resposta:** (X) usamos como veio  ( ) alteramos  ( ) descartamos

**Por quê (o que conferimos, o que mudamos): Usamos a SalaService inteira. Decidimos criar uma classe @RestControllerAdvice no pacote escola.controller para mapear essas exceções automaticamente nos status 404 e 409, deixando o código mais limpo.**

### Prompt 3

**O prompt (cópia exata):**

> cria o SalaController em escola.controller usando só a SalaService. tem q ter as rotas pra cadastrar (POST 201), listar (GET 200), buscar id (GET 200), reservar (PATCH /1/reservar 200), liberar (PATCH /1/liberar 200) e deletar (DELETE 204). se mandar dado invalido tipo sem nome o spring tem q dar 400

**O que a IA devolveu (uma ou duas linhas): Devolveu o SalaController com as anotações @PostMapping, @GetMapping, @PatchMapping, @DeleteMapping e o @Valid nos parâmetros do DTO/Model.**

**O que fizemos com a resposta:** (X) usamos como veio  ( ) alteramos  ( ) descartamos

**Por quê (o que conferimos, o que mudamos): Aceitamos o código do Controller. Só trocamos o caminho do endpoint de deleção que veio como /salas/excluir/{id} para /salas/{id}, mantendo o padrão REST que o professor pediu.**


### Prompt 4

**O prompt (cópia exata):**

> me passa os comandos cURL ou arquivo .http com a ordem exata do roteiro de teste da prova (passos 1 ao 10) pra eu rodar aqui no VS Code / Postman e ver se os status HTTP batem certinho com o que a prova exige.

**O que a IA devolveu (uma ou duas linhas): Devolveu a lista dos 10 testes na ordem do enunciado.**

**O que fizemos com a resposta:** ( ) usamos como veio  ( ) alteramos  (X) descartamos

**Por quê (o que conferimos, o que mudamos):**



## Como conferimos

== EXECUÇÃO DOS TESTES DA PROVA ===

[TESTE] 1. Cadastrar Sala S101
   Sucesso: Sala{id=1, codigo='S101', nome='Sala S101', capacidade=30, reservada=false}

[TESTE] 2. Tentar cadastrar com capacidade inválida (Simula 400)
   Capturado -> Erro 400: A capacidade deve ser maior que zero

[TESTE] 3. Tentar cadastrar código duplicado (Simula 409)
   Capturado -> HTTP 409 CONFLICT: Código repetido: S101

[TESTE] 4. Reservar a Sala 1 (Simula 200 OK)
   Sucesso: Sala{id=1, codigo='S101', nome='Sala S101', capacidade=30, reservada=true}

[TESTE] 5. Tentar reservar a Sala 1 novamente (Simula 409)
   Capturado -> HTTP 409 CONFLICT: Sala já está reservada.

[TESTE] 6. Tentar excluir a Sala 1 reservada (Simula 409)
   Capturado -> HTTP 409 CONFLICT: Não pode excluir sala reservada.

[TESTE] 7. Liberar a Sala 1 (Simula 200 OK)
   Sucesso: Sala{id=1, codigo='S101', nome='Sala S101', capacidade=30, reservada=false}

[TESTE] 8. Excluir a Sala 1 liberada (Simula 204 No Content)
   Deletada com sucesso!

[TESTE] 9. Buscar Sala 1 deletada (Simula 404)
   Capturado -> HTTP 404 NOT FOUND: Sala não encontrada ID: 1

## Onde a IA errou, ou onde nós erramos

Tentamos rodar os testes (testes.http / curl) e deu conexão recusada porque a API não estava rodando — a máquina nem tinha o Java (JDK) instalado para subir o servidor na porta 8080.

## Respostas finais, sem consultar a IA

Respondam com as suas palavras, nos últimos 5 minutos. Não copiem de lugar nenhum.

**Pergunta 1:**
O Controller recebe o PATCH /salas/1/reservar e chama a Service; a Service busca o Model via Repository, valida se está livre, altera a flag de reserva e salva; o Controller devolve 200 OK
**Pergunta 2:**
O Controller só cuida das rotas HTTP, enquanto a Service centraliza as regras de negócio para poderem ser reutilizadas e testadas sem dependência da camada Web
**Pergunta 3:**
Nenhuma classe Java muda, pois o Spring Data JPA/Hibernate isola o código do banco; só alteramos o application.properties (conexão) e o pom.xml (driver)