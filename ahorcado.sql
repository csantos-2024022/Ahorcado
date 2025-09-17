drop database if exists DB_Ahorcado;
create database DB_Ahorcado;
use DB_Ahorcado;

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
CALL spInsertarPalabras('ORDENADOR', 'Máquina para procesar información');
CALL spInsertarPalabras('TELEFONO', 'Dispositivo para comunicarte');
CALL spInsertarPalabras('GUITARRA', 'Instrumento de 6 cuerdas');
CALL spInsertarPalabras('BICICLETA', 'Vehículo de dos ruedas');
CALL spInsertarPalabras('SOL', 'Estrella que nos da luz y calor');
CALL spInsertarPalabras('LUNA', 'Satélite natural de la Tierra');
CALL spInsertarPalabras('PARAGUAS', 'Objeto para protegerte de la lluvia');
CALL spInsertarPalabras('CHOCOLATE', 'Dulce que se hace con cacao');
CALL spInsertarPalabras('BIBLIOTECA', 'Lugar donde hay muchos libros');
CALL spInsertarPalabras('RELOJ', 'Aparato que mide el tiempo');
Select * from Usuarios;





