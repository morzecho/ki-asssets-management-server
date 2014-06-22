# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table ASSET (
  id                        bigint not null,
  NAME                      varchar(255) not null,
  LOCATION_ID               bigint,
  CATEGORY_ID               bigint,
  constraint pk_ASSET primary key (id))
;

create table BREAK_DOWN (
  id                        bigint not null,
  CATEGORY_ID               bigint not null,
  name                      varchar(255),
  constraint pk_BREAK_DOWN primary key (id))
;

create table CATEGORY (
  id                        bigint not null,
  NAME                      varchar(255) not null,
  constraint pk_CATEGORY primary key (id))
;

create table LOCATION (
  id                        bigint not null,
  LOCATION_NAME             varchar(255) not null,
  constraint uq_LOCATION_LOCATION_NAME unique (LOCATION_NAME),
  constraint pk_LOCATION primary key (id))
;

create table MANAGEMENT_USER (
  id                        bigint not null,
  EMAIL                     varchar(255) not null,
  TOKEN                     varchar(255) not null,
  constraint uq_MANAGEMENT_USER_EMAIL unique (EMAIL),
  constraint uq_MANAGEMENT_USER_TOKEN unique (TOKEN),
  constraint pk_MANAGEMENT_USER primary key (id))
;

create sequence ASSET_seq;

create sequence BREAK_DOWN_seq;

create sequence CATEGORY_seq;

create sequence LOCATION_seq;

create sequence MANAGEMENT_USER_seq;

alter table ASSET add constraint fk_ASSET_location_1 foreign key (LOCATION_ID) references LOCATION (id);
create index ix_ASSET_location_1 on ASSET (LOCATION_ID);
alter table ASSET add constraint fk_ASSET_category_2 foreign key (CATEGORY_ID) references CATEGORY (id);
create index ix_ASSET_category_2 on ASSET (CATEGORY_ID);
alter table BREAK_DOWN add constraint fk_BREAK_DOWN_CATEGORY_3 foreign key (CATEGORY_ID) references CATEGORY (id);
create index ix_BREAK_DOWN_CATEGORY_3 on BREAK_DOWN (CATEGORY_ID);



# --- !Downs

drop table if exists ASSET cascade;

drop table if exists BREAK_DOWN cascade;

drop table if exists CATEGORY cascade;

drop table if exists LOCATION cascade;

drop table if exists MANAGEMENT_USER cascade;

drop sequence if exists ASSET_seq;

drop sequence if exists BREAK_DOWN_seq;

drop sequence if exists CATEGORY_seq;

drop sequence if exists LOCATION_seq;

drop sequence if exists MANAGEMENT_USER_seq;

