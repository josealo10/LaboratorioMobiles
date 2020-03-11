
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
	carrera int,
    usuario varchar(30),
    primary key(cedula),
    foreign key (carrera) references carrera(codigo),
    foreign key (usuario) references usuario(username)
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
 create procedure mobiles.insertAlumno(cedula int, nombre varchar(30 ), telefono int, email varchar(30) , carrera int)
 begin
	insert into mobiles.alumno(cedula, nombre, telefono , email, carrera) 
    values(cedula, nombre, telefono , email, carrera);
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
	delete from mobiles.alumno where alumno.cedula = cedula;
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
 insert into mobiles.usuario(username,clave,permiso) values('222','222','Administrador');
 
 insert into mobiles.carrera(nombre) values('Informatica');
 insert into mobiles.carrera(nombre) values('Administracion');
 
 insert into mobiles.curso(nombre, creditos , horas_semanales, carrera) values('Plataformas mobiles',4,4,1);
 insert into mobiles.curso(nombre, creditos , horas_semanales, carrera) values('Inge 3',4,4,1);
 insert into mobiles.curso(nombre, creditos , horas_semanales, carrera) values('Finanzas',4,4,2);
 insert into mobiles.curso(nombre, creditos , horas_semanales, carrera) values('Contabilidad',4,4,2);
 
 insert into mobiles.alumno(cedula,nombre,telefono,email,carrera) values(402420953,'Alonso',89620145,'alonso@gmail.com',1);

 call insertCurso('Soporte', '4', '4','1')
 call mobiles.getUsuario('111','111');
 call mobiles.getCursos();
 call mobiles.getCarreras();
 call mobiles.getCarrera(1);
 call mobiles.getAlumnos();
 call mobiles.deleteCurso(11);
 call mobiles.updateCurso(4, 'Funda', 4, 5, 1);
 call mobiles.insertAlumno(402420, 'Alonso', '89620145', 'josealonso@gmail.com','1');
 call mobiles.updateAlumno(40256420, 'pedro', 89620145, 'josealonso@gmail.com',1);
 call mobiles.deleteAlumno(402420);
 
 drop table mobiles.alumno;
 drop table mobiles.curso;
 drop table mobiles.carrera;
 drop table mobiles.usuario;
 
 drop procedure mobiles.getUsuario;
 
 drop procedure mobiles.getCarrera;
 drop procedure mobiles.getCarreras;
 
 drop procedure mobiles.getAlumnos;
 drop procedure mobiles.insertAlumno;
 drop procedure mobiles.deleteAlumno;
 drop procedure mobiles.updateAlumno;
 
 drop procedure mobiles.getCursos;
 drop procedure mobiles.insertCurso;
 drop procedure mobiles.deleteCurso;
 drop procedure mobiles.updateCurso;

