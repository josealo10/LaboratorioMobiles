drop database if exists mobiles;
create database if not exists mobiles;
use mobiles;

 create table if not exists mobiles.usuario(
	username varchar(30),
    clave varchar(30),
    permiso varchar(30),
    constraint primary key(username),
    constraint check(permiso in ('Alumno', 'Profesor', 'Matriculador', 'Administrador'))
 );

 create table if not exists mobiles.carrera(
	codigo int primary key not null auto_increment,
    nombre varchar(30)
 );
 
 create table if not exists mobiles.curso(
	codigo int primary key not null auto_increment,
    nombre varchar(30),
	creditos int,
    horas_semanales int,
    carrera int,
    constraint foreign key(carrera) references carrera(codigo)
 );
 
 create table if not exists mobiles.alumno(
	cedula int not null,
    nombre varchar(30),
    telefono int,
    email varchar(30),
	carrera int not null,
    usuario varchar(30) not null,
    primary key(cedula),
    foreign key (carrera) references carrera(codigo),
    foreign key (usuario) references usuario(username)
 );
 
 create table if not exists mobiles.cursosMatriculados(
	alumno int not null,
    curso int not null,
    primary key (alumno,curso),
    foreign key (alumno) references alumno(cedula),
    foreign key (curso) references curso(codigo)
 );
 
 DELIMITER $$
 create procedure mobiles.getUsuario(username varchar(30),clave varchar(30))
 begin
	select username, clave, permiso from mobiles.usuario 
		where usuario.username = username
			and usuario.clave = clave;
 end
 $$
 
  DELIMITER $$
 create procedure mobiles.insertUsuario(username varchar(30 ), clave varchar(30),permiso varchar(30))
 begin
	insert into mobiles.usuario(username,clave,permiso) values(username,clave,permiso);
 end
 $$
 
DELIMITER $$
 create procedure mobiles.getCarreras()
 begin
	select * from mobiles.carrera;
 end
 $$
 
 DELIMITER $$
 create procedure mobiles.getCursos()
 begin
	select curso.codigo, curso.nombre, curso.creditos, curso.horas_semanales, carrera.codigo as carrera
    from mobiles.curso, mobiles.carrera
    where curso.carrera = carrera.codigo
    ;
 end
 $$

 DELIMITER $$
 create procedure mobiles.insertCurso(nombre varchar(30 ), creditos int, horas_semanales int , carrera int)
 begin
	insert into mobiles.curso(nombre, creditos , horas_semanales, carrera) 
    values(nombre, creditos , horas_semanales, carrera);
 end
 $$
 
  DELIMITER $$
 create procedure mobiles.updateCurso(codigo int, nombre varchar(30 ), creditos int, horas_semanales int , carrera int)
 begin
	update mobiles.curso 
    set curso.nombre = nombre, 
    curso.creditos = creditos , 
    curso.horas_semanales = horas_semanales, 
    curso.carrera = carrera 
    where curso.codigo = codigo;
 end
 $$
 
 
  DELIMITER $$
 create procedure mobiles.deleteCurso(codigo int)
 begin
	delete from mobiles.cursosmatriculados where mobiles.cursosmatriculados.curso = codigo;
	delete from mobiles.curso where curso.codigo = codigo;
 end
 $$
 
  DELIMITER $$
 create procedure mobiles.getAlumnos()
 begin
	select * from mobiles.alumno;
 end
 $$

  DELIMITER $$
 create procedure mobiles.getAlumno(cedula int)
 begin
	select * from mobiles.alumno
    where mobiles.alumno.cedula = cedula;
 end
 $$
 
  DELIMITER $$
 create procedure mobiles.getAlumnoWithUsername(username varchar(30))
 begin
	select mobiles.alumno.cedula from mobiles.alumno, mobiles.usuario
    where mobiles.alumno.usuario = mobiles.usuario.username
    and mobiles.usuario.username = username;
 end
 $$
 
  DELIMITER $$
 create procedure mobiles.getCursosMatriculados(cedula int)
 begin
	select mobiles.curso.codigo,
    mobiles.curso.nombre,
    mobiles.curso.horas_semanales,
    mobiles.curso.creditos,
    mobiles.carrera.nombre 
		from mobiles.alumno, mobiles.curso,mobiles.cursosmatriculados,mobiles.carrera
			where mobiles.alumno.cedula = mobiles.cursosmatriculados.alumno
			and mobiles.cursosmatriculados.curso = mobiles.curso.codigo
			and mobiles.curso.carrera = mobiles.carrera.codigo
				and mobiles.alumno.cedula = cedula;
 end
 $$

 DELIMITER $$
 create procedure mobiles.insertAlumno(cedula int, nombre varchar(30 ), telefono int, email varchar(30) , carrera int, usuario varchar(30))
 begin
	insert into mobiles.alumno(cedula, nombre, telefono , email, carrera, usuario) 
    values(cedula, nombre, telefono , email, carrera, usuario);
 end
 $$
 
  DELIMITER $$
 create procedure mobiles.updateAlumno(cedula int, nombre varchar(30 ), telefono int, email varchar(30) , carrera int)
 begin
	update mobiles.alumno 
    set alumno.nombre = nombre, 
    alumno.telefono = telefono , 
    alumno.email = email, 
    alumno.carrera = carrera 
    where alumno.cedula = cedula;
 end
 $$
 
  DELIMITER $$
 create procedure mobiles.deleteAlumno(cedula int)
 begin
	delete from mobiles.cursosmatriculados where mobiles.cursosmatriculados.alumno = cedula;
    select @usuario := mobiles.alumno.usuario 
		from mobiles.alumno 
        where mobiles.alumno.cedula = cedula;
	delete from mobiles.alumno where alumno.cedula = cedula;
    delete from mobiles.usuario where mobiles.usuario.username = @usuario;
 end
 $$
 
