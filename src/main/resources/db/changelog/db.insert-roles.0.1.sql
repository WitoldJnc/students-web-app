--liquibase formatted sql

--changeset Anton:roles
insert into roles (id, roles) VALUE (1, 'ADMIN');
insert into roles (id, roles) VALUE (2, 'USER');
insert into roles (id, roles) VALUE (3, 'MODER');