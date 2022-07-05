-- DROP FUNCTION freeseats(integer);

create or replace function freeSeats(_FlightId int) returns int[]
as $$
declare
    _result int[] := array[]::int[];
    _currentTime timestamp := now();
    _flightTime timestamp;
    _i int := 0;
    _seatNo int;
    _allSeats int := 0;
begin
    _flightTime := (select FlightTime from Flights where FlightId=_FlightId);
    if _flightTime < _currentTime then
        return _result;
    end if;

    _allSeats := (select SeatNo from Seats where PlaneId in (select (PlaneId) from Flights where FlightId=_FlightId));
    while _i < _allSeats loop
        _result := array_append(_result, _i + 1);
        _i := _i + 1;
    end loop;

    for _seatNo in select (SeatNo) from TakenSeats where FlightId=_FlightId loop
        _result := array_remove(_result, _seatNo);
    end loop;

    for _seatNo in select (SeatNo) from ReservedSeats where FlightId=_FlightId loop
        _result := array_remove(_result, _seatNo);
    end loop;

    return _result;
end; $$
language plpgsql;

select freeSeats(1)