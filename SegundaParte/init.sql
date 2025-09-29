-- init.sql

-- Habilitar extensão para UUID
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Criar tabela
CREATE TABLE tb_users (
  id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  name VARCHAR(255) NOT NULL,
  age INT NOT NULL,
  password VARCHAR(255) NOT NULL,
  login VARCHAR(100) UNIQUE NOT NULL
);

-- Inserir dados iniciais
INSERT INTO tb_users (name, age, password, login)
VALUES
    ('Ana Silva', 30, 'senha123', 'ana.silva'),
    ('João Santos', 25, 'senha456', 'joao.santos');
