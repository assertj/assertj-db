CREATE TABLE movie (
  id         NUMBER PRIMARY KEY,
  title      VARCHAR NOT NULL,
  year       NUMBER,
  movie_imdb UUID
);

CREATE TABLE actor (
  id         NUMBER PRIMARY KEY,
  name       VARCHAR NOT NULL,
  firstname  VARCHAR NOT NULL,
  birth      DATE,
  actor_imdb UUID
);

CREATE TABLE interpretation (
  id        NUMBER PRIMARY KEY,
  id_movie  NUMBER,
  id_actor  NUMBER,
  character VARCHAR,

  FOREIGN KEY (id_movie) REFERENCES movie (id),
  FOREIGN KEY (id_actor) REFERENCES actor (id)
);

CREATE TABLE test (
  var1  INT PRIMARY KEY,
  var2  BOOLEAN,
  var3  TINYINT,
  var4  SMALLINT,
  var5  BIGINT,
  var6  DECIMAL(20, 2),
  var7  REAL,
  var8  TIME,
  var9  DATE,
  var10 TIMESTAMP,
  var11 BINARY,
  var12 VARCHAR,
  var13 DECIMAL(20, 2),
  var14 REAL,
  var15 UUID
);

CREATE TABLE test2 (
  var1  INT,
  var2  BOOLEAN,
  var3  TINYINT,
  var4  SMALLINT,
  var5  BIGINT,
  var6  DECIMAL(20, 2),
  var7  REAL,
  var8  TIME,
  var9  DATE,
  var10 TIMESTAMP,
  var11 BINARY,
  var12 VARCHAR,
  var13 DECIMAL(20, 2),
  var14 REAL,
  var15 TINYINT,
  var16 UUID
);
