
 create table if not exists usuario(
	username varchar(30),
    clave varchar(30),
    permiso varchar(30),
    constraint primary key(username),
    constraint check(permiso like 'Alumno' or 'Profesor' or 'Matriculador' or 'Administrador')
 );
 
 create table if not exists carrera(
	codigo int primary key not null auto_increment,
    nombre varchar(30)
 );
 
 create table if not exists curso(
	codigo int primary key not null auto_increment,
    nombre varchar(30),
	creditos int,
    horas_semanales int,
    carrera int,
    constraint foreign key(carrera) references carrera(codigo)
 );
 
 create table if not exists alumno(
	cedula int not null auto_increment,
    nombre varchar(30),
    telefono int,
    email varchar(30),
	carrera int,
    usuario varchar(30),
    primary key(cedula),
    foreign key (carrera) references carrera(codigo),
    foreign key (usuario) references usuario(username)
 );
 
 DELIMITER $$
 create procedure getUsuario(username varchar(30),clave varchar(30))
 begin
	select username, clave, permiso from mobiles.usuario 
		where usuario.username = username
			and usuario.clave = clave;
 end
 $$
 
DELIMITER $$
 create procedure getCursos()
 begin
	select curso.codigo, curso.nombre, curso.creditos, curso.horas_semanales, carrera.codigo as carrera
    from mobiles.curso, mobiles.carrera
    where curso.carrera = carrera.codigo
    ;
 end
 $$
 
DELIMITER $$
 create procedure getCarrera(codigo int)
 begin
	select codigo, nombre from mobiles.carrera 
		where carrera.codigo = codigo;
 end
 $$
 
 insert into usuario(username,clave,permiso) values('111','111','Alumno');
 insert into usuario(username,clave,permiso) values('222','222','Administrador');
 insert into carrera(nombre) values('Informatica');
 insert into carrera(nombre) values('Administracion');
 insert into curso(nombre, creditos , horas_semanales, carrera) values('Plataformas mobiles',4,4,1);
 insert into curso(nombre, creditos , horas_semanales, carrera) values('Inge 3',4,4,1);
 insert into curso(nombre, creditos , horas_semanales, carrera) values('Finanzas',4,4,2);
 insert into curso(nombre, creditos , horas_semanales, carrera) values('Contabilidad',4,4,2);

 
 call getUsuario('111','111');
 call getCursos();
 call getCarrera(1);
 
 drop table alumno;
 drop table curso;
 drop table carrera;
 drop table usuario;
 
 drop procedure getCursos;
 drop procedure getUsuario;
 drop procedure getCarrera;

