create database mealcard
on 
 ( name=mealcard_data,
   filename='D:\大学\SQL\mealcard\mealcard_data.mdf')
log on 
 ( name=mealcard_log,
   filename='D:\大学\SQL\mealcard\mealcard_log.ldf')

   create table Idcard(
		Sno char(10) primary key,
		Sname char(10) not null,
		Balance dec(6,1) default null check(Balance>=0),
		Adpat char(10),
   )

   create table restaurant(
		Rid char(10) primary key,
		Rname char(10) not null,
		Income dec(6,1) default null,
		Adress char(10) not null,
   )

   create table food(
		Fid char(10) not null primary key,
		Rid char(10) not null,
		Fname char(10) not null,
		Sort char(10) not null,
		Price dec(6,1) not null check(Price>0),
		foreign key (Rid) references restaurant(Rid),
   )

   create table consume(
		Cid int identity(1,1) primary key,
		Sno char(10)not null,
		Rid char(10)not null,
		Fid char(10)not null,
		CPrice dec(6,1),
		CDate date not null default null,
		foreign key (Sno) references Idcard(Sno),
		foreign key (Rid) references restaurant(Rid),
		foreign key (Fid) references food(Fid),
		index INDEX_Sno(Sno),
   )


   create table Acount(
		Aid int not null primary key,
		pwd varchar(50) not null
   )

   go
   create trigger CP_consume
   on consume
   after insert 
   as begin 
   update consume 
   set CPrice=(select Price from food where food.Fid = consume.Fid)
   end

   go
   create trigger or_Idcard
   on consume
   after insert
   as begin
   update Idcard set Balance=Balance-(select distinct CPrice from consume where consume.Sno = Idcard.Sno)
   update restaurant set Income=Income+(select distinct CPrice from consume where consume.Rid = restaurant.Rid)
   --update restaurant set Income=Income+(select CPrice from consume where consume.Rid = restaurant.Rid)
   end

   drop trigger or_Idcard

   go
   create proc login_in
   @Aid int,
   @pwd varchar(50)
   as begin
		if @pwd=(select pwd from Acount where Aid=@Aid)  return 1
		else return 0
   end  
   go

   go
   create proc approve
   @id  char(10)
   as begin
		if @id=(select Sno from Idcard) return 1
		else return 0
   end
   go

   go
   create proc RS_approve
   @id  char(10)
   as begin
		if @id=(select Rid from restaurant) return 1
		else return 0
   end
   go

   go
   create proc pay
   @money dec(6,1),
   @id char(10)
   as begin
      Update Idcard
	  set Balance=Balance+@money
	  where Sno=@id
	end
	go

	go
	create proc id_record
	@Sno char(10)
	as begin
		Select 		
			Cid as 消费编号,
			Sno as 学号,
			Rid as 餐厅号,
			Fid as 食物编号,
			CPrice as 价格,
			CDate as 消费时间
		from consume
		where Sno=@Sno
   end
   go
	
   go 
   create proc foodMenu
   @Rid char(10)
   as begin 
		Select 
			Fid as 食物编号,
			Rid as 餐厅编号,
			Fname as 食物名称,
			Sort as 食物类别,
			Price as 价格
		from food
		where Rid=@Rid
	end
	go

   go
   create proc ordering
   @Fid char(10),
   @Sno char(10),
   @Rid char(10)
   as begin
		Insert into consume(Fid,Sno,Rid,CDate) values (@Fid,@Sno,@Rid,GETDATE())
	end
	go


	exec ordering 1,2014,1 

	go
	create proc foodInsert
	@Fid char(10),
	@Rid char(10),
	@Fname char(10),
	@Sort char(10),
	@Price dec(6,1) 
	as begin
		--if not exists (select Fid from food = @Fid) then
		Insert into food(Fid,Rid,Fname,Sort,Price) values(@Fid,@Rid,@Fname,@Sort,@Price)
	end

	drop proc foodInsert

	go
	create proc RestaurantInsert
	@Rid char(10),
	@Rname char(10),
	@Income dec(6,1),
	@Adress char(10)
	as begin
		Insert into restaurant(Rid,Rname,Income,Adress) values(@Rid,@Rname,@Income,@Adress)
	end

	go
	create proc foodUpdate
	@Fid char(10),
	@Rid char(10),
	@Fname char(10),
	@Sort char(10),
	@Price dec(6,1) 
	as begin
		Update food set Fid=@Fid,Rid=@Rid,Fname=@Fname,Sort=@Sort,Price=@Price
	end

	