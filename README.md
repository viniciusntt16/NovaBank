# NovaBank
Implementação de um projeto em java para implementar um sistema bancário simples

# NovaBank — Sistema Bancário em Java

Projeto prático desenvolvido em Java para simular um sistema bancário simples executado pelo terminal.

O sistema permite cadastrar clientes, criar contas bancárias e realizar operações como depósito, saque, transferência e consulta de histórico.

## Funcionalidades

- Cadastro de clientes com nome e CPF;
- Validação de nome vazio, CPF vazio, CPF inválido e CPF duplicado;
- Listagem de clientes;
- Busca de cliente por CPF;
- Criação de conta associada a um cliente existente;
- Geração automática de número de conta sequencial;
- Listagem de contas;
- Consulta de saldo;
- Depósito;
- Saque com validação de saldo suficiente;
- Transferência entre contas;
- Consulta de histórico de movimentações;
- Tratamento de entradas inválidas e exceções de negócio.

## Regras de negócio

- O CPF deve conter exatamente 11 dígitos numéricos;
- Não é permitido cadastrar clientes com nome ou CPF vazio;
- Não é permitido cadastrar dois clientes com o mesmo CPF;
- Uma conta só pode ser criada para um cliente já cadastrado;
- Toda conta começa com saldo igual a zero;
- Os valores monetários são armazenados em centavos usando o tipo `long`;
- Depósitos, saques e transferências devem possuir valor maior que zero;
- Um saque ou transferência só é permitido quando existe saldo suficiente;
- Não é permitido transferir valores para a mesma conta;
- Operações rejeitadas não alteram saldo nem histórico;
- Cada transferência gera um registro no histórico da conta de origem e outro na conta de destino.

## Valores em centavos

Os valores são informados e armazenados em centavos para evitar problemas de precisão com valores decimais.

| Valor informado | Valor em reais |
|---:|---:|
| `1` | R$ 0,01 |
| `100` | R$ 1,00 |
| `1050` | R$ 10,50 |
| `10000` | R$ 100,00 |
| `15000` | R$ 150,00 |

## Tecnologias utilizadas

- Java 21;
- Docker;
- Docker Compose;
- Git;
- GitHub.

## Estrutura do projeto

```text
NovaBank/
├── Evidencias/
│   ├── 01-build-docker.png
│   ├── 02-execucao-docker-run.png
│   └── ...
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── org/
│   │   │       └── example/
│   │   │           ├── Aplicacao.java
│   │   │           ├── Main.java
│   │   │           ├── Console/
│   │   │           │   ├── ClienteConsole.java
│   │   │           │   └── ContaConsole.java
│   │   │           ├── Entidades/
│   │   │           │   ├── Cliente.java
│   │   │           │   └── Conta.java
│   │   │           ├── Exceptions/
│   │   │           │   ├── ClienteException.java
│   │   │           │   └── ContaException.java
│   │   │           └── Service/
│   │   │               ├── ClienteService.java
│   │   │               └── ContaService.java
│   │   └── resources/
│   └── test/
├── target/
├── .dockerignore
├── .gitignore
├── docker-compose.yml
├── Dockerfile
├── pom.xml
└── README.md
```

## Pré-requisitos

Antes de executar o projeto, tenha instalado:

- Java 21;
- Docker Desktop;
- Docker Compose;
- Git, caso deseje clonar o repositório.

## Execução local com Java

Na raiz do projeto, compile a aplicação:

```bash
mvn clean compile
```

Depois execute a classe principal:

```bash
java -cp target/classes org.example.Aplicacao
```


> Caso o comando com `**` não funcione no seu terminal, compile os arquivos pela IDE ou utilize um comando compatível com o seu sistema operacional.

## Execução com Docker

### Construir a imagem

Na raiz do projeto, onde está o `Dockerfile`:

```bash
docker build -t novabank-java:1.0.0 .
```

### Executar o container

```bash
docker run --rm -it --name novabank-container novabank-java:1.0.0
```

Parâmetros utilizados:

- `--rm`: remove o container após o encerramento da aplicação;
- `-i`: mantém a entrada do teclado disponível;
- `-t`: cria um terminal interativo;
- `--name`: define um nome para o container.

## Execução com Docker Compose

### Construir a imagem

```bash
docker compose build
```

### Executar a aplicação

```bash
docker compose run --rm novabank
```

## Persistência dos dados

Os dados são mantidos apenas em memória durante a execução da aplicação.

Ao encerrar o programa ou remover o container, os clientes, contas, saldos e históricos são perdidos.

## Cenários testados

