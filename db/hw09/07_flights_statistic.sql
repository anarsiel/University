create or replace function flightStat(_UserId int, _Pass text, _FlightId int)
    returns table (
        _Reservable boolean, _Buyable boolean, _Free bigint, _Reserved bigint, _Taken bigint
) as $$
declare
    currentTime timestamp := now();
    _passHash varchar(60) := crypt(_Pass, 'md5');
begin
    if _passHash not in (select UserPassword from Users where UserId=_UserId) then
        return next;
    end if;

    return query select 
        aaa.a, bbb.b, ccc.c, ddd.d, eee.e 
    from 
    (
        select count(unnest) >= 1 as a from unnest(freeSeats(_FlightId))
    ) aaa,
    (
        select count(unnest) >= 1 as b
        from (
            select unnest
            from unnest(freeSeats(_FlightId))
            union select seatNo as unnest from ReservedSeats natural join users
                where FlightId=_FlightId and UserId=_UserId and ReservedTime + (interval '3 Days') > currentTime) reqqq
    ) bbb,
    (
        select count(unnest) as c from unnest(freeSeats(_FlightId))
    ) ccc,
    (
        select count(seatNo) as d from ReservedSeats 
            where FlightId=_FlightId and ReservedTime + (interval '3 Days') > currentTime
    ) ddd,
    (
        select count(seatNo) as e from TakenSeats where flightId=_FlightId
    ) eee;
end; $$
language plpgsql;

select flightStat(1, 'new password', 1);