create table cnf_application (
   id serial not null primary key,
   application_name varchar(100),
   product_name varchar(100),  
   active varchar(1)
);

create table cnf_job (
   id serial not null primary key,
   job_name varchar(100),
   application_id int references cnf_application(id),
   active varchar(1)
);

create table cnf_job_sequence (
   id serial not null primary key,
   step_name varchar(100),
   step_params varchar(500),
   sequence int,
   job_id int references cnf_job(id),
   active varchar(1)
);

create table cnf_step_sequence (
   id serial not null primary key,
   step_name varchar(100),
   step_params varchar(500), 
   sequence float,
   job_sequence_id int references cnf_job_sequence(id), 
   active varchar(1)
);

create table cnf_field (
   id serial not null primary key,	
   field_name varchar(100),
   field_type varchar(500),
   field_pattern varchar(100),
   is_required varchar(1),   
   job_sequence_id int references cnf_job_sequence(id),
   active varchar(1)
);

create table cnf_transformation (
   id serial not null primary key,
   target_field varchar(100),
   target_type varchar(500),
   target_pattern varchar(100),
   transformation_type varchar(50),
   transformation_params varchar(500),	
   step_sequence_id int references cnf_step_sequence(id),
   field_id int references cnf_field(id),
   active varchar(1)
);

select * from cnf_application
select * from cnf_job

insert into cnf_application ( application_name, product_name, active ) values ('Claims Processor','CDP','Y');
insert into cnf_job ( job_name, application_id, active  ) values ( 'FINCODE',1,'Y' )
insert into cnf_job_sequence ( step_name, step_params, sequence, job_id, active) values ( 'QUERY','query=select * from patient, da=gcif', 1, 1, 'Y' )
insert into cnf_job_sequence ( step_name, step_params, sequence, job_id, active) values ( 'SP','name=truncate_patient, da=gcif', 2, 1, 'Y' )

select * from cnf_job_sequence

--select * from cnf_application

/*create table cnf_record_sequence (
   id serial not null primary key,
   step_name varchar(100),
   step_params varchar(500),
   sequence int,
   step_sequence_id int references cnf_step_sequence(id),
   active varchar(1)
);

create table cnf_data_source (
   id serial not null primary key,
   source_name varchar(100),
   source_type varchar(50),
   source_params varchar(500),
   job_id int references cnf_job(id),
   active varchar(1)
);
*/
