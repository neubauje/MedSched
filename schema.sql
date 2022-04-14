-- *************************************************************************************************
-- This script creates all of the database objects (tables, sequences, etc) for the database
-- *************************************************************************************************

BEGIN;

-- CREATE statements go here
DROP TABLE IF EXISTS app_user cascade;

CREATE TABLE if not exists app_user
(
    id        SERIAL PRIMARY KEY UNIQUE,
    user_name varchar(32)  NOT NULL UNIQUE,
    password  varchar(32)  NOT NULL,
    role      varchar(32)  NOT NULL,
    salt      varchar(255) NOT NULL
);

DROP TABLE IF EXISTS review cascade;
create table if not exists review
(
    review_id      serial not null
        constraint review_pk
            primary key,
    message        text,
    rating         int,
    date_submitted timestamp not null,
    user_id  int,
    office_id  int,
    doctor_id int,
    doctor_response text
);

DROP TABLE IF EXISTS office cascade;
create table if not exists office
(
    office_id     serial  not null
        constraint office_pk
            primary key,
    office_name   text,
    address       text,
    days_open     text,
    opening_hours time,
    closing_hours time,
    phone_number  text,
    cost_per_hour numeric not null
);

create unique index if not exists office_office_id_uindex
    on office (office_id);

DROP TABLE IF EXISTS doctor cascade;
create table if not exists doctor
(
    "doctor_id" serial  not null,
    "user_id"   int     not null,
    "office_id" int,
--     "office_select" boolean,
    "firstname" varchar not null,
    "lastname"  varchar not null,
    "specialty" varchar
);

create unique index doctor_doctorid_uindex
    on doctor ("doctor_id");

alter table doctor
    add constraint doctor_pk
        primary key ("doctor_id");

drop table if exists patient cascade;
create table if not exists patient
(
    "patient_id"     serial  not null,
    "user_id"        int     not null,
    "firstname"      varchar not null,
    "lastname"       varchar not null,
    "dob"            date    not null,
    "phone"          numeric,
    "email"          varchar,
    "emergencyphone" numeric,
    "emergencyname"  varchar,
    "insurance"      varchar
);

create unique index patient_patientid_uindex
    on patient ("patient_id");

alter table patient
    add constraint patient_pk
        primary key ("patient_id");

DROP TABLE IF EXISTS "patient_doctor" cascade;
create table if not exists patient_doctor
(
    patient_id serial not null
        constraint patient_doctor_patient_patientid_fk
            references patient,
    doctor_id serial not null
        constraint patient_doctor_doctor_doctorid_fk
            references doctor,
    constraint patient_doctor_pk
        primary key (doctor_id, patient_id)
);

alter table doctor
    add constraint doctor_office_office_id_fk
        foreign key ("office_id") references office;

DROP TABLE IF EXISTS "time_slot" cascade;
create table if not exists "time_slot"
(
    timeslot_id  serial                not null
        constraint time_slot_pk
            primary key,
    doctor_id    integer               not null,
    date         date                  not null,
    start_time   time                  not null,
    end_time     time                  not null,
    assigned boolean default false not null,
    patient_id   integer,
    description  varchar(255)
);

alter table doctor
    add constraint doctor_app_user_id_fk
        foreign key (user_id) references app_user (id);

alter table patient
    add constraint patient_app_user_id_fk
        foreign key (user_id) references app_user (id);
alter table time_slot
    add constraint doctor_id_fk foreign key (doctor_id)
        references doctor (doctor_id);
alter table time_slot
    add constraint patient_id_fk foreign key (patient_id)
        references patient (patient_id);

alter table review
    add constraint review_office_office_id_fk
        foreign key (office_id) references office (office_id);

alter table review
    add constraint review_patient_patient_id_fk
        foreign key (user_id) references app_user (id);

alter table review
    add constraint review_doctor_doctor_id_fk
        foreign key (doctor_id) references doctor (doctor_id);

DROP TABLE IF EXISTS "notification" cascade;
create table if not exists "notification"
(
    notif_id serial not null,
    doctor_id int
        constraint notification_doctor_doctor_id_fk
            references doctor,
    patient_id int
        constraint notification_patient_patient_id_fk
            references patient,
    contents varchar(255),
    date_time timestamp not null,
    acknowledged boolean not null,
    intended_for_doctor boolean not null
);

create unique index notification_notif_id_uindex
    on notification (notif_id);

alter table notification
    add constraint notification_pk
        primary key (notif_id);

COMMIT;