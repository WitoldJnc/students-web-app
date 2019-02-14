--liquibase formatted sql

--changeset Anton:1549984554202-22
insert into users (password, username) VALUE ( '1', '1');
insert into users (password, username) VALUE ( 'user', 'user');
