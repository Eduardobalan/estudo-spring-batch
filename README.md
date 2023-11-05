# Projeto de Estudo: Spring Batch

Este projeto é dedicado ao estudo do Spring Batch e sua integração na estrutura do Spring Framework. 
Aqui estão alguns dos principais conceitos e configurações que você encontrará neste projeto:

## Objetivos do Projeto
- Configuração de Múltiplos Bancos de Dados: Este projeto demonstra como configurar e utilizar vários bancos de dados para simular uma migração de dados de um banco DB_ORIGEM para um banco DB_DESTINO.
- Migração de Dados: Um dos principais componentes deste projeto é um JOB que realiza a migração de dados. Os dados são lidos de uma tabela de origem (tb_pessoa) e são inseridos nas tabelas de destino (tb_pessoa_destino e tb_usuario_destino).
- Ciclo de Vida do JOB: Além disso, incluímos um JOB dedicado a ajudar no entendimento do ciclo de vida completo de um JOB do Spring Batch, incluindo as etapas do JOB e as interações com seus listeners.

## Arquitetura
Para obter insights detalhados sobre a arquitetura do projeto, consulte a documentação disponível [aqui](docs/arquitetura.md).🏭

## Como executar em desenvolvimento ‍💻

Para configurar e executar o projeto em um ambiente de desenvolvimento, siga o guia detalhado disponível na documentação [aqui](docs/como-executar-dev.md).

## Referências

Para uma introdução teórica abrangente sobre o Spring Batch e sua organização de banco de dados, recomendamos a leitura da documentação oficial [aqui](https://docs.spring.io/spring-batch/docs/4.3.x/reference/html/domain.html#domainLanguageOfBatch)<br>
Além disso, para um entendimento mais profundo sobre os ciclos de transação do banco de dados e os listeners do Spring Batch, sugerimos a leitura dos seguintes artigos:
- [Transactions in Spring Batch – Part 1: The Basics](https://www.codecentric.de/wissens-hub/blog/transactions-in-spring-batch-part-1-the-basics)
- [Transactions in Spring Batch – Part 2: Restart, cursor based reading and listeners](https://www.codecentric.de/wissens-hub/blog/transactions-in-spring-batch-part-2-restart-cursor-based-reading-and-listeners)
- [Transactions in Spring Batch – Part 3: Skip and retry](https://www.codecentric.de/wissens-hub/blog/transactions-in-spring-batch-part-3-skip-and-retry)

Essas referências proporcionarão uma compreensão mais profunda e prática dos conceitos relacionados ao Spring Batch e ao processamento em lotes.
