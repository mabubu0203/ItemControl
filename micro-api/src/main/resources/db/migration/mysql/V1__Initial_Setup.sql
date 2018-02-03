CREATE TABLE IF NOT EXISTS GOODS_TBL(
  ID BIGINT(10) UNSIGNED PRIMARY KEY AUTO_INCREMENT ,
  VERSION INT(5) UNSIGNED NOT NULL DEFAULT '0' ,
  CODE VARCHAR(10) UNIQUE NOT NULL ,
  NAME VARCHAR(25) NOT NULL ,
  PRICE SMALLINT(4) UNSIGNED ,
  NOTE VARCHAR(64) NOT NULL ,
  CREATE_DATETIME DATETIME NOT NULL ,
  UPDATE_DATETIME DATETIME NOT NULL
) DEFAULT CHARSET=UTF8;