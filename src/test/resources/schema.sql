create table movie(
    id number primary key,
    title varchar not null,
    year number,
    movie_imdb UUID
);

create table actor(
    id number primary key,
    name varchar not null,
    firstname varchar not null,
    birth date,
    actor_imdb UUID
);

create table interpretation(
    id number primary key,
    id_movie number,
    id_actor number,
    character varchar,

    foreign key (id_movie) references movie(id),
    foreign key (id_actor) references actor(id)
);

create table test(
    var1 int primary key,
    var2 boolean,
    var3 tinyint,
    var4 smallint,
    var5 bigint,
    var6 decimal(20,2),
    var7 real,
    var8 time,
    var9 date,
    var10 timestamp,
    var11 binary,
    var12 varchar,
    var13 decimal(20,2),
    var14 real,
    var15 UUID
);

create table test2(
    var1 int,
    var2 boolean,
    var3 tinyint,
    var4 smallint,
    var5 bigint,
    var6 decimal(20,2),
    var7 real,
    var8 time,
    var9 date,
    var10 timestamp,
    var11 binary,
    var12 varchar,
    var13 decimal(20,2),
    var14 real,
    var15 tinyint,
    var16 UUID
);


create table test3(
    var1 TEXT
);

