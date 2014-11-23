delete from test2;
delete from test;
delete from interpretation;
delete from actor;
delete from movie;

insert into movie values(1, 'Alien', 1979);
insert into movie values(2, 'The Village', 2004);
insert into movie values(3, 'Avatar', 2009);

insert into actor values(1, 'Weaver', 'Sigourney', PARSEDATETIME('08/10/1949', 'dd/MM/yyyy'));
insert into actor values(2, 'Phoenix', 'Joaquim', PARSEDATETIME('28/10/1974', 'dd/MM/yyyy'));
insert into actor values(3, 'Worthington', 'Sam', PARSEDATETIME('02/08/1976', 'dd/MM/yyyy'));

insert into interpretation values(1, 1, 1, 'Ellen Louise Ripley');
insert into interpretation values(2, 2, 1, 'Alice Hunt');
insert into interpretation values(3, 3, 1, 'Dr Grace Augustine');
insert into interpretation values(4, 2, 2, 'Lucius Hunt');
insert into interpretation values(5, 3, 3, 'Jake Sully'); 

insert into test values (
    1,
    true,
    2,
    3,
    4,
    5.6,
    7.8,
    PARSEDATETIME('09:46:30', 'HH:mm:ss'),
    PARSEDATETIME('24/05/2014', 'dd/MM/yyyy'),
    PARSEDATETIME('24/05/2014 09:46:30', 'dd/MM/yyyy HH:mm:ss'),
    FILE_READ('classpath:h2-logo-2.png'),
    'text',
    5,
    7
);

insert into test values (
    10,
    false,
    20,
    30,
    40,
    50.6,
    70.8,
    PARSEDATETIME('12:29:49', 'HH:mm:ss'),
    PARSEDATETIME('30/05/2014', 'dd/MM/yyyy'),
    PARSEDATETIME('30/05/2014 12:29:49', 'dd/MM/yyyy HH:mm:ss'),
    FILE_READ('classpath:logo-dev.jpg'),
    'another text',
    50,
    70
);

insert into test values (
    100,
    false,
    25,
    300,
    400,
    500.6,
    700.8,
    PARSEDATETIME('12:29:49', 'HH:mm:ss'),
    PARSEDATETIME('30/05/2014', 'dd/MM/yyyy'),
    PARSEDATETIME('30/05/2014', 'dd/MM/yyyy'),
    FILE_READ('classpath:logo-dev.jpg'),
    'another text again',
    500,
    700
);

insert into test values (
    1000,
    false,
    0,
    0,
    0,
    0,
    0,
    PARSEDATETIME('12:29:49', 'HH:mm:ss'),
    PARSEDATETIME('30/05/2014', 'dd/MM/yyyy'),
    PARSEDATETIME('30/05/2014', 'dd/MM/yyyy'),
    FILE_READ('classpath:logo-dev.jpg'),
    'another text again',
    500,
    700
);

insert into test2 values (
    1,
    true,
    2,
    3,
    4,
    5.6,
    7.8,
    PARSEDATETIME('09:46:30', 'HH:mm:ss'),
    PARSEDATETIME('24/05/2014', 'dd/MM/yyyy'),
    PARSEDATETIME('24/05/2014 09:46:30', 'dd/MM/yyyy HH:mm:ss'),
    FILE_READ('classpath:h2-logo-2.png'),
    'text',
    5,
    7,
    null
);

insert into test2 values (
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null
);

