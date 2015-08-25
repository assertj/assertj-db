delete from test2;
delete from test;
delete from interpretation;
delete from actor;
delete from movie;

insert into movie values(1, 'Alien', 1979, '30B443AE-C0C9-4790-9BEC-CE1380808435');
insert into movie values(2, 'The Village', 2004, '16319617-AE95-4087-9264-D3D21BF611B6');
insert into movie values(3, 'Avatar', 2009, 'D735221B-5DE5-4112-AA1E-49090CB75ADA');

insert into actor values(1, 'Weaver', 'Sigourney', PARSEDATETIME('08/10/1949', 'dd/MM/yyyy'), '648DFAC8-14AC-47F7-95CF-3475525A3BE3');
insert into actor values(2, 'Phoenix', 'Joaquim', PARSEDATETIME('28/10/1974', 'dd/MM/yyyy'), '626A68EC-DD0E-4346-B701-1DBB9D5F5800');
insert into actor values(3, 'Worthington', 'Sam', PARSEDATETIME('02/08/1976', 'dd/MM/yyyy'), '0709D19B-3227-4105-9DD0-4810FF1494BB');

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
    7,
    'BD9C7590-DCA0-4477-A95D-13A7D1F3CC19'
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
    70,
    'B2905C03-E6E6-4ADE-9EB7-2B3A8989B100'
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
    700,
    '3C8B01F4-1472-4FAC-A276-978C9162D919'
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
    700,
    'FDA220A0-D783-4CDD-ADB0-6CA3393A6236'
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
    null,
    '22B2D036-F398-4C99-87F4-8A5BA2EB0858'
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
    null,
    null
);

