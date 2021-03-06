CREATE TABLE IF NOT EXISTS GOODS_TBL(
  ID BIGINT(10) UNSIGNED PRIMARY KEY AUTO_INCREMENT ,
  CODE VARCHAR(10) UNIQUE NOT NULL ,
  CATEGORY_ID BIGINT(10) UNSIGNED ,
  NAME VARCHAR(25) NOT NULL ,
  PRICE SMALLINT(4) UNSIGNED ,
  NOTE VARCHAR(64) NOT NULL ,
  CONSTRAINT GOODS_TBL_CATEGORY_ID FOREIGN KEY(CATEGORY_ID) REFERENCES CATEGORY_TBL(ID)
) DEFAULT CHARSET=UTF8;

CREATE TABLE IF NOT EXISTS GOODS_STATUS_TBL(
  GOODS_ID BIGINT(10) UNSIGNED PRIMARY KEY NOT NULL ,
  VERSION INT(5) UNSIGNED NOT NULL DEFAULT '0' ,
  DELETE_FLAG BOOLEAN NOT NULL DEFAULT FALSE ,
  CREATE_DATETIME DATETIME NOT NULL ,
  UPDATE_DATETIME DATETIME NOT NULL ,
  CONSTRAINT GOODS_STATUS_TBL_GOODS_ID FOREIGN KEY(GOODS_ID) REFERENCES GOODS_TBL(ID)
) DEFAULT CHARSET=UTF8;