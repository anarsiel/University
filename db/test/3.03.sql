delete from Runs
where RunId in (
   select RunId from 
   Runs natural join Sessions natural join Contests
   where ContestName = :ContestName
)
