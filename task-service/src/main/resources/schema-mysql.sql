CREATE TABLE tb_task
(
 id varchar(100) NOT NULL,
 kategori varchar(100) NOT NULL,
 nama varchar(100) NOT NULL,
 is_finished tinyint(1) not null,
 created_date TIMESTAMP,
 PRIMARY KEY (id)
);