| Cenário | Resultado esperado |
|---|---|
| Cadastro com nome vazio | Operação recusada |
| Cadastro com CPF vazio ou inválido | Operação recusada |
| Cadastro com CPF duplicado | Operação recusada |
| Criação de conta para cliente inexistente | Operação recusada |
| Criação de conta para cliente existente | Conta criada com saldo zero |
| Depósito com valor positivo | Saldo atualizado |
| Depósito com valor zero ou negativo | Operação recusada |
| Saque com saldo suficiente | Saldo atualizado |
| Saque com saldo insuficiente | Operação recusada |
| Transferência entre contas diferentes | Saldo debitado na origem e creditado no destino |
| Transferência para a mesma conta | Operação recusada |
| Consulta de histórico | Exibe apenas operações concluídas |
| Execução com Docker | Aplicação iniciada no container |
| Execução com Docker Compose | Aplicação iniciada no container |

## Evidências

As evidências dos testes realizados com Docker e Docker Compose estão disponíveis na pasta:

```text
evidencias/
```

Exemplos de evidências incluídas:

- Build da imagem Docker;
- Execução da aplicação com `docker run`;
- Execução da aplicação com `docker compose run`;
- Cadastro de cliente;

## Autor

Desenvolvido por: **Vinícius Aguiar Basilio**

GitHub: **https:https://github.com/viniciusntt16**


### Saidas do console

========== NOVABANK ==========

1 - Cadastrar cliente

2 - Listar clientes

3 - Buscar cliente por CPF

4 - Criar conta

5 - Listar contas

6 - Consultar saldo

7 - Realizar depósito

8 - Realizar saque

9 - Realizar transferência

10 - Consultar histórico

0 - Sair

Escolha uma opção: 1

Nome:

CPF: 12345678910

Erro: O nome não pode estar vazio.

========== NOVABANK ==========

1 - Cadastrar cliente

2 - Listar clientes

3 - Buscar cliente por CPF

4 - Criar conta

5 - Listar contas

6 - Consultar saldo

7 - Realizar depósito

8 - Realizar saque

9 - Realizar transferência

10 - Consultar histórico

0 - Sair

Escolha uma opção: 1

Nome: Vinicius

CPF: 12345678910

Cliente cadastrado com sucesso!

Nome: Vinicius

CPF: 12345678910

========== NOVABANK ==========

1 - Cadastrar cliente

2 - Listar clientes

3 - Buscar cliente por CPF

4 - Criar conta

5 - Listar contas

6 - Consultar saldo

7 - Realizar depósito

8 - Realizar saque

9 - Realizar transferência

10 - Consultar histórico

0 - Sair

Escolha uma opção: 1

Nome: Lukas

CPF: 12345678910

Erro: Já existe um cliente cadastrado com esse CPF.

========== NOVABANK ==========

1 - Cadastrar cliente

2 - Listar clientes

3 - Buscar cliente por CPF

4 - Criar conta

5 - Listar contas

6 - Consultar saldo

7 - Realizar depósito

8 - Realizar saque

9 - Realizar transferência

10 - Consultar histórico

0 - Sair

Escolha uma opção: 2

===== CLIENTES CADASTRADOS =====

Nome: Vinicius

CPF: 12345678910

========== NOVABANK ==========

1 - Cadastrar cliente

2 - Listar clientes

3 - Buscar cliente por CPF

4 - Criar conta

5 - Listar contas

6 - Consultar saldo

7 - Realizar depósito

8 - Realizar saque 

9 - Realizar transferência

10 - Consultar histórico

0 - Sair

Escolha uma opção: 4

Informe o CPF do cliente: 12345678911

Cliente não encontrado. Cadastre o cliente antes de criar uma conta.

========== NOVABANK ==========

1 - Cadastrar cliente

2 - Listar clientes

3 - Buscar cliente por CPF

4 - Criar conta

5 - Listar contas

6 - Consultar saldo

7 - Realizar depósito

8 - Realizar saque

9 - Realizar transferência

10 - Consultar histórico

0 - Sair

Escolha uma opção: 5

Não há contas cadastradas.

========== NOVABANK ==========

1 - Cadastrar cliente

2 - Listar clientes

3 - Buscar cliente por CPF

4 - Criar conta

5 - Listar contas

6 - Consultar saldo

7 - Realizar depósito

8 - Realizar saque

9 - Realizar transferência

10 - Consultar histórico

0 - Sair

Escolha uma opção: 4

Informe o CPF do cliente: 12345678910

Conta criada com sucesso!

Número da conta: 1001

Titular: Vinicius

Saldo: R$ 0,00

========== NOVABANK ==========

1 - Cadastrar cliente

2 - Listar clientes

3 - Buscar cliente por CPF

4 - Criar conta

5 - Listar contas

6 - Consultar saldo

7 - Realizar depósito

8 - Realizar saque

