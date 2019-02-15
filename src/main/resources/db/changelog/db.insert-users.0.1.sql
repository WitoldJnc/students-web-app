--liquibase formatted sql

--changeset Anton:1549984554202-22

--log 1, pas 1 - admin
insert into users (id, password, username, roles) VALUE (1, '$2a$08$pjNTuvRzzRTpf71K4TdTAej7bcxQZE7vFvPYoxVQvmNmfTZHlQzuu', '1', '1');

--log 2, pas 2 - user
insert into users (id, password, username, roles) VALUE (2, '$2a$08$rGhxYyjSt0l6gpeITabaSOnDgeFTnJfilGHlprSbf34lyFLc/3vXq', '2', '2');

--log 3, pas 3 - admin
insert into users (id, password, username, roles) VALUE (3, '$2a$08$BB4eazQeVn46KfVPbsPn6OUxQze9/onHfzxGMnCU2CQIeKRIYn/.6', '3', '3');