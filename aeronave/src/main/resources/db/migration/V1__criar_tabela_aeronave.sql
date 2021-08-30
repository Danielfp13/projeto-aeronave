CREATE TABLE aeronave(
	id INTEGER NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ) PRIMARY KEY,
	nome  VARCHAR (100) NOT NULL,
	marca VARCHAR(100) NOT NULL,
	ano INTEGER NOT NULL,
	descricao TEXT ,
	vendido BOOLEAN NOT NULL,
	created TIMESTAMP WITH TIME ZONE,
	updated TIMESTAMP WITH TIME ZONE
);