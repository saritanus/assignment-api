create table appliance
(
    id varchar(36) primary key ,
    serial_number varchar(32) not null,
    brand varchar(32) not null,
    model varchar(32) not null,
    purchase_date varchar(12),
    status varchar(32) not null,
    is_deleted boolean
);

