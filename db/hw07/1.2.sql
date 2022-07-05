delete 
from Students
where GroupId IN (
	select GroupId from Groups where GroupName = :GroupName
);
