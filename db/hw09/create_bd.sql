create table Flights(
    FlightId int primary key,
    FlightTime timestamp not null,
    PlaneId int not null
);

create table Seats(
    PlaneId int primary key, 
    SeatNo int not null
);

-- занятые места (номер рейса, номер места)
-- нужно для статистики
create table TakenSeats(
    FlightId int not null,
    SeatNo int not null,
    primary key (FlightId, SeatNo)
);

-- зарезервированные места. То же самое что занятые, но с указанием
-- времени резервирвания и Id того, кто зарегестировался
create table ReservedSeats(
    FlightId int not null,
    SeatNo int not null,
    UserId int not null,
    ReservedTime timestamp not null,
    primary key(FlightId, SeatNo)
);

-- модуль предоставляющий криптографические функции для psql
create extension pgcrypto;

-- Пользователи (идентификатор пользователя, md5 hash его пароля)
create table Users(
    UserId int primary key,
    UserPassword varchar(80) not null
);