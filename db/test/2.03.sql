select distinct TeamId from
   Sessions cross join Runs
   where Runs.SessionId = Sessions.SessionId and ContestId = :ContestId and Accepted = 0
