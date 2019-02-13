--liquibase formatted sql

--changeset Anton:1549984554202-21
insert  into marks (discipline_id, mark, student_id) values (1, 5, 7);
insert  into marks (discipline_id, mark, student_id) values (4, 6, 7);

insert  into marks (discipline_id, mark, student_id) values (1, 4, 8);
insert  into marks (discipline_id, mark, student_id) values (4, 3, 8);