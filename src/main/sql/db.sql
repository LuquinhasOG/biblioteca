CREATE DATABASE biblioteca;

CREATE TABLE cliente (
    id SERIAL PRIMARY KEY,
    nome character varying(100) NOT NULL,
    cpf character varying(11) NOT NULL UNIQUE,
    data_nascimento date NOT NULL,
	numero_telefone character varying(20) NOT NULL,
    rua character varying(100) NOT NULL,
    bairro character varying(100) NOT NULL,
    numero integer NOT NULL,
    complemento character varying(20),
	ativo BOOLEAN DEFAULT TRUE,
    CONSTRAINT cpf CHECK (length(cpf::text) = 11)
);

CREATE TABLE editora (
    id SERIAL PRIMARY KEY,
    nome character varying(100) COLLATE pg_catalog."default" NOT NULL
);

CREATE TABLE autor (
    id SERIAL PRIMARY KEY,
    nome character varying(100) COLLATE pg_catalog."default" NOT NULL
);

CREATE TABLE livro (
    id SERIAL PRIMARY KEY,
    nome character varying(100) COLLATE pg_catalog."default" NOT NULL,
    isbn character varying(13) COLLATE pg_catalog."default" NOT NULL,
    preco_aluguel double precision NOT NULL,
    sinopse character varying(500) COLLATE pg_catalog."default",
    id_editora integer NOT NULL,
	id_autor integer NOT NULL REFERENCES autor(id),
    quantidade_estoque integer DEFAULT 0,
	quantidade_disponivel integer DEFAULT 0,
    CONSTRAINT livro_id_editora_fkey FOREIGN KEY (id_editora)
        REFERENCES public.editora (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

CREATE TABLE estado_aluguel (
	id SERIAL PRIMARY KEY,
	descricao VARCHAR(20)
);

INSERT INTO estado_aluguel (id, descricao) VALUES
	(1, 'alugado'), (2, 'devolvido'), (3, 'atrasado'), (4, 'desolvido com atraso');

CREATE TABLE aluguel (
    id SERIAL PRIMARY KEY,
    id_cliente integer NOT NULL,
    id_livro integer NOT NULL,
    id_estado_aluguel INTEGER NOT NULL REFERENCES estado_aluguel(id) DEFAULT 1,
    data_aluguel date NOT NULL,
    data_devolucao date,
    renovacoes integer NOT NULL DEFAULT 0,
    CONSTRAINT aluguel_id_cliente_fkey FOREIGN KEY (id_cliente)
        REFERENCES public.cliente (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT aluguel_id_livro_fkey FOREIGN KEY (id_livro)
        REFERENCES public.livro (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

CREATE TABLE multa (
    id SERIAL PRIMARY KEY,
	id_aluguel INTEGER REFERENCES aluguel(id),
    valor double precision DEFAULT 0,
	pago BOOLEAN DEFAULT FALSE
);