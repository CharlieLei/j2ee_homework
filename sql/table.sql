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
restaurantId char(7),
restaurantName varchar(10),
longitude double,
latitude double,
addrName varchar(100),
restaurantType int,
primary key (restaurantId)
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
productId int,
itemId int,
primary key (productId, itemId)
);

create table if not exists orders(
orderId int auto_increment,
memberId varchar(10),
restaurantId char(7),

placingOrderTime timestamp,
fulfillingOrderTime timestamp ,

senderLogitude double ,
senderLatitude double ,
receiverLogitude double ,
receiverLatitude double ,

totalAmount double ,
refund double ,
state int,

primary key (orderId)
);

create table if not exists orderItems(
orderId int,
productId int,
primary key (orderId, productId)
);

create table if not exists yummyBills(
tradingDate timestamp ,
memberId varchar(10) ,
orderId int ,
isSettled boolean ,
primary key (tradingDate)
);