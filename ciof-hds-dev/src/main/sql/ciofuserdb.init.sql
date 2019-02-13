/*
 * ciofuserdb.init.sql
 * (C) 2019. Industrial Value Chain Initiative
 */
/* roles */
drop table if exists roles cascade;
CREATE TABLE roles
(
    role_id                 serial NOT NULL,
    role_name               varchar(256) NOT NULL,
    role_description        varchar(256) NOT NULL,

    constraint roles_pk primary key (role_id),
    constraint roles_key1 unique (role_name)
);

/* users */
drop table if exists users cascade;
CREATE TABLE users
(
    user_id                 serial NOT NULL,
    username                varchar(256) NOT NULL,
    password                varchar(256) NOT NULL,

    constraint users_pk primary key (user_id),
    constraint users_key1 unique (username)
);

/* user_roles */
drop table if exists user_roles cascade;
CREATE TABLE user_roles
(
    user_id                 integer NOT NULL,
    role_id                 integer NOT NULL,

    constraint user_roles_pk primary key (user_id, role_id),
    constraint user_roles_fk1 foreign key (user_id) references users(user_id) on delete cascade on update cascade,
    constraint user_roles_fk2 foreign key (role_id) references roles(role_id) on delete cascade on update cascade
);

/* add roles */
insert into roles(role_name, role_description) values('ADMINISTRATOR', 'The administrator for system');
insert into roles(role_name, role_description) values('USER', 'The general user');
