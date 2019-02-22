create table if not exists members(
memberId varchar(10),
password varchar(16),
email varchar(30),
memberName varchar(10),
phone char(11),
memberLevel int,
balance double,
state int,
code char(32),
primary key (memberId)
);

create table if not exists memberDeliveryAddrs(
addrId int auto_increment,
memberId varchar(10),
longitude double,
latitude double,
addrName varchar(100),
primary key (addrId)
);

create table if not exists restaurants(
restaurantId char(7),
password varchar(16),
restaurantName varchar(10),
longitude double,
latitude double,
addrName varchar(100),
balance double,
restaurantType int,
primary key (restaurantId)
);

create table if not exists restaurantInfoChanges(
id int auto_increment,
restaurantId char(7),
restaurantName varchar(10),
longitude double,
latitude double,
addrName varchar(100),
restaurantType int,
isExamined boolean,
isApproved boolean,
primary key (id)
);

create table if not exists managers(
managerId char(7),
password varchar(16),
primary key (managerId)
);

create table if not exists products(
productId int auto_increment,
restaurantId char(7),
productName varchar(20),
quantity int,
price double,
primary key (productId)
);

create table if not exists productItems(
id int auto_increment,
productId int,
itemId int,
primary key (id)
);

create table if not exists orders(
orderId int auto_increment,
memberId varchar(10),
restaurantId char(7),

placingOrderTime timestamp,
payDeadline timestamp,
fulfillingOrderTime timestamp ,

senderLongitude double ,
senderLatitude double ,
senderAddrName varchar(100),
receiverLongitude double ,
receiverLatitude double ,
receiverAddrName varchar(100),

totalAmount double ,
refund double ,
state int,

primary key (orderId)
);

create table if not exists orderItems(
id int auto_increment,
orderId int,
productId int,
itemAmount int,
itemPrice double,
primary key (id)
);

create table if not exists yummyBills(
tradingDate timestamp ,
orderId int ,
isSettled boolean ,
settleAmount double,
primary key (tradingDate)
);