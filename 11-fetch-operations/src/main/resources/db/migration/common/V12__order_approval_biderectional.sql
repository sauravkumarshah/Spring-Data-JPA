alter table order_approval
    add column order_header_id bigint;

alter table order_approval
    add constraint fk_order_header_id
        foreign key (order_header_id)
            references order_header (id);