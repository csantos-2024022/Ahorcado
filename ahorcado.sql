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
    palabra varchar(255),
    pista varchar(255)
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
	in palabra varchar(255),
    in pista varchar(255)
)
begin
    insert into Palabras (palabra, pista) values
(palabra, pista);
end $$
delimiter ;

select * from Palabras;
CALL spInsertarPalabras('GUITARRA', '1. Instrumento de cuerda 2. Se rasguea 3. La tiene un músico famoso');
CALL spInsertarPalabras('FUEGO', '1. Calienta 2. Alumbra 3. Consume madera');
CALL spInsertarPalabras('OCEANO', '1. Gran masa de agua salada 2. Contiene vida marina 3. Cubre gran parte de la Tierra');
CALL spInsertarPalabras('SOL', '1. Es una estrella 2. Fuente de energía para la Tierra 3. Brilla en el día');
CALL spInsertarPalabras('LUNA', '1. Gira alrededor de la Tierra 2. Se ve de noche 3. Es un satélite');
CALL spInsertarPalabras('ARBOL', '1. Da sombra 2. Produce oxígeno 3. Hecho de madera');
CALL spInsertarPalabras('LIBRO', '1. Tiene hojas 2. Contiene historias 3. Se lee');
CALL spInsertarPalabras('AGUA', '1. Es vital para la vida 2. Sin color ni olor 3. Se bebe');
CALL spInsertarPalabras('TELEFONO', '1. sinonimo de movil 2. ring ring.... 3. tecnologia');
CALL spInsertarPalabras('LLUVIA', '1. Cae del cielo 2. Moja las calles 3. Riegas las plantas');
Select * from Usuarios;