DELIMITER $$
 create procedure mobiles.getCarrera(codigo int)
 begin
	select codigo, nombre from mobiles.carrera 
		where carrera.codigo = codigo;
 end
 $$
 
 insert into mobiles.usuario(username,clave,permiso) values('111','111','Alumno');
 insert into mobiles.usuario(username,clave,permiso) values('333','333','Alumno');
 insert into mobiles.usuario(username,clave,permiso) values('222','222','Administrador');
 
 insert into mobiles.carrera(nombre) values('Informática');
 insert into mobiles.carrera(nombre) values('Administración');
 
 insert into mobiles.curso(nombre, creditos , horas_semanales, carrera) values('Plataformas moviles',4,4,1);
 insert into mobiles.curso(nombre, creditos , horas_semanales, carrera) values('Ingeniería 3',4,4,1);
 insert into mobiles.curso(nombre, creditos , horas_semanales, carrera) values('Fundamentos',4,4,1);
 insert into mobiles.curso(nombre, creditos , horas_semanales, carrera) values('Programación 1',4,4,1);
 insert into mobiles.curso(nombre, creditos , horas_semanales, carrera) values('Redes',4,4,1);
 
 insert into mobiles.curso(nombre, creditos , horas_semanales, carrera) values('Finanzas',4,4,2);
 insert into mobiles.curso(nombre, creditos , horas_semanales, carrera) values('Contabilidad',4,4,2);
 
 insert into mobiles.alumno(cedula,nombre,telefono,email,carrera,usuario) values(402420953,'Alonso', 89620145,'alonso@gmail.com',1,111);
 insert into mobiles.alumno(cedula,nombre,telefono,email,carrera,usuario) values(171717,'Fazio', 89620145,'fazio@gmail.com',1,333);
 
 insert into mobiles.cursosMatriculados(alumno,curso) values(402420953,1);
 insert into mobiles.cursosMatriculados(alumno,curso) values(402420953,2);
 insert into mobiles.cursosMatriculados(alumno,curso) values(402420953,3);
 
 insert into mobiles.cursosMatriculados(alumno,curso) values(171717,3);
 insert into mobiles.cursosMatriculados(alumno,curso) values(171717,4);
 insert into mobiles.cursosMatriculados(alumno,curso) values(171717,5);

 call insertCurso('Soporte', '4', '4','1')
 call mobiles.getUsuario('101','111');
 call mobiles.insertUsuario('444','444','Alumno');
 call mobiles.getCursos();
 call mobiles.getCarreras();
 call mobiles.getCarrera(1);
 call mobiles.getAlumnos();
 call mobiles.getAlumno(402420953);
 call mobiles.getAlumnoWithUsername('111');
 call mobiles.getCursosMatriculados(402420953);
 call mobiles.insertCurso('hola',4,5,4);
 call mobiles.deleteCurso(1);
 call mobiles.updateCurso(4, 'Funda', 4, 5, 1);
 call mobiles.insertAlumno(402420, 'Alonso', '89620145', 'josealonso@gmail.com',1,111);
 call mobiles.updateAlumno(40256420, 'Pedro', 89620145, 'josealonso@gmail.com',1);
 call mobiles.deleteAlumno(171717);
 
 
 drop table mobiles.cursosMatriculados;
 drop table mobiles.alumno;
 drop table mobiles.curso;
 drop table mobiles.carrera;
 drop table mobiles.usuario;
 
 drop procedure mobiles.getUsuario;
 drop procedure mobiles.insertUsuario;
 
 drop procedure mobiles.getCarrera;
 drop procedure mobiles.getCarreras;
 
 drop procedure mobiles.getAlumnos;
 drop procedure mobiles.getAlumno;
 drop procedure mobiles.getAlumnoWithUsername;
 drop procedure mobiles.insertAlumno;
 drop procedure mobiles.deleteAlumno;
 drop procedure mobiles.updateAlumno;
 drop procedure mobiles.getCursosMatriculados;
 
 drop procedure mobiles.getCursos;
 drop procedure mobiles.insertCurso;
 drop procedure mobiles.deleteCurso;
 drop procedure mobiles.updateCurso;

