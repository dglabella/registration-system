--query all user from all databases (root)
select user from mysql.user;

--change user's password
alter user 'username'@'localhost' identified by 'newpassword';