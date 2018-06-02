--���ô洢���̴����ļ���
exec sp_configure 'show advanced options',1 reconfigure

exec sp_configure 'xp_cmdshell',1 reconfigure

exec xp_cmdshell 'mkdir D:\SQL\SURVEY' reconfigure


--��master�鿴�������Ŀ��ļ��Ƿ����

use master

--����survey�⣬���survey���Ѿ����ھͰ�ԭ���Ŀ�ɾ�����´���

if exists(select * from sysdatabases where name = 'survey')

 drop database survey


go

--�������ݿ�survey

create database survey
on
(
  name = 'survey_data',
  filename = 'D:\SQL\SURVEY\survey_data.mdf',
  size = 3mb,
  maxsize = unlimited,
  filegrowth = 15%
)
log on
(
  name = 'survey_log',
  filename = 'D:\SQL\SURVEY\survey_log.ldf',
  size = 1mb,
  maxsize = unlimited,
  filegrowth = 15%
)

go

-----�����ʾ�ϵͳ���ݿ�

use survey

--1.�������������ͱ�
if exists(select * from sysobjects where name = 'ItemTypeTable')
 drop table ItemTypeTable

create table ItemTypeTable
(
ItemType int constraint ItemTypeTable_pk primary key,
Statement varchar(50) not null
)
go

--����������ͱ�������ݣ�0��ʾ��ѡ��1��ʾ��ѡ��2��ʾ�ı�
insert into ItemTypeTable
select 0,'RadioItem' union
select 1,'CheckboxItem' union
select 2,'TextItem'

select * from ItemTypeTable 
go

--2.�������������Ա�
if exists(select * from sysobjects where name = 'ItemAttributeTable')
 drop table ItemAttributeTable

create table ItemAttributeTable
(
ItemAttribute int constraint ItemAttributeTable_pk primary key,
Statement varchar(30) not null
)
--����������Ա�������ݣ�0��ʾ���У�1��ʾ˽��
insert into ItemAttributeTable
select 0,'public' union
select 1,'private'

select * from ItemAttributeTable
go


--3.�����Ƿ�Ĭ��ѡ�б�
if exists(select * from sysobjects where name = 'IfDefaultSelectedTable')
 drop table IfDefaultSelectedTable

create table IfDefaultSelectedTable
(
DefaultSelected int constraint IfDefaultSelectedTable_pk primary key,
Statement varchar(30) not null
)
--���Ƿ�Ĭ��ѡ�б��в������ݣ�0��ʾĬ�ϲ�ѡ�У�1��ʾĬ��ѡ��
insert into IfDefaultSelectedTable
select 0,'NotSelected' union
select 1,'Selected'

select * from IfDefaultSelectedTable 
go

--4.��������Ա���ͱ�
if exists(select * from sysobjects where name = 'UserTypeTable')
 drop table UserTypeTable


create table UserTypeTable
(
UserType int constraint UserTypeTable_pk primary key,
Statement varchar(20) not null
)
go
--�����Ա���ͱ��в������ݣ�0Ϊ��ͨ����Ա��1Ϊϵͳ����Ա
insert into UserTypeTable
select 0,'GeneralManager' union
select 1,'Administrator'

select * from UserTypeTable
go


--5.��������Ա��Ϣ��
if exists(select * from sysobjects where name = 'UserTable')
 drop table UserTable

create table UserTable
(
UserID int constraint UserTable_pk primary key identity(1000,1),
UserPassword varchar(50) not null,
UserName varchar(50)not null unique,
UserType int not null
)
go
--�����Ա�����ϵͳ����Ա������Ϊ1
insert into UserTable(UserPassword,UserName,UserType)
values('admin','admin',1)
go
select * from UserTable

--6.���������ʾ���Ϣ��
if exists(select * from sysobjects where name = 'SurveyTable')
 drop table SurveyTable

create table SurveyTable
(
SurveyID int constraint SurveyTable_pk primary key identity(1,1),
SurveyTitle varchar(150),
SurveyOwnerID int constraint SurveyTable_OwnerID_fk foreign key references UserTable(UserID),
SurveyLink varchar(100),
SurveyCreateDateTime DateTime default(getdate()),--�����ʾ���ʱ��
SurveyExpirationDateTime varchar(12),--�����ʾ����ʱ��

)

select * from SurveyTable
go

--7.������������Ϣ��

if exists(select * from sysobjects where name = 'VisitorTable')
 drop table VisitorTable

