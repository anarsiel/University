select RunId, SessionId, Letter, SubmitTime from
Sessions natural join Runs
where  Accepted = 0 and ContestId = :ContestId