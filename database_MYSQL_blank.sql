
-- MAIN TABLES
create table t_student (
	id int primary key AUTO_INCREMENT,
	name varchar(128) not null,
	classId int not null,
	info text not null default ''
);

create table t_teacher (
	id int primary key AUTO_INCREMENT,
	username varchar(32),
	pass text,
	name varchar(128) not null,
	info text not null default ''
);

create table t_subject (
	id int primary key AUTO_INCREMENT,
	name varchar(128) not null,
	info text not null default ''
);

create table t_bulk (
	id int primary key AUTO_INCREMENT,
	name varchar(32) not null,
	info text not null default ''
);

create table t_class (
	id int primary key AUTO_INCREMENT,
	name varchar(16) not null,
	teacherId int not null,
	bulkId int not null,
	info text not null default ''
);

create table t_score (
	id bigint primary key AUTO_INCREMENT,
	subjectId int not null,
	studentId int not null,
	teacherId int not null,
	score float not null,
	coefficient smallint not null
);

create table t_scorelog (
	id int primary key AUTO_INCREMENT,
	studentId int not null,
	scores text not null default '',
	remarks text not null default '',
	school_year varchar(64) not null default ''
);

create table t_bulk_subject (
	bulkId int not null,
	subjectId int not null,
	primary key (bulkId, subjectId)
);

create table t_teacher_subject (
	teacherId int not null,
	subjectId int not null,
	primary key (teacherId, subjectId)
);
-- MAIN TABLES end

-- TABLE CONSTRAINTS
alter table t_student
	add constraint FK_student_class foreign key (classId) references t_class(id)
;

alter table t_scorelog
	add constraint FK_log_student foreign key (studentId) references t_student(id)
;

alter table t_score
	add constraint FK_score_subject foreign key (subjectId) references t_subject(id),
		constraint FK_score_student foreign key (studentId) references t_student(id),
		constraint FK_score_teacher	foreign key (teacherId) references t_teacher(id)
;

alter table t_class
	add constraint FK_class_teacher foreign key (teacherId) references t_teacher(id),
		constraint FK_class_bulk    foreign key (bulkId)    references t_bulk(id)
;

alter table t_bulk_subject
	add constraint FK_bulksubject_bulk foreign key (bulkId) references t_bulk(id),
		constraint FK_bulksubject_subj foreign key (subjectId) references t_subject(id)
;

alter table t_teacher_subject
	add constraint FK_teachsubj_teacher foreign key (teacherId) references t_teacher(id),
		constraint FK_teachsubj_subject foreign key (subjectId) references t_subject(id)
;
-- TABLE CONSTRAINTS end

-- ORPHAN TABLES
create table t_properties (
	_key varchar(256) primary key,
	_value text not null default ''
);

create table t_admin (
	id varchar(64) primary key,
	pass text not null,
	prohibited text not null default '',
	last_login bigint not null,
	last_change bigint not null
);
-- ORPHAN TABLES end