9 - Realizar transferência

10 - Consultar histórico

0 - Sair

Escolha uma opção: 5

===== CONTAS CADASTRADAS =====

Número da conta: 1001

Titular: Vinicius

Saldo: R$ 0,00

========== NOVABANK ==========

1 - Cadastrar cliente

2 - Listar clientes

3 - Buscar cliente por CPF

4 - Criar conta

5 - Listar contas

6 - Consultar saldo

7 - Realizar depósito

8 - Realizar saque

9 - Realizar transferência

10 - Consultar histórico

0 - Sair

Escolha uma opção: 7

Número da conta: 10000

Valor do depósito em centavos: 5

Erro: Conta número 10000 não encontrada.

========== NOVABANK ==========

1 - Cadastrar cliente

2 - Listar clientes

3 - Buscar cliente por CPF

4 - Criar conta

5 - Listar contas

6 - Consultar saldo

7 - Realizar depósito

8 - Realizar saque

9 - Realizar transferência

10 - Consultar histórico

0 - Sair

Escolha uma opção: 5

===== CONTAS CADASTRADAS =====

Número da conta: 1001

Titular: Vinicius

Saldo: R$ 0,00

------------------------------

========== NOVABANK ==========

1 - Cadastrar cliente

2 - Listar clientes

3 - Buscar cliente por CPF

4 - Criar conta

5 - Listar contas

6 - Consultar saldo

7 - Realizar depósito

8 - Realizar saque

9 - Realizar transferência

10 - Consultar histórico

0 - Sair

Escolha uma opção: 7

Número da conta: 1001

Valor do depósito em centavos: 10000

Depósito realizado com sucesso!

Saldo atual: R$ 100,00

========== NOVABANK ==========

1 - Cadastrar cliente

2 - Listar clientes

3 - Buscar cliente por CPF

4 - Criar conta

5 - Listar contas

6 - Consultar saldo

7 - Realizar depósito

8 - Realizar saque

9 - Realizar transferência

10 - Consultar histórico

0 - Sair

Escolha uma opção: 5

===== CONTAS CADASTRADAS =====

Número da conta: 1001

Titular: Vinicius

Saldo: R$ 100,00

========== NOVABANK ==========

1 - Cadastrar cliente

2 - Listar clientes

3 - Buscar cliente por CPF

4 - Criar conta

5 - Listar contas

6 - Consultar saldo

7 - Realizar depósito

8 - Realizar saque

9 - Realizar transferência

10 - Consultar histórico

0 - Sair

Escolha uma opção: 8

Número da conta: 1001

Valor do saque em centavos: 3000

Saque realizado com sucesso!

Saldo atual: R$ 70,00

========== NOVABANK ==========

1 - Cadastrar cliente

2 - Listar clientes

3 - Buscar cliente por CPF

4 - Criar conta

5 - Listar contas

6 - Consultar saldo

7 - Realizar depósito

8 - Realizar saque

9 - Realizar transferência

10 - Consultar histórico

0 - Sair

Escolha uma opção: 8

Número da conta: 1001

Valor do saque em centavos: 8000

Erro: Saldo insuficiente.

========== NOVABANK ==========

1 - Cadastrar cliente

2 - Listar clientes

3 - Buscar cliente por CPF

4 - Criar conta

5 - Listar contas

6 - Consultar saldo

7 - Realizar depósito

8 - Realizar saque

9 - Realizar transferência

10 - Consultar histórico

0 - Sair

Escolha uma opção: 8

Número da conta: 1001

Valor do saque em centavos: -1000

Erro: O valor do saque deve ser maior que zero.

========== NOVABANK ==========

1 - Cadastrar cliente

2 - Listar clientes

3 - Buscar cliente por CPF

4 - Criar conta

5 - Listar contas

6 - Consultar saldo

7 - Realizar depósito

8 - Realizar saque

9 - Realizar transferência

10 - Consultar histórico

0 - Sair

Escolha uma opção: 1

Nome: Lukas

CPF: 12345678911

Cliente cadastrado com sucesso!

Nome: Lukas

CPF: 12345678911

========== NOVABANK ==========

1 - Cadastrar cliente

2 - Listar clientes

3 - Buscar cliente por CPF

4 - Criar conta

5 - Listar contas

6 - Consultar saldo

7 - Realizar depósito

8 - Realizar saque

9 - Realizar transferência

10 - Consultar histórico

0 - Sair

Escolha uma opção: 4

Informe o CPF do cliente: 12345678911

Conta criada com sucesso!

Número da conta: 1002

Titular: Lukas

Saldo: R$ 0,00

========== NOVABANK ==========
1 - Cadastrar cliente


2 - Listar clientes

3 - Buscar cliente por CPF

