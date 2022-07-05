select SessionId, count(SessionId) as Opened from (
	select distinct SessionId, Letter from Runs
) req
group by SessionId;