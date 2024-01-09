# brq-test
Teste solicitado pelo cliente para saber os conhecimentos 

## Tecnologias
 - Spring boot
   - Java
 - Spring Jpa/Data
   - H2 
- Swagger  

## Possiveis melhorias
- Implementacao de spring security (token/jwt/auth 2.0)
- Inclusao de tabela de historico
- Criacao da tabela de usuario
  - Armazenando informacoes de senha, login, token, status
- As transferencias deveriam so ocorrem em horario comercial
  - As realizadas fora desse horario seriam agendadas para o dia seguinte (schelude)
- Implementacao de outros tipos de transferencia (pix, que poderia ocorrer em qualquer momento)
  - Seria necessario uma integracao com terceiros (acredito eu) 
- Estudar possibilidade de saque e deposito de dinheiro fisico
  - Parceria com o banco 24hrs
- Estudar possibilidade de parte de investimento

## Payloads

### Cadastrar pessoa fisica
POST | /natural-person/create

```
{
  "cpf": 45025283078,
  "name": "teste",
  "address": "av brasil",
  "password": "cafe123"
}
```
### Cadastrar pessoa juridica
POST | /juristic-person/create

```
{
  "cnpj": 99783073000164,
  "name": "teste - empresa",
  "address": "av brasil",
  "password": "cafe123"
}
```
### Realizar transferencia
POST | /account/transfer

```
{
  "sending": {
    "number": 0,
    "agency": 0
  },
  "receiving": {
    "number": 0,
    "agency": 0
  },
  "amount": 0
}
```
### Criacao de conta
POST | /account/person/{person_document}
