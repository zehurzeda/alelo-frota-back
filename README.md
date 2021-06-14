# Avaliação técnica Alelo Frota

Este projeto se trata da parte Backend da avaliação técnica para o processo seletivo da empresa Alelo

### Pre-requisitos

É necessário ter instalado as seguintes ferramentas para rodar o projeto:

* [Docker](https://www.docker.com/)

Este projeto depende da sua parte frontend que se encontra no seguinte repositório:

* [Frontend](https://github.com/zehurzeda/alelo-frota-front)

### Rodando o projeto

Para rodar o projeto somente é necessário possuir o docker portanto os seguintes passos são necessários:

#### Imagem

Na raiz do projeto rodar o seguinte comando para criar a imagem docker:

```
docker build -t alelo/frota-back-image .
```

#### Container

Na raiz do projeto rodar o seguinte comando para criar o container docker:

```
docker run --name alelo-frota-container -d -p 8080:8080 alelo/frota-back-image
```

Pronto temos a aplicação online!!

## Acessando a aplicação

Para acessar a aplicação é necessário a parte frontend.

Também foi feito o deploy da aplicação no Heroku, podemos acessa-la pelo link:

[heroku-app](https://vek-simulation-app.herokuapp.com/home)
