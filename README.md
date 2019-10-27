# repository1  
NAME  
"mileage-diary"  
  
Description  
This app is for managing the mileage, refueling amount, fuel consumption etc. of a car.  
You can create new data, view data, modify or delete the latest data.  
Java8, JavaFX, MySQL are used.  
  
MySQL Table Structure  
Database Name - "record", Table Name - "record"
  
id int unsigned not null auto_increment,  
km double(7,2),  
totalKm double(8,2) as sum(km) from test1 where ,  
litter double(5,2),  
totalLitter double(8,2),  
mileage double(5,2),  
totalMileage double(5,2),  
price int(3),  
cost int(6),  
totalCost int(7),  
memo varchar(100),  
updated_at datetime default current_timestamp,  
primary key(id)  
