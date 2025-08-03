alter table comments add deleted tinyint;
update comments set deleted = 0;
alter table comments modify deleted tinyint not null;