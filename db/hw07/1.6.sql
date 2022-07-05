select distinct StudentId
from Students natural join Plan
where StudentId not in (
	select StudentId
from Students natural join Plan natural join Marks
);