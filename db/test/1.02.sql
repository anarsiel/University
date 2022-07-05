select TeamName from Teams
where TeamId in (select distinct TeamId from Sessions
where ContestId = :ContestId)