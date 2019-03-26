#查看当前所有的数据库
SHOW DATABASES;
#创建数据库
CREATE DATABASE test_db;
#查看数据库定义
SHOW CREATE DATABASE test_db;
#删除数据库 
DROP DATABASE test_db;
#查看数据库系统所支持的引擎类型
SHOW ENGINES;
#1：InnoDB是首选的事务型数据库引擎，支持事务安全表（ACID）Atomicity：原子性，Consistency：一致性，Isolation：隔离性 Durability：持久性
#2：InnoDB给MySQL提供了具有提交，回滚和崩溃恢复能的事务安全的存储引擎，
#3：InnoDB锁定在行级，并且也在SELECT语句中提供了一个类似Oracle的非锁定读。
#4：在SQL查询中，可以自由地将InnoDB类型的表和其他MySQL的表的类型混合起来，甚至在同一个查询语句中也可以混合。ALTER
#5：InnoDB是为处理巨大数据量的最大性能而设计的，它的CPU效率可能是任何其他基于磁盘的关系型数据库引擎所不能匹敌的。ALTER
#6：InnoDB存储引擎完全与MySQL服务器整合，InnoDB存储引擎为在主内存中缓存数据和索引维持它自己的缓冲池。
#7：InnoDB将它的表和索引 存在一个逻辑表空间中，表空间可以包含数个文件（或原始磁盘分区）
#8：InnoDB表可以是任何大小的，即使文件的大小被限制为2GB的操作系统上面。
#9：InnoDB支持外键完整性约束，存储表中的数据时，每张表的存储都按主键顺序存放，如果没有显示在表定义时指定主键，InnoDB会为每一行生成一个6B的ROWID，并以此作为主键
#10：InnoDB被用在众多需要高性能的大型数据库站点上 InnoDB不创建目录，使用InnoDB时，MySQL将在MySQL的数据目录（my.cnf datadir定义）
#11：下创建一个名为ibdata1的10MB大小的自动扩展数据文件，以及两个名为ib_logfile0和ib_logfile1的5MB的大小的日志文件。

#MyISAM
#MyISAM是基于ISAM（ Indexed Sequential Access Method，索引顺序存取法，是IBM发展起来的文件系统，可以根据索引或者文件进行的顺序，任意地访问存储的文件）
#的存储引擎，并进行了扩展。它是在Web，数据存储和其他应用环境下最常用的存储引擎之一，MyISAM拥有较高的插入，查询速度，但很遗憾不支持事务啊。
#在MySQL5.5.5之前它是默认的数据库引擎，后面就换成了前面我们说的InnoDB了，它主要的特性有：
#1：大文件（达63位文件长度）在支持大文件的文件系统和操作系统上被支持。（被超越了）
#2：当把删除，更新及插入操作混合使用的时候，动态大小的行产生更少的碎片。这要通过合并相邻被删除的块，以及若下一个块被删除，就扩展到下一块来自动完成。
#3：每个MyISAM表最大索引数是64，这个通过编译来改变，每个索引最大的列数是16个
#4:最大的键长度是1000B，这个也可以通过编译来改变，对于键长超过250B的情况，一个超过1024B的键将被用上。
#5:BLOB和TEXT列可以被索引ALTER
#6:NULL值被允许在索引的列中，这个值占每个键的0-1个字节
#7:所有数字键值以高字节优先被存储以允许一个更高的索引压缩。
#8：每个表一个AUTO_INCREMENT列的内部处理,MyISAM为INSERT和UPDATE操作自动更新这一列,这使得AUTO_INCREMENT列更快.在序列顶的值被删除就不能再利用了ALTER
#9:可以把数据文件和索引文件放在不同目录ALTER
#10:每个字符列可以有不同的字符集
#11:有VARCHAR的表可以固定或动态记录长度
#12:VARCHAR和CHAR列可以多达64KB
#使用MyISAM引擎创建数据库,将生产3个文件,文件的名字以表的名字开始,扩展名指出文件类型,frm文件存储表定义,数据文件的扩展名为.MYD(MYData),索引文件的扩展名为.MYI(MYIndex)

#查看默认存储引擎
SHOW VARIABLES LIKE 'storage_engine';

#创建表
USE test_db;
CREATE TABLE tb_emp (
    id INT(11),
    name VARCHAR(25),
    deptId INT(11),
    salary FLOAT
);

#查看所有的表
SHOW TABLES;

CREATE TABLE tb_emp2 (
    id INT(11) PRIMARY KEY,
    name VARCHAR(25),
    deptId INT(11),
    salary FLOAT
);

