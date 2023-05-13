# Cardápio Digital - Backend
Este é um projeto de um cardápio digital, voltada para estudos de uma criação Fullstack do zero.
## Requisitos 💻
- Para rodar esse projeto você precisa ter o Java 17 instalado na sua máquina.

<br>

## Gerando o JAR 🚀
- Navegue até a pasta raiz do projeto onde fica localizado o arquivo pom.xml
- Abra o console no diretorio e execute a seguinte linha abaixo:
```bash
mvn package
```

## Executando o JAR 📦
- Navegue até a pasta target
- Abra o console no diretorio e execute a seguinte linha abaixo:
```bash
java -jar cardapio-0.0.1-SNAPSHOT
```

# Rotas do Cardápio 📖

> ### Método POST 🕊️
#### Criar um item para o cardápio `.../food`

```json
{
 "title": "string",
 "image": "string",
 "price": "number"
}
```

> ### Método GET 📬
#### Listar todos os itens do cardápio `.../food`

<br>

> ### Método DELETE 🗑️
#### Deletar um item do cardápio `.../food/delete/{id}`

<br>

## Projetos relacionados 📁

- ### [Front-End](https://github.com/ogabrielalves/cardapio-digital-frontend) - FRONT END desenvolvido em React com TS.
