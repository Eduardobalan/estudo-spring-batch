## Como executar 💻

Aqui está um guia básico para executar este projeto em seu ambiente de desenvolvimento:

### Passo 1 - Clone o Repositório

Primeiro, clone o projeto a partir do repositório do GitHub. Use um dos seguintes comandos, dependendo da forma de
acesso escolhida:

```bash
git clone git@github.com:Eduardobalan/estudo-spring-batch.git
```

ou

```bash
git clone https://github.com/Eduardobalan/estudo-spring-batch.git
```

### Passo 2 - Configuração na Sua IDE

Certifique-se de importar o projeto para a sua IDE favorita. Isso permitirá que você faça as configurações necessárias e
trabalhe com o código com facilidade.

### Passo 3 - Iniciando os Containers Docker

Para executar o projeto, é necessário iniciar dois containers Docker com bancos de dados PostgreSQL.
Para fazer isso, execute o seguinte comando a partir da pasta raiz do repositório:

```bash
docker-compose up -d
```

Isso iniciará os bancos de dados necessários para o funcionamento do projeto.

### Passo 4 - Configuração Automática ao subir seu projeto.

Quando você executa o projeto pela primeira vez, várias ações serão executadas automaticamente:

- O Hibernate criará as entidades necessárias para ambos os bancos de dados.
- O MigrarBancoSpringBatchCommandLineRunner inserirá as tabelas do Spring Batch.
- O MigrarCargaOrigemCommandLineRunner preencherá automaticamente o banco de dados de origem (DB_ORIGEM) com registros
  de exemplo.

### Passo 5 - Execução dos Jobs via Requisição HTTP

O projeto oferece um único ponto de entrada para a execução de Jobs:

- http://localhost:8081/startJobs/{jobName}/{jobId}

#### Substitua as variáveis Path:

**{jobName}**: Coloque o nome do job desejado ('JOB_MIGRAR_PESSOA_DESTINO' ou '
JOB_VISUALIZAR_CICLO_DE_VIDA_SPRING_BATCH').

**{jobId}**: Insira um identificador único. Se você usar o mesmo identificador, o Spring Batch tentará continuar a
execução anterior.
______________________________________________________________________________________________________________________

#### Executando o Job JOB_MIGRAR_PESSOA_DESTINO

Neste job inicial, a migração de dados é executada, onde os dados são extraídos de uma tabela de origem (tb_pessoa) e
inseridos nas tabelas de destino (tb_pessoa_destino e tb_usuario_destino).
Embora este job possa parecer direto, há diversas configurações essenciais dentro da pasta de configuração do
aplicativo (application/configuration).
Estas configurações são cruciais para informar o Spring sobre quais entidades (Entitys) e repositórios (repositorys)
estão vinculados a quais bases de dados, e apesar de serem ocultas, desempenham um papel fundamental no processo de
migração de dados.

Use o seguinte comando curl para iniciar o Job 'JOB_MIGRAR_PESSOA_DESTINO':

```bash
curl --request POST   --url http://localhost:8081/startJobs/JOB_MIGRAR_PESSOA_DESTINO/1 --header 'Content-Type: application/json' --data '{}'
```

______________________________________________________________________________________________________________________

#### Executando o Job JOB_VISUALIZAR_CICLO_DE_VIDA_SPRING_BATCH

Este job realiza o mesmo procedimento do JOB_MIGRAR_PESSOA_DESTINO, porém, nele foram incorporados vários listeners do
Spring.
Esses listeners auxiliam na compreensão do ciclo de vida completo de um job no Spring Batch, abrangendo as etapas do job
e as interações com seus ouvintes (listeners).

Para iniciar o Job 'JOB_VISUALIZAR_CICLO_DE_VIDA_SPRING_BATCH', utilize o seguinte comando curl:

```bash
curl --request POST   --url http://localhost:8081/startJobs/JOB_VISUALIZAR_CICLO_DE_VIDA_SPRING_BATCH/2 --header 'Content-Type: application/json' --data '{}'
```

______________________________________________________________________________________________________________________

#### Executando o Job JOB_UTILIZANDO_CONTEXT

Neste exemplo, investigamos a utilização do contexto no Spring Batch para esclarecer o funcionamento dos contextos de
step e job.
Em um job composto por 3 steps, inserimos informações tanto no **StepExecutionContext** quanto no **JobExecutionContext
**. No terceiro step,
ao tentar recuperar essas informações, notamos que o valor salvo no **StepExecutionContext** não está disponível, uma
vez que esse contexto existe apenas para a execução de um step específico.
No entanto, observamos que o **JobExecutionContext** mantém suas variáveis intactas.

Essa distinção é valiosa em cenários onde desejamos compartilhar informações entre etapas de um job e persistir dados
para possíveis reinicializações.
O JobExecutionContext pode ser útil para armazenar dados de alto nível, compartilhados entre várias etapas do job,
enquanto o StepExecutionContext é ideal para informações específicas de uma etapa individual.
Isso permite uma maior flexibilidade e controle ao gerenciar o fluxo de trabalho do Spring Batch, garantindo que os
dados estejam disponíveis onde e quando são necessários.

Para iniciar o Job 'JOB_UTILIZANDO_CONTEXT', utilize o seguinte comando curl:

```bash
curl --request POST   --url http://localhost:8081/startJobs/JOB_UTILIZANDO_CONTEXT/2 --header 'Content-Type: application/json' --data '{}'
```

______________________________________________________________________________________________________________________

#### Executando o Job JOB_MIGRAR_PESSOA_E_USUARIO_CRIANDO_VINCULO

Neste exemplo, adotamos um StepExecutionListener com a anotação @StepScope para renovar seu contexto a cada execução de
um novo step. Dentro deste StepExecutionListener, realizamos um carregamento de um Map<Long, Long> que armazena
informações do ID do banco antigo e do ID do banco novo. Com isso, incorporamos o StepExecutionListener via @Autowired
no nosso processer, concedendo-nos acesso a um mapeamento de IDs antigos para novos. Essa abordagem evita a necessidade
de acessar o banco a cada execução do processer para descobrir a origem do ID, reduzindo consideravelmente a frequência
de operações de entrada e saída (I/O) a cada iteração do processer.

Para iniciar o Job 'JOB_MIGRAR_PESSOA_E_USUARIO_CRIANDO_VINCULO', utilize o seguinte comando curl:

```bash
curl --request POST   --url http://localhost:8081/startJobs/JOB_MIGRAR_PESSOA_E_USUARIO_CRIANDO_VINCULO/55 --header 'Content-Type: application/json' --data '{}'
```

______________________________________________________________________________________________________________________


Agora, você pode facilmente agendar a execução dos Jobs desejados por meio de requisições HTTP.
Lembre-se de que a requisição HTTP apenas agendará a execução e não retornará uma resposta direta após o agendamento
bem-sucedido.
Para monitorar e compreender melhor a execução do projeto, esteja atento ao log da aplicação e às alterações nas tabelas
de banco de dados relacionadas aos Jobs.
Isso fornecerá insights essenciais sobre o progresso e o funcionamento do projeto.