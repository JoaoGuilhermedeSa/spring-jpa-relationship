

# 🧙 RPG Relations Demo - Spring Boot POC

Esta é uma aplicação de demonstração desenvolvida em **Java 17** com **Spring Boot**, utilizando **JPA** e banco de dados em memória **H2** para simular um pequeno mundo de RPG.

Ela foi criada com o objetivo de mostrar os diferentes relacionamentos do JPA:
- `@OneToOne`
- `@OneToMany`
- `@ManyToOne`
- `@ManyToMany`
- Cascade e Orphan Removal

## 🔧 Tecnologias Utilizadas

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- Banco H2 (em memória)
- Maven

---

## ▶️ Como Rodar

```bash
git clone https://github.com/seu-usuario/rpg-relations-demo.git
cd rpg-relations-demo
mvn spring-boot:run
```

A aplicação estará disponível em:

http://localhost:8080

Console do banco de dados:

http://localhost:8080/h2-console

JDBC URL:

jdbc:h2:mem:rpgdb

Usuário e senha: sa
🧩 Entidades e Relacionamentos

    Character tem um Inventory (@OneToOne)

    Inventory possui vários Items (@OneToMany)

    Item pertence a um Inventory (@ManyToOne)

    Character participa de múltiplas Guilds (@ManyToMany)

    Character possui várias Quests (@OneToMany com cascade = REMOVE)

🔌 Endpoints REST (Testes via Postman ou Curl)
📜 Character
Criar personagem

POST /api/characters
Content-Type: application/json
```
{
  "name": "Aragorn"
}
```
Listar personagens

GET /api/characters

Buscar personagem por ID

GET /api/characters/{id}

Deletar personagem (deleta inventário e quests também)

DELETE /api/characters/{id}

🎒 Inventory e Items
Criar inventário com itens

POST /api/characters/{characterId}/inventory
Content-Type: application/json
```
{
  "items": [
    { "name": "Espada de Prata" },
    { "name": "Escudo de Carvalho" }
  ]
}
```
Ver inventário do personagem

GET /api/characters/{characterId}/inventory

🧱 Guild
Criar guilda

POST /api/guilds
Content-Type: application/json
```
{
  "name": "Os Guardiões da Luz"
}
```
Listar guildas

GET /api/guilds

Associar personagem a uma guilda

POST /api/characters/{characterId}/guilds/{guildId}

Ver membros de uma guilda

GET /api/guilds/{guildId}/members

📜 Quests
Criar missão para personagem

POST /api/characters/{characterId}/quests
Content-Type: application/json
```
{
  "title": "Salvar o Reino de Gondor",
  "status": "IN_PROGRESS"
}
```
Ver missões do personagem

GET /api/characters/{characterId}/quests

🔁 Comportamento em Cascade

    Ao criar um personagem com inventário, ele será salvo automaticamente (CascadeType.ALL)

    Ao remover um personagem:

        O inventário será removido automaticamente (orphanRemoval = true)

        As quests também serão removidas (CascadeType.REMOVE)

        Os itens e guildas continuam no banco se estiverem associados a outros objetos

💡 Exemplos de Testes via curl

Criar personagem:
```
curl -X POST http://localhost:8080/api/characters -H "Content-Type: application/json" -d '{"name":"Legolas"}'
```
Adicionar inventário e itens:
```
curl -X POST http://localhost:8080/api/characters/1/inventory -H "Content-Type: application/json" -d '{"items":[{"name":"Arco Élfico"},{"name":"Flecha de Gelo"}]}'
```

🧪 Testes manuais

Você pode utilizar ferramentas como Postman ou Insomnia para realizar os testes REST com facilidade.
📁 Estrutura do Projeto
```
src/
├── controller/      # Endpoints REST
├── dto/             # DTOs de entrada
├── model/           # Entidades JPA
├── repository/      # Interfaces do Spring Data JPA
├── service/         # Camada de negócio
└── resources/
    └── application.properties
```