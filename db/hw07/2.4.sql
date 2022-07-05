update Students set GroupId = (
	select GroupId from Groups
	where GroupName = :GroupName
)
where StudentId in (
	select StudentId from Students
	where GroupId in (
	    select GroupId from Groups where GroupName = :FromGroupName
	)
);