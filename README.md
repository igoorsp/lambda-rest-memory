# Lambda Memory Integration Example

Este projeto é uma função AWS Lambda escrita em Java utilizando Quarkus com compilação nativa.  
O objetivo é consumir um endpoint externo de endereços fake, simulando delays configuráveis via variável de ambiente.

## Funcionalidades

- Integra com serviço externo (FakerAPI ou Mocky).
- Suporte a timeout configurável no RestClient.
- Delay da chamada pode ser definido por variável de ambiente (`MOCKY_DELAY`).

## Pré-requisitos

- Java 17+
- Maven
- AWS CLI configurado
- Conta AWS para deploy da Lambda

## Variáveis de Ambiente

| Nome         | Obrigatório | Descrição                                           | Exemplo |
|--------------|-------------|----------------------------------------------------|---------|
| MOCKY_DELAY  | Não         | Delay para resposta do serviço externo (ex: `60s`) | `60s`   |

## Configuração do Endpoint

O endpoint externo pode ser configurado em `src/main/resources/application.properties`:

```properties
quarkus.rest-client.faker-api.url=https://run.mocky.io/v3/6e8d6e99-715f-41dc-96a7-c8e11b4cdd30
quarkus.rest-client.faker-api.connect-timeout=60000
quarkus.rest-client.faker-api.read-timeout=60000
```
> O parâmetro `mocky-delay` é enviado automaticamente pelo código Java usando a variável de ambiente.

## Como Buildar em Nativo

```sh
mvn clean package -Pnative -Dquarkus.native.container-build=true -Dquarkus.native.builder-image=quay.io/quarkus/ubi-quarkus-mandrel-builder-image:23.0.0.0-Final-java17
```

O resultado será um arquivo na pasta `target/` pronto para ser enviado para o AWS Lambda.

## Deploy na AWS Lambda

1. Faça upload do binário gerado pelo Quarkus Native.
2. Configure a variável de ambiente `MOCKY_DELAY` conforme desejado.
3. Configure as permissões e triggers SQS conforme necessário.

## Exemplo de Uso

Ao receber um evento, a função irá:

* Buscar o valor de delay configurado (ou usar `60s` por padrão).
* Fazer a requisição para o endpoint externo, aguardando o tempo simulado.
* Logar o endereço recebido.

## Estrutura Principal do Projeto

* `LambdaMemory.java` — Handler principal da Lambda.
* `FakerApiClient.java` — Interface do RestClient para o endpoint externo.
* `AddressResponse.java` — DTO para a resposta do serviço de endereço fake.
* `application.properties` — Configurações do RestClient.

## Observações

* O projeto está pronto para compilação nativa e uso em produção.
* O uso de `@RegisterForReflection` garante funcionamento em native-image com serialização JSON.
``
