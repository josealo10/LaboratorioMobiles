/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  jaalf
 * Created: 04/03/2020
 */
 create table if not exists carrera(
	codigo int primary key not null auto_increment,
    nombre varchar(30)
 );
 
 
 create table if not exists alumno(
	cedula int primary key not null auto_increment,
    nombre varchar(30),
    telefono int,
    email varchar(30),
    carrera int foreign key carrera(codigo)    
 );

