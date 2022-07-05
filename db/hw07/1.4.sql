delete 
from Students
where StudentId IN (
	select StudentId
	from Marks
	group BY StudentId
	having count(StudentId) >= 3
);