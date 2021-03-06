DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS promotion;

CREATE TABLE book (
  id BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(255),
  author VARCHAR(100) NOT NULL,
  type VARCHAR(100),
  price DOUBLE NOT NULL,
  isbn BIGINT NOT NULL,
  PRIMARY KEY (id)
  );

CREATE TABLE promotion (
    id BIGINT NOT NULL AUTO_INCREMENT,
    promocode VARCHAR(255) NOT NULL,
    booktype VARCHAR(255),
    discountprice DOUBLE NOT NULL
);