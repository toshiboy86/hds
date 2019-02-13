/*
 * ciofhdsdb.init.sql
 * (C) 2019. Industrial Value Chain Initiative
 */
drop table if exists specific_category_dictionary cascade;
create table specific_category_dictionary
(
    insert_date_time    timestamp NOT NULL default current_timestamp,

    category_dictionary_id           varchar(256) NOT NULL,
    category_dictionary_name         varchar(256) NOT NULL,
    category_dictionary_description  varchar(4096) NOT NULL,
    
    constraint specific_category_dictionary_pk primary key (category_dictionary_id)
);

drop table if exists component_category cascade;
create table component_category
(
    insert_date_time    timestamp NOT NULL default current_timestamp,

    component_category_id            varchar(256) NOT NULL,
    component_category_type          varchar(256) NOT NULL,
    component_category_name          varchar(256) NOT NULL,
    component_category_description   varchar(4096) NOT NULL,
    category_dictionary_id           varchar(256) NOT NULL,
    
    constraint component_category_pk primary key (component_category_id),
    constraint component_category_fk1 foreign key (category_dictionary_id) references specific_category_dictionary(category_dictionary_id) on delete cascade on update cascade
);

drop table if exists data_dictionary cascade;
create table data_dictionary
(
    insert_date_time             timestamp NOT NULL default current_timestamp,

    data_dictionary_id           varchar(256) NOT NULL,
    data_dictionary_name         varchar(256) NOT NULL,
    data_dictionary_description  varchar(4096) NOT NULL,
    data_dictionary_type         varchar(256) NOT NULL,
    version                      integer NOT NULL,
    previous_data_dictionary_id  varchar(256),
    owner_id                     varchar(256) NOT NULL,
    
    constraint data_dictionary_pk primary key (data_dictionary_id),
    constraint data_dictionary_fk1 foreign key (previous_data_dictionary_id) references data_dictionary(data_dictionary_id) on delete cascade on update cascade
);

drop table if exists data_component_model cascade;
create table data_component_model
(
    insert_date_time        timestamp NOT NULL default current_timestamp,

    dcm_id                  varchar(256) NOT NULL,
    dcm_name                varchar(256) NOT NULL,
    dcm_description         varchar(4096) NOT NULL,
    author                  varchar(256) NOT NULL,
    creation_date           varchar(256) NOT NULL,
    category_dictionary_id  varchar(256) NOT NULL,
    component_category_id   varchar(256) NOT NULL,
    data_dictionary_id      varchar(256) NOT NULL,
    
    constraint data_component_model_pk primary key (dcm_id),
    constraint data_component_model_fk1 foreign key (category_dictionary_id) references specific_category_dictionary(category_dictionary_id) on delete cascade on update cascade,
    constraint data_component_model_fk2 foreign key (component_category_id) references component_category(component_category_id) on delete cascade on update cascade,
    constraint data_component_model_fk3 foreign key (data_dictionary_id) references data_dictionary(data_dictionary_id) on delete cascade on update cascade
);

drop table if exists dcm_information_design cascade;
create table dcm_information_design
(
    insert_date_time           timestamp NOT NULL default current_timestamp,

    dcm_information_design_id  serial NOT NULL,
    information_design_id      varchar(256) NOT NULL,
    information_design_type    varchar(256) NOT NULL,
    information_design_name    varchar(256) NOT NULL,
    remarks                    varchar(4096),
    dcm_id                     varchar(256) NOT NULL,

    constraint dcm_information_design_pk primary key (dcm_information_design_id),
    constraint dcm_information_design_fk1 foreign key (dcm_id) references data_component_model(dcm_id) on delete cascade on update cascade
);

drop table if exists service_dictionary cascade;
create table service_dictionary
(
    insert_date_time                  timestamp NOT NULL default current_timestamp,

    service_dictionary_id             varchar(256) NOT NULL,
    service_dictionary_name           varchar(256) NOT NULL,
    service_dictionary_description    varchar(4096) NOT NULL,
    service_dictionary_type           varchar(256) NOT NULL,
    version                           integer NOT NULL,
    previous_service_dictionary_id    varchar(256),
    owner_id                          varchar(256) NOT NULL,

    constraint service_dictionary_pk primary key (service_dictionary_id),
    constraint service_dictionary_fk1 foreign key (previous_service_dictionary_id) references service_dictionary(service_dictionary_id) on delete cascade on update cascade
);