create table VisitorTable
(
VisitorIP varchar(30) not null,
visiteDateTime DateTime not null default(getdate()),
VisitorNumber int constraint VisitorTable_pk primary key identity(1,1),
VisiteSurveyID int constraint VisitorTable_SurveyID_fk foreign key references SurveyTable(SurveyID),
VisiteSurveyData varchar(1000) not null--�����ǲ��ǵ��������ݴ�С����
)

select * from VisitorTable
go

--8.������������Ϣ��

if exists(select * from sysobjects where name = 'ItemTable')
 drop table ItemTable

create table ItemTable
(
ItemID int constraint ItemTable_pk primary key identity(1,1),
ItemCaption varchar(100) not null,
ItemType int constraint ItemTable_Type_fk foreign key references ItemTypeTable(ItemType),
ItemAttribute int constraint ItemTable_Attribute_fk foreign key references ItemAttributeTable(ItemAttribute) default(1),
ItemOwnerID int constraint ItemTable_Owner_fk foreign key references UserTable(UserID),
ItemOwnerSurveyID int constraint ItemTable_OwnerSurvey_fk foreign key references SurveyTable(SurveyID),
RadioCheckboxCount int not null
)

go
select * from ItemTable

--9.���������ʾ�͵�����Ĺ�����

if exists(select * from sysobjects where name = 'SurveyAssociateItem')
 drop table SurveyAssociateItem

create table SurveyAssociateItem
(
SurveyID int,
ItemID int,
constraint SurveyAssociateItemTable_pk primary key(SurveyID,ItemID)
)

go

select * from SurveyAssociateItem

--10.������ѡ��

if exists(select * from sysobjects where name = 'RadioTable')
 drop table RadioTable

create table RadioTable
(
RadioOwnerID int constraint RadioTable_Owner_fk foreign key references ItemTable(ItemID),
RadioIndex int not null,
constraint RadioTable_pk primary key(RadioOwnerID,RadioIndex),
RadioCaption varchar not null,
DefaultSelected int constraint RadioTable_Owner_fk1 foreign key references IfDefaultSelectedTable(DefaultSelected),
SelectedCount int not null
)

---�޸ĺ�����ݿ��
create table RadioTable
(
  RadioID int  constraint RadioTable_pk primary key(RadioID) identity(1,1),
  RadioOwnerID int constraint RadioTable_Owner_fk foreign key references ItemTable(ItemID),
  RadioIndex int not null,
  RadioCaption varchar(300) not null,
  DefaultSelected int constraint RadioTable_Owner_fk1 foreign key references IfDefaultSelectedTable(DefaultSelected),
  SelectedCount int not null
)

go
select * from RadioTable

--11.������ѡ���

if exists(select * from sysobjects where name = 'CheckboxTable')
 drop table CheckboxTable

create table CheckboxTable
(
CheckboxOwnerID int constraint CheckboxTable_Owner_fk foreign key references ItemTable(ItemID),
CheckboxIndex int not null,
constraint CheckboxTable_pk primary key(checkboxOwnerID,CheckboxIndex),
CheckboxCaption varchar(50),
DefaultSelected int constraint RadioTable_Owner_fk foreign key references IfDefaultSelectedTable(DefaultSelected),
SelectedCount int not null
)

----�޸ĺ�����ݿ��
create table CheckboxTable
(
CheckboxID int  constraint CheckboxTable_pk primary key(CheckboxID) identity(1,1),
CheckboxOwnerID int constraint CheckboxTable_Owner_fk foreign key references ItemTable(ItemID),
CheckboxIndex int not null,
CheckboxCaption varchar(50),
DefaultSelected int constraint CheckboxTable_Owner_fk1 foreign key references IfDefaultSelectedTable(DefaultSelected),
SelectedCount int not null
)

select * from CheckboxTable
go

--12.�������ֱ�
if exists(select * from sysobjects where name = 'TextTable')
 drop table TextTable


create table TextTable
(
TextOwnerID int constraint TextTable_OwnerID_fk foreign key references ItemTable(ItemID),
TextCaption varchar(100),
constraint TextTable_pk primary key(TextOwnerID,TextCaption),
TextContent varchar(1000)
)

--�޸ĺ�����ݿ��

create table TextTable
(
  TextID int  constraint TextTable_pk primary key(TextID) identity(1,1),
  
  TextOwnerID int constraint TextTable_OwnerID_fk foreign key references ItemTable(ItemID),

  TextCaption varchar(100),
  
  TextContent varchar(1000)
)

select * from TextTable