/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/12/16 15:25:10                          */
/*==============================================================*/


drop table if exists DATA_FIELD;

drop table if exists DATA_FOUNDATION;

drop table if exists DATA_RELATION;

drop table if exists DATA_TABLE;

drop table if exists DB_CONNECTION;

drop table if exists FILTER;

drop table if exists FOLDER;

drop table if exists OBJECT;

drop index Relationship_6_FK on QUERY_STATEMENT;

drop table if exists QUERY_STATEMENT;

drop table if exists UNIVERSE;

/*==============================================================*/
/* Table: DATA_FIELD                                            */
/*==============================================================*/
create table DATA_FIELD
(
   field_id             int not null auto_increment,
   table_id             int,
   field_name           varchar(100),
   field_type           varchar(100),
   field_length         int,
   is_pk                bool,
   is_fk                bool,
   primary key (field_id)
);

/*==============================================================*/
/* Table: DATA_FOUNDATION                                       */
/*==============================================================*/
create table DATA_FOUNDATION
(
   df_id                int not null auto_increment,
   df_name              varchar(100),
   df_creator           varchar(100),
   df_create_time       timestamp,
   df_description       varchar(200),
   primary key (df_id)
);

/*==============================================================*/
/* Table: DATA_RELATION                                         */
/*==============================================================*/
create table DATA_RELATION
(
   data_relation_id     int not null auto_increment,
   table1_id            int,
   table2_id            int,
   primary key (data_relation_id)
);

/*==============================================================*/
/* Table: DATA_TABLE                                            */
/*==============================================================*/
create table DATA_TABLE
(
   table_id             int not null auto_increment,
   df_id                int,
   table_name           varchar(100),
   table_description    varchar(200),
   primary key (table_id)
);

/*==============================================================*/
/* Table: DB_CONNECTION                                         */
/*==============================================================*/
create table DB_CONNECTION
(
   dbc_id               int not null auto_increment,
   db_name              varchar(100),
   db_location          varchar(50),
   db_port              varchar(10),
   db_account           varchar(50),
   db_pwd               varchar(50),
   primary key (dbc_id)
);

/*==============================================================*/
/* Table: FILTER                                                */
/*==============================================================*/
create table FILTER
(
   filter_id            int not null auto_increment,
   folder_id            int,
   object_id            int,
   filter_name          varchar(100),
   operator             int,
   operand_type         int,
   operands             varchar(500),
   primary key (filter_id)
);

/*==============================================================*/
/* Table: FOLDER                                                */
/*==============================================================*/
create table FOLDER
(
   folder_id            int not null auto_increment,
   u_id                 int,
   folder_name          varchar(100),
   folder_description   varchar(200),
   primary key (folder_id)
);

/*==============================================================*/
/* Table: OBJECT                                                */
/*==============================================================*/
create table OBJECT
(
   object_id            int not null auto_increment,
   folder_id            int,
   object_name          varchar(100),
   object_description   varchar(200),
   object_type          int,
   related_field        varchar(200),
   primary key (object_id)
);

/*==============================================================*/
/* Table: QUERY_STATEMENT                                       */
/*==============================================================*/
create table QUERY_STATEMENT
(
   QS_id                int not null auto_increment,
   u_id                 int,
   QS                   varchar(100),
   QS_type              int,
   QS_description       varchar(200),
   primary key (QS_id)
);

/*==============================================================*/
/* Index: Relationship_6_FK                                     */
/*==============================================================*/
create index Relationship_6_FK on QUERY_STATEMENT
(
   u_id
);

/*==============================================================*/
/* Table: UNIVERSE                                              */
/*==============================================================*/
create table UNIVERSE
(
   u_id                 int not null auto_increment,
   df_id                int,
   dbc_id               int,
   u_creator            varchar(100),
   u_create_time        timestamp,
   u_description        varchar(200),
   primary key (u_id)
);

alter table DATA_FIELD add constraint FK_Relationship_12 foreign key (table_id)
      references DATA_TABLE (table_id) on delete restrict on update restrict;

alter table DATA_RELATION add constraint FK_Relationship_15 foreign key (table1_id)
      references DATA_TABLE (table_id) on delete restrict on update restrict;

alter table DATA_RELATION add constraint FK_Relationship_16 foreign key (table2_id)
      references DATA_TABLE (table_id) on delete restrict on update restrict;

alter table DATA_TABLE add constraint FK_Reference_10 foreign key (df_id)
      references DATA_FOUNDATION (df_id) on delete restrict on update restrict;

alter table FILTER add constraint FK_Relationship_17 foreign key (object_id)
      references OBJECT (object_id) on delete restrict on update restrict;

alter table FILTER add constraint FK_Relationship_8 foreign key (folder_id)
      references FOLDER (folder_id) on delete restrict on update restrict;

alter table FOLDER add constraint FK_Relationship_6 foreign key (u_id)
      references UNIVERSE (u_id) on delete restrict on update restrict;

alter table OBJECT add constraint FK_Relationship_7 foreign key (folder_id)
      references FOLDER (folder_id) on delete restrict on update restrict;

alter table QUERY_STATEMENT add constraint FK_Reference_11 foreign key (u_id)
      references UNIVERSE (u_id) on delete restrict on update restrict;

alter table UNIVERSE add constraint FK_Relationship_4 foreign key (df_id)
      references DATA_FOUNDATION (df_id) on delete restrict on update restrict;

alter table UNIVERSE add constraint FK_Relationship_5 foreign key (dbc_id)
      references DB_CONNECTION (dbc_id) on delete restrict on update restrict;

