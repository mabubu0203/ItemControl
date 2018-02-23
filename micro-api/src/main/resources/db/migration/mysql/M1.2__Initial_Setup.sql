CREATE TABLE IF NOT EXISTS CATEGORY_TBL(
  ID BIGINT(10) UNSIGNED PRIMARY KEY AUTO_INCREMENT ,
  CODE VARCHAR(10) UNIQUE NOT NULL ,
  NAME VARCHAR(25) NOT NULL ,
  NOTE VARCHAR(64) NOT NULL
) DEFAULT CHARSET=UTF8;

CREATE TABLE IF NOT EXISTS CATEGORY_STATUS_TBL(
  CATEGORY_ID BIGINT(10) UNSIGNED UNIQUE NOT NULL ,
  VERSION INT(5) UNSIGNED NOT NULL DEFAULT '0' ,
  DELETE_FLAG BOOLEAN ,
  CREATE_DATETIME DATETIME NOT NULL ,
  UPDATE_DATETIME DATETIME NOT NULL ,
  FOREIGN KEY(CATEGORY_ID) REFERENCES CATEGORY_TBL(ID)
) DEFAULT CHARSET=UTF8;
