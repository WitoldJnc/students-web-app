--liquibase formatted sql

--changeset Anton:1549984554202-18
insert into students (first_name, group_id, last_name) values ('John', 1 , 'Galt');
insert into students (first_name, group_id, last_name) values ('Dagny', 1 , 'Taggart');
insert into students (first_name, group_id, last_name) values ('Francisco', 1 , 'Anconia');
insert into students (first_name, group_id, last_name) values ('Hank', 2 , 'Rearden');
insert into students (first_name, group_id, last_name) values ('Eddie', 2 , 'Willers');
insert into students (first_name, group_id, last_name) values ('James', 2 , 'Taggart');
insert into students (first_name, group_id, last_name) values ('Lillian', 3 , 'Rearden');
insert into students (first_name, group_id, last_name) values ('Wesley', 3 , 'Mouch');