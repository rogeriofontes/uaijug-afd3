create table `user` (
	`id` bigint NOT NULL AUTO_INCREMENT,
	`email` varchar(255) NOT NULL, 
	`password` varchar(255) NOT NULL, 
	`create_by` varchar(255) NOT NULL DEFAULT 'system_user',
    `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_modified_by` varchar(255),
    `last_modified_date` datetime,
	primary key (id),
	constraint username_unique unique (email) 
);

insert into user (email, password) values ('fontestz@gmail.com', '$2a$10$4q2I1/BSLFOx64ji5oDz2uH.ZLOtQFi9N821ILDmjxO7wgt/gagnS');

create table `user_role` (
	`id` bigint NOT NULL AUTO_INCREMENT,
	`role_name` varchar(255), 
	`user_id` bigint,
	`create_by` varchar(255) NOT NULL DEFAULT 'system_user',
    `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_modified_by` varchar(255),
    `last_modified_date` datetime,
	primary key (id)
);

insert into user_role (role_name, user_id) values ('USER', 1);
insert into user_role (role_name, user_id) values ('ADMIN', 1);
