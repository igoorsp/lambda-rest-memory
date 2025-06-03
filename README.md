# Lambda Memory Integration Example

Este projeto é uma função AWS Lambda escrita em Java utilizando Quarkus com compilação nativa. O objetivo é consumir um endpoint externo de endereços fake, simulando delays configuráveis via variável de ambiente.

## Funcionalidades

- Processa eventos recebidos do SQS.
- Integra com serviço externo (FakerAPI ou Mocky).
- Suporte a timeout configurável no RestClient.
- Delay da chamada pode ser definido por variável de ambiente (`MOCKY_DELAY`).

## Pré-requisitos

- Java 17+
- Maven
- AWS CLI configurado
- Conta AWS para deploy da Lambda

## Variáveis de Ambiente

| Nome         | Obrigatório | Descrição                                             | Exemplo  |
|--------------|-------------|------------------------------------------------------|----------|
| MOCKY_DELAY  | Não         | Delay para resposta do serviço externo (ex: `60s`)   | `60s`    |

## Configuração do Endpoint

O endpoint externo pode ser configurado em `src/main/resources/application.properties`:

```properties
quarkus.rest-client.faker-api.url=https://run.mocky.io/v3/6e8d6e99-715f-41dc-96a7-c8e11b4cdd30
quarkus.rest-client.faker-api.connect-timeout=60000
quarkus.rest-client.faker-api.read-timeout=60000