CREATE TABLE tb_emp3 (
    id INT(11),
    name VARCHAR(25),
    deptID INT(11),
    saraly FLOAT,
    PRIMARY KEY (id)
);

CREATE TABLE tb_emp4 (
    name VARCHAR(25),
    deptId INT(11),
    saraly FLOAT,
    PRIMARY KEY (name , deptId)
);

#外键:外键用来在两个表的数据之间建立链接的,它可以是一列或者多列,一个表可有一个或者多个外键.外键对应是参照完整性,一个表的外键可以为空值,
#若不为空值,则每一个外键值必须等于另一个表中的主键的某个值.
#外键,首先它是表中的一个字段,它可以不是本表中的主键,但对应的是另一个表的主键(必须是主键),外键主要作用是保证数据引用的完整性,定义外键后,
#不允许删除在另一个表中具有关联关系的行.例如下面的,部门表tb_debt1的主键是id,在员工表tb_emp5中有一个键detpId与这个id关联.
#主表:对于两个具有关联关系的表而言,相关联字段中主键所在的那个表即为主表.
#从表:对于两个具有关联关系的表而言,相关联字段中外键所在的那个表即为从表.
#语法 [CONSTRAINT <外键名> FOREIGN KEY (字段1)[,字段2] REFERENCES <主表名> 主键列1,[,主键列2]]
# "外键名"为定义外键约束的的名称,一个表中不能有相同名称的外键,"字段名"表示子表需要添加外键约束的字段列,"主表名":表示子表外键所依赖的表的名称,"主键列":主表中的主键
#子表的外键必须关联到主表的主键上,并且关键的字段的数据类型必须匹配,否则在创建表的时候会报错.
SHOW TABLES;
CREATE TABLE tb_dept1 (
    id INT(11) PRIMARY KEY,
    name VARCHAR(22) NOT NULL,
    location VARCHAR(50)
);
CREATE TABLE tb_emp5 (
    id INT(11) PRIMARY KEY,
    name VARCHAR(25),
    deptId INT(11),
    saraly FLOAT,
    CONSTRAINT fk_emp5_dept1 FOREIGN KEY (deptId)
        REFERENCES tb_dept1 (id)
);

#非空约束.

CREATE TABLE tb_emp6(
	id INT(11) PRIMARY KEY,
    name VARCHAR(25) NOT NULL,
    deptId INT(11),
    saraly FLOAT
);

#唯一约束
CREATE TABLE tb_dept2(
	id INT(11) PRIMARY KEY,
    name VARCHAR(22) UNIQUE,
    location VARCHAR(50)
);

#[ CONSTRAINT <约束名> UNIQUE(<字段名>)]

CREATE TABLE tb_empt3(
	id INT(11) PRIMARY KEY,
    name VARCHAR(22),
    location VARCHAR(50),
    CONSTRAINT STH UNIQUE(name)
);
#UNIQUE和PRIMARY KEY的区别:一个表中可以有多个字段声明为UNIQUE,但只能有一个字段被声明为PRIMARY KEY,
#声明为PRIMARY KEY的列不允许为空值(NULL),但是声明为UNIQUE的字段可以为空值(NULL)

#默认约束:指定列的默认值
#如果插入一条新的记录时,没有为这个字段赋值，那么系统为自动为这个字段设置为你设置的默认值
CREATE TABLE tb_emp7(
	id INT(11) PRIMARY KEY,
    name VARCHAR(25) NOT NULL,
    deptId INT(11) DEFAULT 1,
    salary FLOAT
);

#设置属性值自动增加,一个表只能有一个字段使用AUTO_INCREMENT约束,且该字段只能是主键的一部分（主键是有两种的噢，如果回忆不起来，看上面）,它可以约束任务整形类型(TINYINT,SMALLINT,MEDIUMINT,INT,BIGINT等),默认添加一条新的记录,就会自动增加1.
CREATE TABLE tb_emp8(
	id INT(11) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(25) NOT NULL,
    deptId INT(11) DEFAULT 1,
    salary FLOAT
);

#查看数据表的基本结构
#NULL:表示该列是否可以存储NULL值
#Key:表示该列是否已编制索引.PRI表示该列是表主键的一部分;UNI:表示该列是UNIQUE索引的一部分;MUL表示该列中某个给定值允许出现多次.
#Default:表示该列是否有默认值,如果有的话值是多少.
#Extra:表示可以获取的与给定列有关的附加信息,比如AUTO_INCREMENT等.
DESCRIBE tb_emp8;
DESC tb_emp8;
DESCRIBE tb_dept2;

#查看表的详细结构 
SHOW CREATE TABLE tb_emp8;