drop table if exists process_component_model cascade;
create table process_component_model
(
    insert_date_time          timestamp NOT NULL default current_timestamp,

    pcm_id                    varchar(256) NOT NULL,
    service_dictionary_id     varchar(256) NOT NULL,
    pcm_name                  varchar(256) NOT NULL,
    pcm_description           varchar(4096) NOT NULL,
    author                    varchar(256) NOT NULL,
    creation_date             varchar(256) NOT NULL,
    category_dictionary_id    varchar(256) NOT NULL,
    component_category_id     varchar(256) NOT NULL,
    hardware_requirement      varchar(4096) NOT NULL,
    other_requirement         varchar(4096) NOT NULL,

    constraint process_component_model_pk primary key (pcm_id),
    constraint process_component_model_fk1 foreign key (service_dictionary_id) references service_dictionary(service_dictionary_id) on delete cascade on update cascade,
    constraint process_component_model_fk2 foreign key (category_dictionary_id) references specific_category_dictionary(category_dictionary_id) on delete cascade on update cascade,
    constraint process_component_model_fk3 foreign key (component_category_id) references component_category(component_category_id) on delete cascade on update cascade
);

drop table if exists process_component_model_operation cascade;
create table process_component_model_operation
(
    insert_date_time         timestamp NOT NULL default current_timestamp,

    pcm_operation_id         serial NOT NULL,
    pcm_operation_type       varchar(256) NOT NULL,
    pcm_id                   varchar(256) NOT NULL,
    service_dictionary_id    varchar(256) NOT NULL,
    remarks                  varchar(4096),
    dcm_id                   varchar(256) NOT NULL,

    constraint process_component_model_operation_pk primary key (pcm_operation_id),    
    constraint process_component_model_operation_fk1 foreign key (dcm_id) references data_component_model(dcm_id) on delete cascade on update cascade
);

drop table if exists data_relation_model cascade;
create table data_relation_model
(
    insert_date_time        timestamp NOT NULL default current_timestamp,

    drm_id                  varchar(256) NOT NULL,
    data_dictionary_id      varchar(256) NOT NULL,
    dcm_id                  varchar(256) NOT NULL,
    target_dcm_id           varchar(256) NOT NULL,
    
    constraint data_relation_model_pk primary key (drm_id),
    constraint data_relation_model_fk1 foreign key (data_dictionary_id) references data_dictionary(data_dictionary_id) on delete cascade on update cascade,
    constraint data_relation_model_fk2 foreign key (dcm_id) references data_component_model(dcm_id) on delete cascade on update cascade,
    constraint data_relation_model_fk3 foreign key (target_dcm_id) references data_component_model(dcm_id) on delete cascade on update cascade
);

drop table if exists data_property_definition cascade;
create table data_property_definition
(
    insert_date_time        timestamp NOT NULL default current_timestamp,

    dpd_id                  serial NOT NULL,
    dpd_name                varchar(256) NOT NULL,
    dpd_description         varchar(4096) NOT NULL,
    dpd_type                varchar(256) NOT NULL,
    is_list                 boolean NOT NULL,
    is_required             boolean NOT NULL,
    is_primary_key          boolean NOT NULL,
    default_value           varchar(4096) NOT NULL,
    sample_value            varchar(4096) NOT NULL,
    dcm_id                  varchar(256) NOT NULL,
    
    constraint data_property_definition_pk primary key (dpd_id),
    constraint data_property_definition_fk1 foreign key (dcm_id) references data_component_model(dcm_id) on delete cascade on update cascade,
    unique(dpd_name,dcm_id)
);

drop table if exists data_relation_model_addition cascade;
create table data_relation_model_addition
(
    insert_date_time                    timestamp NOT NULL default current_timestamp,

    data_relation_model_addition_id     serial NOT NULL,
    additional_dpd_id                   serial NOT NULL,
    drm_id                              varchar(256) NOT NULL,
    
    constraint data_relation_model_addition_pk primary key (data_relation_model_addition_id),
    constraint data_relation_model_addition_fk1 foreign key (additional_dpd_id) references data_property_definition(dpd_id) on delete cascade on update cascade,
    constraint data_relation_model_addition_fk2 foreign key (drm_id) references data_relation_model(drm_id) on delete cascade on update cascade
);

