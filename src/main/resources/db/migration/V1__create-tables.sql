create table users(
    id bigint not null auto_increment,
    name varchar(50) not null,
    password varchar(100) not null,
    email varchar(100) not null unique,

    primary key (id)
);

create table courses(
    id bigint not null auto_increment,
    name varchar(100) not null,
    category varchar(50) not null,

    primary key (id)
);

create table topics(
    id bigint not null auto_increment,
    title varchar(100) not null,
    message text not null,
    createdAt datetime not null,
    status boolean not null,
    course_id bigint not null,


    primary key (id),
    constraint fk_topics_course_id foreign key(course_id) references courses(id)
);