SHOW TABLES;

SHOW CREATE TABLE tb_dept3;

#修改表名
ALTER TABLE tb_empt3 RENAME TO tb_dept3;

DESCRIBE tb_dept1;

#修改字段的数据类型
ALTER TABLE tb_emp MODIFY name VARCHAR(30);

#修改字段名 
ALTER TABLE tb_dept1 CHANGE location loc VARCHAR(55);

#添加字段:无约束,有约束,添加到第一列,添加到指定列后面,默认是添加到最后.
ALTER TABLE tb_dept1 ADD phone VARCHAR(11);
#删除字段
ALTER TABLE tb_dept1 DROP phone;
#修改字段的排列位置
#更改表的存储引擎
ALTER TABLE tb_emp ENGINE=MyISAM;
#删除表的外键约束
#删除表:如果存在外键约束,那么主能是不能直接删除的,需要先删除外键约束.

#水果表
CREATE TABLE fruits(
	f_id char(10) PRIMARY KEY,
    s_id INT NOT NULL,
    f_name char(255) NOT NULL,
    f_price decimal(8,2) NOT NULL
);

INSERT INTO fruits(f_id,s_id,f_name,f_price)
VALUES('a1',101,'apple',5.2),
('b1',101,'blackberry',10.2),
('bs1',102,'orange',11.2),
('bs2',105,'melon',8.2),
('t1',102,'banana',10.3),
('t2',102,'grape',5.3),
('o2',103,'coconut',9.2),
('c0',101,'cherry',3.2),
('l2',104,'lemon',6.4),
('b2',104,'berry',7.6),
('m1',106,'mango',15.7),
('m2',105,'xbabay',2.6),
('t4',107,'xbababa',3.6),
('m3',105,'xxtt',11.6),
('b5',107,'xxxx',3.6);


#供应商
CREATE TABLE suppliers(
	s_id int PRIMARY KEY AUTO_INCREMENT,
    s_name char(50) NOT NULL,
    s_city char(50) NULL,
    s_zip char(10) NULL,
    s_call char(50) NOT NULL
);

INSERT INTO  suppliers(s_id,s_name,s_city,s_zip,s_call)
VALUES(101,'FastFruit Inc','TianJin','300000','48075'),
(102,'LT suppliers','Chongqing','400000','44333'),
(103,'ACME','Shanghai','200000','90046'),
(104,'FNK Inc','ZhongShan','030000','11111'),
(105,'Good Set','Taiyuang','010','22222'),
(106,'Just eat Ours','Beijing','400000','45678'),
(107,'DK Inc','Zhengzhou','450000','33332');

DESCRIBE fruits;
DESCRIBE suppliers;

SELECT suppliers.s_id, suppliers.s_name,f_name,f_price FROM fruits,suppliers WHERE fruits.s_id = suppliers.s_id;

SELECT suppliers.s_id, suppliers.s_name,f_name,f_price FROM fruits INNER JOIN suppliers ON fruits.s_id = suppliers.s_id;

CREATE TABLE customers(
	c_id INT PRIMARY KEY AUTO_INCREMENT,
    c_name char(50)  NOT NULL,
    c_address char(50) NULL,
    c_city char(50) NULL,
    c_zip char(50) NULL,
    c_contact char(50) NULL,
    c_email char(255) NULL
);

INSERT INTO customers(c_id,c_name,c_address,c_city,c_zip,c_contact,c_email)
VALUES(10001,'RedHook','200 street','Tianjin','300000','Liming','Liming@163.com'),
(10002,'Star','333 street','Dalian','116000','Zhangbo','Zhangbo@163.com'),
(10003,'Netbhood','1 street','Qindao','266000','LuoCong',NULL),
(10004,'JOTO','829 street','Haikou','570000','Yangshan','sam@hotmail.com');

CREATE TABLE orders(
	o_num int PRIMARY KEY AUTO_INCREMENT,
    o_date DATETIME NOT NULL,
    c_id int NOT NULL
);

INSERT INTO orders VALUES(30001,'2008-09-01',10001),
(30002,'2008-09-02',10003),
(30003,'2008-09-03',10004),
(30004,'2008-09-04',10005),
(30005,'2008-09-05',10001);

#左连接:返回包括左表中的所有记录和右表中连接字段相等记录.
SELECT customers.c_id,orders.o_num FROM customers LEFT OUTER JOIN orders ON customers.c_id = orders.c_id;
#右连接:返回包括右表中的所有记录和表表中连接字段相等记录.
SELECT customers.c_id,orders.o_num FROM customers RIGHT OUTER JOIN orders ON customers.c_id = orders.c_id;
