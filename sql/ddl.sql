
//데이터 베이스 생성
CREATE DATABASE study_db default CHARACTER SET UTF8;

//권한주기
GRANT ALL PRIVILEGES ON study_db.* TO joshua@localhost IDENTIFIED BY '1234';
EXIT;
mysql -u study_user -p
USE study_db;