drop table if exists data_relation_model_item  cascade;
create table data_relation_model_item 
(
    insert_date_time               timestamp NOT NULL default current_timestamp,

    data_relation_model_item_id    serial NOT NULL,
    dpd_id                         serial NOT NULL,
    target_dpd_id                  serial NOT NULL,
    drm_id                         varchar(256) NOT NULL,
    
    constraint data_relation_model_item_pk primary key (data_relation_model_item_id),
    
    constraint data_relation_model_item_fk1 foreign key (dpd_id) references data_property_definition(dpd_id) on delete cascade on update cascade,
    constraint data_relation_model_item_fk2 foreign key (target_dpd_id) references data_property_definition(dpd_id) on delete cascade on update cascade,
    constraint data_relation_model_item_fk3 foreign key (drm_id) references data_relation_model(drm_id) on delete cascade on update cascade
);

drop table if exists data_component_model_operation cascade;
create table data_component_model_operation
(
    insert_date_time      timestamp NOT NULL default current_timestamp,

    dcm_operation_id      serial NOT NULL,
    dcm_id                varchar(256) NOT NULL,
    data_dictionary_id    varchar(256) NOT NULL,
    remarks               varchar(4096),
    pcm_id                varchar(256) NOT NULL,

    constraint data_component_model_operation_pk primary key (dcm_operation_id),
    constraint data_component_model_operation_fk1 foreign key (pcm_id) references process_component_model(pcm_id) on delete cascade on update cascade
);

drop table if exists operation_item cascade;
create table operation_item
(
    insert_date_time      timestamp NOT NULL default current_timestamp,

    operation_item_id     serial NOT NULL,
    operation             varchar(256) NOT NULL,
    dcm_operation_id      serial NOT NULL,

    constraint operation_item_pk primary key (operation_item_id),
    constraint operation_item_fk1 foreign key (dcm_operation_id) references data_component_model_operation(dcm_operation_id) on delete cascade on update cascade
);

drop table if exists process_flow_definition cascade;
create table process_flow_definition
(
    insert_date_time     timestamp NOT NULL default current_timestamp,

    pfd_id               serial NOT NULL,
    operation_content    varchar(4096) NOT NULL,
    target_data          varchar(256) NOT NULL,
    pcm_id               varchar(256) NOT NULL,
    
    constraint process_flow_definition_pk primary key (pfd_id),
    constraint process_flow_definition_fk1 foreign key (pcm_id) references process_component_model(pcm_id) on delete cascade on update cascade
);

drop table if exists process_condition cascade;
create table process_condition
(
    insert_date_time            timestamp NOT NULL default current_timestamp,
    
    process_condition_id        serial NOT NULL,
    condition_type              varchar(256) NOT NULL,
    condition                   varchar(256) NOT NULL,
    target_data                 varchar(256) NOT NULL,
    pcm_id                      varchar(256) NOT NULL,
    process_condition_data_type integer NOT NULL,

    constraint process_condition_pk primary key (process_condition_id),
    constraint process_condition_fk1 foreign key (pcm_id) references process_component_model(pcm_id) on delete cascade on update cascade
);

drop table if exists event_and_condition_model cascade;
create table event_and_condition_model
(
    insert_date_time          timestamp NOT NULL default current_timestamp,

    ecm_id                    varchar(256) NOT NULL,
    service_dictionary_id     varchar(256) NOT NULL,
    ecm_name                  varchar(256) NOT NULL,
    ecm_description           varchar(4096) NOT NULL,
    author                    varchar(256) NOT NULL,
    creation_date             varchar(256) NOT NULL,
    category_dictionary_id    varchar(256) NOT NULL,
    component_category_id     varchar(256) NOT NULL,
    other_requirement         varchar(4096) NOT NULL,

    constraint event_and_condition_model_pk primary key (ecm_id),
    constraint event_and_condition_model_fk1 foreign key (service_dictionary_id) references service_dictionary(service_dictionary_id) on delete cascade on update cascade,
    constraint event_and_condition_model_fk2 foreign key (category_dictionary_id) references specific_category_dictionary(category_dictionary_id) on delete cascade on update cascade,
    constraint event_and_condition_model_fk3 foreign key (component_category_id) references component_category(component_category_id) on delete cascade on update cascade
);

drop table if exists process_event cascade;
create table process_event
(
    insert_date_time      timestamp NOT NULL default current_timestamp,

    process_event_id      serial NOT NULL,
    pcm_id                varchar(256) NOT NULL,
    process_event_type    varchar(256) NOT NULL,
    condition             varchar(256) NOT NULL,
    target_data           varchar(256) NOT NULL,
    ecm_id                varchar(256) NOT NULL,

    constraint process_event_pk primary key (process_event_id),
    constraint process_event_fk1 foreign key (ecm_id) references event_and_condition_model(ecm_id) on delete cascade on update cascade
);

