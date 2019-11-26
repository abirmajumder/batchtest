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

create table cnf_data_source (
   id serial not null primary key,
   source_name varchar(100),
   source_type varchar(50),
   source_params varchar(500),
   job_id int references cnf_job(id),
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
   sequence int,
   job_sequence_id int references cnf_job_sequence(id),
   active varchar(1)
);

create table cnf_record_sequence (
   id serial not null primary key,
   step_name varchar(100),
   step_params varchar(500),
   sequence int,
   step_sequence_id int references cnf_step_sequence(id),
   active varchar(1)
);