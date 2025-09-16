drop database if exists DBAhorcado;
create database DBAhorcado;
use DBAhorcado;

create table Usuarios(
	codigoUsuario int auto_increment,
    nombre varchar(50),
    pass varchar(50),
    primary key pk_codigoUsuario(codigoUsuario)
);

create table Palabras(
	codigoPalabra int auto_increment primary key,
    palabra varchar(20),
    pista varchar(50)
);


delimiter $$
create procedure spInsertarUsuario(
	in p_nombre varchar(50),
    in p_pass varchar(50)
)
begin
	insert into Usuarios (nombre, pass) values (p_nombre, p_pass);
end $$
delimiter ;
call spInsertarUsuario(1, 1);

delimiter $$
create procedure spInsertarPalabras(
	in palabra varchar(50),
    in pista varchar(50)
)
begin
    insert into Palabras (palabra, pista) values
(palabra, pista);
end $$
delimiter ;

select * from Palabras;
call spInsertarPalabras('prueba', 'hoola');


