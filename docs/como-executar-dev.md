## Como executar 💻
Aqui está um guia básico para executar este projeto em seu ambiente de desenvolvimento:

### Passo 1 - Clone o Repositório
Primeiro, clone o projeto a partir do repositório do GitHub. Use um dos seguintes comandos, dependendo da forma de acesso escolhida:

```bash
git clone git@github.com:Eduardobalan/estudo-spring-batch.git
```
ou
```bash
git clone https://github.com/Eduardobalan/estudo-spring-batch.git
```

### Passo 2 - Configuração na Sua IDE
Certifique-se de importar o projeto para a sua IDE favorita. Isso permitirá que você faça as configurações necessárias e trabalhe com o código com facilidade.

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
- O MigrarCargaOrigemCommandLineRunner preencherá automaticamente o banco de dados de origem (DB_ORIGEM) com registros de exemplo.

### Passo 5 - Execução dos Jobs via Requisição HTTP

O projeto oferece um único ponto de entrada para a execução de Jobs:
- http://localhost:8081/startJobs/{jobName}/{jobId}

#### Substitua as variáveis Path:

**{jobName}**: Coloque o nome do job desejado ('JOB_MIGRAR_PESSOA_DESTINO' ou 'JOB_VISUALIZAR_CICLO_DE_VIDA_SPRING_BATCH').

**{jobId}**: Insira um identificador único. Se você usar o mesmo identificador, o Spring Batch tentará continuar a execução anterior.
______________________________________________________________________________________________________________________

#### Executando o Job JOB_MIGRAR_PESSOA_DESTINO
Realiza a migração de dados. Os dados são lidos de uma tabela de origem (tb_pessoa) e são inseridos nas tabelas de destino (tb_pessoa_destino e tb_usuario_destino).

Use o seguinte comando curl para iniciar o Job 'JOB_MIGRAR_PESSOA_DESTINO':
```bash
curl --request POST   --url http://localhost:8081/startJobs/JOB_MIGRAR_PESSOA_DESTINO/1 --header 'Content-Type: application/json' --data '{}'
```
______________________________________________________________________________________________________________________

#### Executando o Job JOB_VISUALIZAR_CICLO_DE_VIDA_SPRING_BATCH
Ajudar no entendimento do ciclo de vida completo de um JOB do Spring Batch, incluindo as etapas do JOB e as interações com seus listeners.

Para iniciar o Job 'JOB_VISUALIZAR_CICLO_DE_VIDA_SPRING_BATCH', utilize o seguinte comando curl:
```bash
curl --request POST   --url http://localhost:8081/startJobs/JOB_VISUALIZAR_CICLO_DE_VIDA_SPRING_BATCH/2 --header 'Content-Type: application/json' --data '{}'
```

______________________________________________________________________________________________________________________


Agora, você pode facilmente agendar a execução dos Jobs desejados por meio de requisições HTTP. 
Lembre-se de que a requisição HTTP apenas agendará a execução e não retornará uma resposta direta após o agendamento bem-sucedido. 
Para monitorar e compreender melhor a execução do projeto, esteja atento ao log da aplicação e às alterações nas tabelas de banco de dados relacionadas aos Jobs. 
Isso fornecerá insights essenciais sobre o progresso e o funcionamento do projeto.