-- Criar o banco de dados
CREATE DATABASE IF NOT EXISTS db_processos;

-- Criar usuário com senha
CREATE USER 'andré'@'%' IDENTIFIED BY '123456';

-- Dar permissões de admin (todas as permissões)
GRANT ALL PRIVILEGES ON *.* TO 'andré'@'%' WITH GRANT OPTION;

-- Garantir que o MySQL recarregue as permissões
FLUSH PRIVILEGES;
