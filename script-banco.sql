create table esportes (
id bigint(20) primary key auto_increment,
descricao varchar(50),
local varchar(50),
quantidade int);

create table ligas(
id bigint(20) primary key auto_increment,
descricao varchar(50),
pais varchar(50),
esporte bigint(20),
premio varchar(50),
foreign key(esporte) references esportes(id));

create table times (
id bigint(20) primary key auto_increment,
descricao varchar(50),
liga bigint(20),
tecnico varchar(50),
estadio varchar(50),
foreign key(liga) references ligas(id));

create table jogadores (
id bigint(20) primary key auto_increment,
nome varchar(50),
time bigint(20),
foreign key(time) references times(id));
