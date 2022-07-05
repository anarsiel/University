update Students set Marks =	
	(select count(*) from Marks where StudentId = Students.StudentId)