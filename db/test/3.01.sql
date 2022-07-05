delete from Runs
where RunId in (
   select RunId from 
   Runs natural join Sessions
   where TeamId = :TeamId
)
