# Tips.md



#### 一些小说明

1. server port:8888

2. user的id为0为特殊身份者，应当保留

3. MYsql DATABASE: game0

   [SHOW CREATEs](  "for CV 🤓☝️")

   ````mysql
   CREATE DATABASE game0;
   
   USE game0; 
   
   CREATE TABLE users 
   (
   	id INT AUTO_INCREMENT PRIMARY KEY ,
       name VARCHAR(100) UNIQUE NOT NULL,
       email VARCHAR(100) UNIQUE,
       keys_id INT NOT NULL,
       `password` VARCHAR(100) NOT NULL,
       register_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 
       sign VARCHAR(255) NOT NULL DEFAULT "0"
   );
   
   CREATE TABLE `keys`
   (
       id INT PRIMARY KEY,
       `code` CHAR(30) NOT NULL 
   );
   
   CREATE TABLE rank0
   (
       users_id INT,
       sc0re BIGINT DEFAULT 0,
       map0_id INT,
       `rank` INT,
       UNIQUE(users_id,map0_id)
   );
   
   CREATE TABLE logined
   (
       id INT AUTO_INCREMENT PRIMARY KEY,
   	users_id INT NOT NULL,
       ip VARCHAR(45) DEFAULT "0.0.0.0",
       time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
       login_or_out TINYINT NOT NULL
   );
   
   CREATE TABLE bl0cks
   (
       map0_id INT NOT NULL,
       x BIGINT NOT NULL,
       y BIGINT NOT NULL,
       num TINYINT NOT NULL DEFAULT -1,
       gr0up_id INT NOT NULL DEFAULT 0,
       belong_to_users_id INT DEFAULT 0,
       choosable TINYINT NOT NULL DEFAULT false,
       UNIQUE(map0_id,x,y)
   );
   
   CREATE TABLE map0s
   (
   	id INT AUTO_INCREMENT PRIMARY KEY,
       users_id INT COMMENT "who create this map",
       seed VARCHAR(100) NOT NULL,
       create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 
       biggest_gr0up_id INT DEFAULT 0
   );
   
   CREATE TABLE gr0ups
   (
   	id INT NOT NULL,
       father_id INT NOT NULL DEFAULT 0,
       map0_id INT NOT NULL,
       users_id INT NOT NULL,
       bl0ck_count INT NOT NULL DEFAULT 0,
       UNIQUE(id,map0_id)
   )COMMENT "yes i'm an additional helpless table";
   
   
   CREATE TABLE guess_number
   (
   	users_id INT NOT NULL,
       answer INT NOT NULL,
       guess INT,
       times INT,
       try_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
   );
   
   CREATE TABLE rank_guess
   (
       users_id INT UNIQUE,
       `score` BIGINT DEFAULT 0,
       `rank` INT DEFAULT -1
   );
   ````




