create database if not exists posts;

show databases;

use posts;

show tables;

drop database posts;

drop table if exists user;

select * from user;
select * from posts;

Select u.name, u.last_name, u.login, p.postID
from user as u, posts as p
where u.userID = p.userID;

truncate table user;

