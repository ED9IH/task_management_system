create table users
(
    id       bigint generated always as identity
        primary key,
    email    varchar not null,
    password varchar not null,
    role     varchar not null
);


create table task
(
    id          bigint generated always as identity
        primary key,
    title       varchar not null,
    description varchar,
    status      varchar not null,
    priority    varchar not null,
    user_id     bigint  not null
        references users
);

create table comment
(
    id      bigint generated always as identity
        constraint comment_pk
            primary key,
    text    varchar(255),
    task_id bigint
        constraint fkfknte4fhjhet3l1802m1yqa50
            references task,
    author  varchar not null
);
