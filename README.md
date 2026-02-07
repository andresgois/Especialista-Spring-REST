# Especialista Spring Rest

### Perguntas
- Qual a diferença do Spring Boot para Spring Framework

- Spring MVC
  - Projeto do Spring para desenvolver para Web.
- O que é injeção de dependências?
  - 

## Comandos
- mysql -h 127.0.0.1 -P 3306 -u andre -p

### Desativar Flyway (se não quiser migração agora)
No application.properties:
spring.flyway.enabled=false

### Permite criar a tabela de migração mesmo que o banco já tenha dados, evitando erros de migração
spring.flyway.baseline-on-migrate=true
spring.flyway.baseline-version=1

outra solução é criar a tabela de migração manualmente:
DROP DATABASE db_processo;
CREATE DATABASE db_processo;

## Quando uma migration do flyway falha, o que fazer?
- Verificar o erro específico no log para entender o motivo da falha.
- Corrigir o erro no script de migração (por exemplo, corrigir a sintaxe SQL ou ajustar a lógica).
- Se necessário, limpar o estado do banco de dados para permitir uma nova tentativa de migração (por exemplo, excluir a tabela de migração ou restaurar o banco de dados para um estado anterior).
- Reexecutar a migração para garantir que o problema foi resolvido.
- deletar o registro da tabela de migração para a versão que falhou, para permitir uma nova tentativa de migração.
- outra forma é criar o arquivo flyway.properties e configurar o flyway para ignorar a versão que falhou:
- flyway.ignoreMissingMigrations=true
- digitar o seguinte comando:
  - ./mvnw flyway:repair -Dflyway.configFiles=src/main/resources/flyway.properties
