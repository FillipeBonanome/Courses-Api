alter table users add active tinyint;
update users set active = 1;
alter table users modify active tinyint not null;