-- DROP FUNCTION flightsstatistics(integer,text);

create or replace function flightsStatistics(_UserId int, _Pass text)
    returns table (
        _FlightId int, _Reservable boolean, _Buyable boolean, _Free bigint, _Reserved bigint, _Taken bigint
) as $$
declare
    _currentTime timestamp := now();
begin
    return query select FlightId as _FlightId,
    (
        select count(unnest) >= 1 from unnest(freeSeats(Flights.FlightId))
    ) as _Reservable, 
    (
        select count(unnest) >= 1
        from (select unnest
            from unnest(freeSeats(Flights.FlightId))
            union 
            select seatNo as unnest 
                from ReservedSeats natural join Users
                where FlightId = Flights.FlightId and ReservedTime + (interval '3 Days') > _currentTime and UserId=_UserId) reqqq
    ) as _Buyable ,
    (
        select count(unnest) from unnest(freeSeats(Flights.FlightId))
    ) as _Free,
    (
        select count(seatNo) from TakenSeats where flightId=Flights.FlightId
    ) as _Taken,
    (
        select count(seatNo) from ReservedSeats where
            FlightId=Flights.FlightId and ReservedTime + (interval '3 Days') > _currentTime
    ) as _Reserved
        from Flights;
end; $$
language plpgsql;

select flightsStatistics(1, 'new password');