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
    primary key(cedula),
    foreign key (carrera) references carrera(codigo)    
 );

