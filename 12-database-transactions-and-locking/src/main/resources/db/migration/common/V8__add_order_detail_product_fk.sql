alter table order_line add column product_id bigint;
alter table order_line add constraint fk_order_line_product foreign key (product_id) references product (id);