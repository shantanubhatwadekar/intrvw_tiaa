create table IF NOT EXISTS AMEXNUMBERS
(
   cc_number bigint not null,
   primary key(cc_number)
);
create table IF NOT EXISTS VISANUMBERS
(
   cc_number bigint not null,
   primary key(cc_number)
);
create table IF NOT EXISTS MASTERNUMBERS
(
   cc_number bigint not null,
   primary key(cc_number)
);
create table IF NOT EXISTS DISCOVERNUMBERS
(
   cc_number bigint not null,
   primary key(cc_number)
);