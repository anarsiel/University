select TeamId, count(SessionId) as Opened from (
	select distinct TeamId, Letter from Runs
) req
group by TeamId;