INSERT INTO Flights(FlightId, FlightTime, PlaneId)
    VALUES (1, '2021-11-22 19:10:25-07', 1),
           (2, '2021-11-22 20:10:25-07', 2),
           (3, '2021-11-22 05:50:25-07', 1);

INSERT INTO Seats(PlaneId, SeatNo)
    VALUES (1, 20),
           (2, 30);

INSERT INTO TakenSeats(FlightId, SeatNo)
    VALUES  (1, 3),
            (2, 4),
            (1, 6);

INSERT INTO ReservedSeats(FlightId, SeatNo, UserId, ReservedTime)
    VALUES  (1, 2, 1, '2021-11-22 05:04:02-07'),
            (2, 7, 1, '2021-11-22 06:04:02-07'),
            (1, 9, 2, '2021-11-22 07:04:02-07');


INSERT INTO Users(UserId, UserPassword)
    VALUES  (1, crypt('new password', 'md5')),
            (2, crypt('WhatDoThink', 'md5')),
            (3, crypt('657KAT', 'md5'));