--liquibase formatted sql

--changeset Anton:1549984554202-20
insert  into marks (discipline_id, mark, student_id) values (3, 7, 4);
insert  into marks (discipline_id, mark, student_id) values (4, 8, 4);

insert  into marks (discipline_id, mark, student_id) values (3, 5, 5);
insert  into marks (discipline_id, mark, student_id) values (4, 1, 5);

insert  into marks (discipline_id, mark, student_id) values (3, 8, 6);
insert  into marks (discipline_id, mark, student_id) values (4, 5, 6);