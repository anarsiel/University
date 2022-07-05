delete 
from Students
where StudentId NOT IN (
	select distinct StudentId from Marks
);