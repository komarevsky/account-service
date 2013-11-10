create table if not exists account (
    id int primary key not null,
    amount bigint not null default 0
);
