create table tb_cidade (
                           estado_id bigint not null,
                           id bigint not null auto_increment,
                           nome varchar(255) not null,
                           primary key (id)
) engine=InnoDB default charset=utf8;

create table tb_cozinha (
                            id bigint not null auto_increment,
                            nome varchar(255) not null,
                            primary key (id)
) engine=InnoDB default charset=utf8;

create table tb_estado (
                           id bigint not null auto_increment,
                           nome varchar(255) not null,
                           primary key (id)
) engine=InnoDB default charset=utf8;

create table tb_forma_pagamento (
                                    id bigint not null auto_increment,
                                    descricao varchar(255) not null,
                                    primary key (id)
) engine=InnoDB default charset=utf8;

create table tb_grupo (
                          id bigint not null auto_increment,
                          nome varchar(255) not null,
                          primary key (id)
) engine=InnoDB default charset=utf8;

create table tb_grupo_permissoes (
                                     grupo_id bigint not null,
                                     permissao_id bigint not null
) engine=InnoDB default charset=utf8;

create table tb_permissao (
                              id bigint not null auto_increment,
                              descricao varchar(255) not null,
                              nome varchar(255) not null,
                              primary key (id)
) engine=InnoDB default charset=utf8;

create table tb_produto (
                            ativo bit not null,
                            preco decimal(38, 2) not null,
                            id bigint not null auto_increment,
                            restaurante_id bigint,
                            descricao varchar(255) not null,
                            nome varchar(255) not null,
                            primary key (id)
) engine=InnoDB default charset=utf8;

create table tb_restaurante (
                                taxa_frete decimal(38, 2) not null,
                                cozinha_id bigint not null,
                                data_atualizacao datetime(6) not null,
                                data_cadastro datetime(6) not null,
                                endereco_cidade_id bigint,
                                id bigint not null auto_increment,
                                endereco_bairro varchar(255),
                                endereco_cep varchar(255),
                                endereco_complemento varchar(255),
                                endereco_logradouro varchar(255),
                                endereco_numero varchar(255),
                                nome varchar(255) not null,
                                primary key (id)
) engine=InnoDB default charset=utf8;

create table tb_restaurante_forma_pagamento (
                                                forma_pagamento_id bigint not null,
                                                restaurante_id bigint not null
) engine=InnoDB default charset=utf8;

create table tb_restaurante_produtos (
                                         produtos_id bigint not null,
                                         restaurante_id bigint not null
) engine=InnoDB default charset=utf8;

create table tb_usuario (
                            data_cadastro datetime(6) not null,
                            id bigint not null auto_increment,
                            email varchar(255) not null,
                            nome varchar(255) not null,
                            senha varchar(255) not null,
                            primary key (id)
) engine=InnoDB default charset=utf8;

create table tb_usuarios_grupo (
                                   grupo_id bigint not null,
                                   usuario_id bigint not null
) engine=InnoDB default charset=utf8;

alter table
    tb_restaurante_produtos
    add
        constraint uni_rest_prod unique (produtos_id);

alter table
    tb_cidade
    add
        constraint fk_cidade_estado foreign key (estado_id) references tb_estado (id);

alter table
    tb_grupo_permissoes
    add
        constraint fk_grupo_permissoes_permissao foreign key (permissao_id) references tb_permissao (id);

alter table
    tb_grupo_permissoes
    add
        constraint fk_grupo_permissoes_grupo foreign key (grupo_id) references tb_grupo (id);

alter table
    tb_produto
    add
        constraint fk_produto_restaurante foreign key (restaurante_id) references tb_restaurante (id);

alter table
    tb_restaurante
    add
        constraint fk_restaurante_cozinha foreign key (cozinha_id) references tb_cozinha (id);

alter table
    tb_restaurante
    add
        constraint fk_restaurante_cidade foreign key (endereco_cidade_id) references tb_cidade (id);

alter table
    tb_restaurante_forma_pagamento
    add
        constraint fk_restaurante_forma_pagamento_forma_pagamento foreign key (forma_pagamento_id) references tb_forma_pagamento (id);

alter table
    tb_restaurante_forma_pagamento
    add
        constraint fk_restaurante_forma_pagamento_restaurante foreign key (restaurante_id) references tb_restaurante (id);

alter table
    tb_restaurante_produtos
    add
        constraint fk_restaurante_produtos_produto foreign key (produtos_id) references tb_produto (id);

alter table
    tb_restaurante_produtos
    add
        constraint fk_restaurante_produtos_restaurante foreign key (restaurante_id) references tb_restaurante (id);

alter table
    tb_usuarios_grupo
    add
        constraint fk_usuarios_grupo_grupo foreign key (grupo_id) references tb_grupo (id);

alter table
    tb_usuarios_grupo
    add
        constraint fk_usuarios_grupo_usuario foreign key (usuario_id) references tb_usuario (id);