4 - Criar conta

5 - Listar contas

6 - Consultar saldo

7 - Realizar depósito

8 - Realizar saque

9 - Realizar transferência

10 - Consultar histórico

0 - Sair

Escolha uma opção: 9

Número da conta de origem: 1001

Número da conta de destino: 1002

Valor da transferência em centavos: 2000

Transferência realizada com sucesso!

========== NOVABANK ==========

1 - Cadastrar cliente

2 - Listar clientes

3 - Buscar cliente por CPF

4 - Criar conta

5 - Listar contas

6 - Consultar saldo

7 - Realizar depósito

8 - Realizar saque

9 - Realizar transferência

10 - Consultar histórico

0 - Sair

Escolha uma opção: 5

===== CONTAS CADASTRADAS =====

Número da conta: 1001

Titular: Vinicius

Saldo: R$ 50,00

Número da conta: 1002

Titular: Lukas

Saldo: R$ 20,00

------------------------------

========== NOVABANK ==========

1 - Cadastrar cliente

2 - Listar clientes

3 - Buscar cliente por CPF

4 - Criar conta

5 - Listar contas

6 - Consultar saldo

7 - Realizar depósito

8 - Realizar saque

9 - Realizar transferência

10 - Consultar histórico

0 - Sair

Escolha uma opção: 9

Número da conta de origem: 1001

Número da conta de destino: 1002

Valor da transferência em centavos: 1000

Erro: Conta número 1002 não encontrada.

========== NOVABANK ==========

1 - Cadastrar cliente

2 - Listar clientes

3 - Buscar cliente por CPF

4 - Criar conta

5 - Listar contas

6 - Consultar saldo

7 - Realizar depósito

8 - Realizar saque

9 - Realizar transferência

10 - Consultar histórico

0 - Sair

Escolha uma opção: 9

Número da conta de origem: 1001

Número da conta de destino: 1001

Valor da transferência em centavos: 1000

Erro: Não é possível transferir para a mesma conta.

========== NOVABANK ==========

1 - Cadastrar cliente

2 - Listar clientes

3 - Buscar cliente por CPF

4 - Criar conta

5 - Listar contas

6 - Consultar saldo

7 - Realizar depósito

8 - Realizar saque

9 - Realizar transferência

10 - Consultar histórico

0 - Sair

Escolha uma opção: 11

Opção inválida.

========== NOVABANK ==========

1 - Cadastrar cliente

2 - Listar clientes

3 - Buscar cliente por CPF

4 - Criar conta

5 - Listar contas

6 - Consultar saldo

7 - Realizar depósito

8 - Realizar saque

9 - Realizar transferência

10 - Consultar histórico

0 - Sair

Escolha uma opção: 10

Informe o número da conta: 1001

===== HISTÓRICO DA CONTA 1001 =====

DEPÓSITO | Valor: 10000 centavos | Saldo: 10000 centavos

SAQUE | Valor: 3000 centavos | Saldo: 7000 centavos

TRANSFERÊNCIA ENVIADA | Valor: 2000 centavos | Destino: 1002 | Saldo: 5000 centavos

========== NOVABANK ==========

1 - Cadastrar cliente

2 - Listar clientes

3 - Buscar cliente por CPF

4 - Criar conta

5 - Listar contas

6 - Consultar saldo

7 - Realizar depósito

8 - Realizar saque

9 - Realizar transferência

10 - Consultar histórico

0 - Sair

Escolha uma opção: e

Digite uma opção numérica válida.

========== NOVABANK ==========

1 - Cadastrar cliente

2 - Listar clientes

3 - Buscar cliente por CPF

4 - Criar conta

5 - Listar contas

6 - Consultar saldo

7 - Realizar depósito

8 - Realizar saque

9 - Realizar transferência

10 - Consultar histórico

0 - Sair

Escolha uma opção: 11

Opção inválida.

========== NOVABANK ==========

1 - Cadastrar cliente

2 - Listar clientes

3 - Buscar cliente por CPF

4 - Criar conta

5 - Listar contas

6 - Consultar saldo

7 - Realizar depósito

8 - Realizar saque

9 - Realizar transferência

10 - Consultar histórico

0 - Sair

Escolha uma opção: 8

Número da conta: e

Número da conta ou valor inválido.

========== NOVABANK ==========

1 - Cadastrar cliente

2 - Listar clientes

3 - Buscar cliente por CPF

4 - Criar conta

5 - Listar contas

6 - Consultar saldo

7 - Realizar depósito

8 - Realizar saque

9 - Realizar transferência

10 - Consultar histórico

0 - Sair

Escolha uma opção: 8

Número da conta: 1001

Valor do saque em centavos: e

Número da conta ou valor inválido.