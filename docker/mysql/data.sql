/*
truncate table workers;
truncate table companies;
truncate table field_of_activities;
*/

insert into field_of_activities (id, name)
values (0, "OTHER"),
		(1, "SERVICE"),
		(2, "SALES"),
		(3, "EDUCATION"),
		(4, "TRADING"),
		(5, "LOGISTICS"),
		(6, "TRANSPORT"),
		(7, "FINANCIAL"),
		(8, "RAW_MATERIALS"),
		(9, "CHEMICAL");

insert into companies (name, number_of_clients, enterprise_fund, field_of_activity_id)
values ("Service company", 245034, 123276.55, 1),
		("AVIASALES", 15000000, 43000000, 2),
		("Market", 25000, 140000, 4),
		("Bank", 25000000, 67000000, 7);
        
insert into workers (first_name, last_name, age, salary, profession, company_id)
values ("Alex", "Romanov", 28, 1230, "electrician", 1),
		("Ivan", "Smirnov", 33, 1920, "accountant", 4),
		("Ira", "Zayceva", 24, 1090, "trader", 3),
		("Maksim", "Morozov", 27, 4400, "pilot", 2);
		