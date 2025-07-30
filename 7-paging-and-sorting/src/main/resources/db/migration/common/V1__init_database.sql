drop table if exists book cascade;
drop table if exists author;

create table book (
    id bigint not null auto_increment primary key,
    isbn varchar(255),
    publisher varchar(255),
    title varchar(255),
    author_id bigint
) engine=InnoDB;

create table author (
    id bigint not null auto_increment primary key,
    first_name varchar(255),
    last_name varchar(255)
) engine=InnoDB;

alter table book add constraint book_author_fk foreign key (author_id) references author (id);

insert into author (first_name, last_name) values ('John', 'Doe');

insert into book (isbn, title, publisher, author_id)
    values ('1234567890', 'Spring In Action', 'Manning', (select id from author where first_name = 'John' and last_name = 'Doe'));

insert into book (isbn, title, publisher, author_id)
    values ('1234567891', 'Spring-Boot In Action, 1st Edition', 'Manning', (select id from author where first_name = 'John' and last_name = 'Doe'));

insert into book (isbn, title, publisher, author_id)
    values ('1234567892', 'Spring-Boot In Action, 6th Edition', 'Manning', (select id from author where first_name = 'John' and last_name = 'Doe'));


insert into author (first_name, last_name) values ('Miguel', 'de Cervantes');

insert into book (isbn, title, publisher, author_id)
    values ('1234567893', 'Don Quixote', 'Simon', (select id from author where first_name = 'Miguel' and last_name = 'de Cervantes'));

insert into book (isbn, title, publisher, author_id)
    values ('1234567894', 'El Quijote', 'Simon', (select id from author where first_name = 'Miguel' and last_name = 'de Cervantes'));