drop table if exists event_component_state cascade;
create table event_component_state
(
    insert_date_time            timestamp NOT NULL default current_timestamp,

    event_component_state_id    serial NOT NULL,
    dcm_id                      varchar(256) NOT NULL,
    state_id                    varchar(256) NOT NULL,
    dpd_name                    varchar(256) NOT NULL,
    ece                         varchar(256) NOT NULL,
    determination_method        varchar(256) NOT NULL,
    ecm_id                      varchar(256) NOT NULL,

    constraint event_component_state_pk primary key (event_component_state_id),
    constraint event_component_state_fk1 foreign key (ecm_id) references event_and_condition_model(ecm_id) on delete cascade on update cascade
);

drop table if exists user_operation_event cascade;
create table user_operation_event
(
    insert_date_time               timestamp NOT NULL default current_timestamp,

    user_operation_event_id        serial NOT NULL,
    operation_procedure_content    varchar(4096) NOT NULL,
    target_object                  varchar(256) NOT NULL,
    ecm_id                         varchar(256) NOT NULL,

    constraint user_operation_event_pk primary key (user_operation_event_id),
    constraint user_operation_event_fk1 foreign key (ecm_id) references event_and_condition_model(ecm_id) on delete cascade on update cascade
);

drop table if exists regular_event cascade;
create table regular_event
(
    insert_date_time        timestamp NOT NULL default current_timestamp,

    regular_event_id        serial NOT NULL,
    repeat_interval         integer,
    repeat_interval_unit    varchar(256),
    repeat_count            integer,
    repeat_end_date_time    varchar(256),
    daily                   varchar(256),
    weekly                  integer,
    monthly                 integer,
    ecm_id                  varchar(256) NOT NULL,
    
    constraint regular_event_pk primary key (regular_event_id),
    constraint regular_event_fk1 foreign key (ecm_id) references event_and_condition_model(ecm_id) on delete cascade on update cascade
);

drop table if exists dictionary_translation_map cascade;
create table dictionary_translation_map
(
    insert_date_time                  timestamp NOT NULL default current_timestamp,

    dtm_id                            varchar(256) NOT NULL,
    source_data_dictionary_id         varchar(256) NOT NULL,
    source_dcm_id                     varchar(256) NOT NULL,
    source_drm_id                     varchar(256),
    destination_data_dictionary_id    varchar(256) NOT NULL,
    destination_dcm_id                varchar(256) NOT NULL,
    destination_drm_id                varchar(256),
    is_tentative                      boolean NOT NULL,
    owner_id                          varchar(256) NOT NULL,
    
    constraint dictionary_translation_map_pk primary key (dtm_id),
    constraint dictionary_translation_map_fk1 foreign key (source_data_dictionary_id) references data_dictionary(data_dictionary_id),
    constraint dictionary_translation_map_fk2 foreign key (source_dcm_id) references data_component_model(dcm_id),
    constraint dictionary_translation_map_fk3 foreign key (source_drm_id) references data_relation_model(drm_id),
    constraint dictionary_translation_map_fk4 foreign key (destination_data_dictionary_id) references data_dictionary(data_dictionary_id),
    constraint dictionary_translation_map_fk5 foreign key (destination_dcm_id) references data_component_model(dcm_id),
    constraint dictionary_translation_map_fk6 foreign key (destination_drm_id) references data_relation_model(drm_id)
);

drop table if exists property_translation_map cascade;
create table property_translation_map
(
    insert_date_time        timestamp NOT NULL default current_timestamp,

    ptm_id                  serial NOT NULL,
    source_dpd_id           serial NOT NULL,
    destination_dpd_id      serial NOT NULL,
    dtm_id                  varchar(256) NOT NULL,

    constraint property_translation_map_pk primary key (ptm_id),
    constraint property_translation_map_fk1 foreign key (dtm_id) references dictionary_translation_map(dtm_id) on delete cascade on update cascade,
    constraint property_translation_map_fk2 foreign key (source_dpd_id) references data_property_definition(dpd_id),
    constraint property_translation_map_fk3 foreign key (destination_dpd_id) references data_property_definition(dpd_id)
);
