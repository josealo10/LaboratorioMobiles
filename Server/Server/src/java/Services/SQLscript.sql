 create table if not exists carrera(
	codigo int primary key not null auto_increment,
    nombre varchar(30)
    /*lista de cursos */
 );
 
 create table if not exists curso(
	codigo int primary key not null auto_increment,
    nombre varchar(30),
	creditos int,
    horas_semanales int
 );
 
 
 create table if not exists alumno(
	cedula int not null auto_increment,
    nombre varchar(30),
    telefono int,
    email varchar(30),
	carrera int,
    usuario int,
    primary key(cedula),
    foreign key (carrera) references carrera(codigo),
    foreign key (usuario) references usuario(username,clave)
 );
 
 create table if not exists usuario(
	username varchar(30),
    clave varchar(30),
    permiso varchar(30),
    constraint primary key(username),
    constraint check(permiso like 'Alumno' or 'Profesor' or 'Matriculador' or 'Administrador')
 );
 
 insert into usuario(username,clave, permiso) values('111','111','Alumno');
 
 select * from usuario;
 
 DELIMITER $$
 create procedure getUsuario(username varchar(30),clave varchar(30))
 begin
	select username, clave, permiso from mobiles.usuario 
		where usuario.username = username
			and usuario.clave = clave;
 end
 $$
 

 
 call getUsuario('111','111');

