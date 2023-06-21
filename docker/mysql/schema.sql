/*
drop table field_of_activities;
drop table workers;
drop table companies;
*/

create table field_of_activities(id int4 not null primary key, name varchar(20) unique not null);

create table companies(id bigint not null auto_increment primary key, name varchar(100), number_of_clients int4, enterprise_fund double(17,2), field_of_activity_id int4);

create table workers(id bigint not null auto_increment primary key, first_name varchar(20), last_name varchar(20), age int4, salary double(8, 2), profession varchar(100), company_id bigint not null, CONSTRAINT fk_companies_workers FOREIGN KEY(company_id) REFERENCES companies(id) );
