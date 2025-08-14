drop table if exists product_category;
drop table if exists category;

create table category (
    id bigint not null auto_increment primary key,
    description varchar(50),
    created_date timestamp,
    last_modified_date timestamp
);

create table product_category (
    product_id bigint not null,
    category_id bigint not null,
    primary key (product_id, category_id),
    constraint fk_product_category_product foreign key (product_id) references product (id),
    constraint fk_product_category_category foreign key (category_id) references category (id)
);

insert into product (description, product_status, created_Date, last_modified_date) values ('Product 1', 'NEW', now(), now());
insert into product (description, product_status, created_Date, last_modified_date) values ('Product 2', 'NEW', now(), now());
insert into product (description, product_status, created_Date, last_modified_date) values ('Product 3', 'NEW', now(), now());
insert into product (description, product_status, created_Date, last_modified_date) values ('Product 4', 'NEW', now(), now());

insert into category (description, created_date, last_modified_date) values ('Category 1', now(), now());
insert into category (description, created_date, last_modified_date) values ('Category 2', now(), now());
insert into category (description, created_date, last_modified_date) values ('Category 3', now(), now());

insert into product_category (product_id, category_id) select p.id, c.id from product p, category c where p.description = 'Product 2' and c.description = 'Category 1';
insert into product_category (product_id, category_id) select p.id, c.id from product p, category c where p.description = 'Product 1' and c.description = 'Category 3';
insert into product_category (product_id, category_id) select p.id, c.id from product p, category c where p.description = 'Product 4' and c.description = 'Category 3';