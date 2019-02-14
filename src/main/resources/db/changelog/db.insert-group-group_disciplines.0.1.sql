--liquibase formatted sql

--changeset Anton:1549984554202-17
insert into school_groups (group_number) values (312);
insert into group_discipline (group_id, discipline_id) values (1, 2);
insert into group_discipline (group_id, discipline_id) values (1, 1);

insert into school_groups (group_number) values (547);
insert into group_discipline (group_id, discipline_id) values (2, 3);
insert into group_discipline (group_id, discipline_id) values (2, 4);

insert into school_groups (group_number) values (176);
insert into group_discipline (group_id, discipline_id) values (3, 1);
insert into group_discipline (group_id, discipline_id) values (3, 4);