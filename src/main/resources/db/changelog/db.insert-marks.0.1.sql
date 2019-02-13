--liquibase formatted sql

--changeset Anton:1549984554202-19
insert  into marks (discipline_id, mark, student_id) values (1, 10, 1);
insert  into marks (discipline_id, mark, student_id) values (2, 7, 1);

insert  into marks (discipline_id, mark, student_id) values (1, 8, 2);
insert  into marks (discipline_id, mark, student_id) values (2, 2, 2);

insert  into marks (discipline_id, mark, student_id) values (1, 5, 3);
insert  into marks (discipline_id, mark, student_id) values (2, 8, 3);