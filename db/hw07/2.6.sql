update Students set GroupId = (
	select GroupId from Groups
	where GroupName = :GroupName
)
where exists (select * from Groups where GroupName = :GroupName)
and StudentId in (
	select StudentId from Students
	where GroupId in (
	    select GroupId from Groups where GroupName = :FromGroupName
	)
);