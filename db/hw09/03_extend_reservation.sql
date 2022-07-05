
create or replace function extendReservation(_UserId int, _Pass varchar(60), _FlightId int, _SeatNo int) 
    returns boolean
as $$
declare
    _currentTime timestamp := now();
    _passHash varchar(60) := crypt(_Pass, 'md5');
    _reversedTime timestamp;
    _flightTime timestamp;
    _allSeats int := 0;
begin
    if _passHash not in (select UserPassword from Users where UserId=_UserId) then
        return false;
    end if;

    _flightTime := (select FlightTime from Flights where FlightId=_FlightId);
    if _flightTime < _currentTime then
        return false;
    end if; 

    _allSeats := (select SeatNo from Seats where PlaneId in (select (PlaneId) from Flights where FlightId=_FlightId));
    if _SeatNo > _allSeats then  
        return false;
    end if;


    if _FlightId in (select FlightId from TakenSeats where FlightId=_FlightId and SeatNo=_SeatNo) then 
        return false;
    end if;

    if _FlightId in (select FlightId from ReservedSeats where FlightId=_FlightId and UserId=_UserId and SeatNo=_SeatNo) then 
        _reversedTime := (select ReservedTime from ReservedSeats where FlightId=_FlightId and SeatNo=_SeatNo);
        if _reversedTime + (interval '3 Days') < _currentTime then
            return false;
        else
            delete from ReservedSeats where FlightId=_FlightId and SeatNo=_SeatNo;
            insert into ReservedSeats(FlightId, SeatNo, UserId, ReservedTime)
                values (_FlightId, _SeatNo, _UserId, _currentTime);
            return true;
        end if;
    else
        return false;
    end if;
end; $$
language plpgsql;

select extendReservation(1, 'new password', 1, 1);