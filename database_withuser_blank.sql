use master
go
-- create specific user for db
if exists (SELECT name FROM sys.database_principals where name='dbuser_kma_qldiem') drop login dbuser_kma_qldiem
go
create login dbuser_kma_qldiem with password = 'jhdsf7.843fb%jaecbbU8DSTsRsd)zfdree'
go
alter login dbuser_kma_qldiem enable
go
-- create db
if exists (select name from sysdatabases where name='KMA_QLDiem') drop database KMA_QLDiem 
go
create database KMA_QLDiem
go
use KMA_QLDiem
go

-- grant permission
create user dbuser_kma_qldiem for login dbuser_kma_qldiem
go
grant select, insert, update, delete to dbuser_kma_qldiem
go

-- MAIN TABLES
go
create table t_student (
	id int primary key identity,
	name nvarchar(128) not null,
	classId int not null,
	info ntext not null default N''
)

create table t_teacher (
	id int primary key identity,
	username varchar(32),
	pass ntext,
	name nvarchar(128) not null,
	info ntext not null default N''
)

create table t_subject (
	id int primary key identity,
	name nvarchar(128) not null,
	info ntext not null default N''
)

create table t_bulk (
	id int primary key identity,
	name nvarchar(32) not null,
	info ntext not null default N''
)

create table t_class (
	id int primary key identity,
	name nvarchar(16) not null,
	teacherId int not null,
	bulkId int not null,
	info ntext not null default N''
)

create table t_score (
	id bigint primary key identity,
	subjectId int not null,
	studentId int not null,
	teacherId int not null,
	score float not null default 0,
	coefficient smallint not null default 1
)

create table t_scorelog (
	id bigint primary key identity,
	studentId int not null,
	scores ntext not null default N'',
	remarks ntext not null default N'',
	school_year nvarchar(64) not null default N''
)

create table t_bulk_subject (
	bulkId int not null,
	subjectId int not null,
	primary key (bulkId, subjectId)
)

create table t_teacher_subject (
	teacherId int not null,
	subjectId int not null,
	primary key (teacherId, subjectId)
)
go
-- MAIN TABLES end

-- TABLE CONSTRAINTS
alter table t_student
	add constraint FK_student_class foreign key (classId) references t_class(id)
go

alter table t_scorelog
	add constraint FK_log_student foreign key (studentId) references t_student(id)
go

alter table t_score
	add constraint FK_score_subject foreign key (subjectId) references t_subject(id),
		constraint FK_score_student foreign key (studentId) references t_student(id),
		constraint FK_score_teacher	foreign key (teacherId) references t_teacher(id)
go

alter table t_class
	add constraint FK_class_teacher foreign key (teacherId) references t_teacher(id),
		constraint FK_class_bulk    foreign key (bulkId)    references t_bulk(id)
go

alter table t_bulk_subject
	add constraint FK_bulksubject_bulk foreign key (bulkId) references t_bulk(id),
		constraint FK_bulksubject_subj foreign key (subjectId) references t_subject(id)
go

alter table t_teacher_subject
	add constraint FK_teachsubj_teacher foreign key (teacherId) references t_teacher(id),
		constraint FK_teachsubj_subject foreign key (subjectId) references t_subject(id)
go
-- TABLE CONSTRAINTS end

-- ORPHAN TABLES
go
create table t_properties (
	_key nvarchar(256) primary key,
	_value ntext not null default N''
)

create table t_admin (
	id nvarchar(64) primary key,
	pass ntext not null,
	prohibited text not null default '',
	last_login bigint not null default 0,
	last_change bigint not null default 0
)
go
-- ORPHAN TABLES end