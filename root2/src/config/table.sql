drop database if mvc exists;
create database mvc;
use mvc;
create table student(
id int not null auto_increment,
name varchar(20),
sex varchar(4),
age int,
primary key (id)
);
