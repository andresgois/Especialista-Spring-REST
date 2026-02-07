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
