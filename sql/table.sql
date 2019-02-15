create table if not exists members(
memberId varchar(10),
password varchar(16),
memberName varchar(10),
email varchar(30),
phone char(11),
memberLevel int,
balance double,
code varchar(50),
state int,
primary key (memberId)
);