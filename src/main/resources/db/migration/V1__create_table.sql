drop table if exists card;
create table card (number varchar(16) not null, cvv2 integer, end_date date, issue_date date, owner_name varchar(255), owner_patronymic varchar(255), owner_surname varchar(255), password varchar(255), primary key (number)) engine=InnoDB;
