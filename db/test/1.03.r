pi{RunId, SessionId, Letter, SubmitTime} (
	sigma{Accepted = 0 && ContestId = :ContestId} (
		Sessions njoin Runs
	)
)