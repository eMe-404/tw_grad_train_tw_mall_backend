create table tw_user (
  id bigint(20) primary key,
  username varchar(50) not null,
  password varchar(100) not null,
  fullname varchar(50),
  role varchar(50) not null
) default charset utf8;
