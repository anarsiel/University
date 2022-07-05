update Students set Marks =	
	Marks + 
	(select count(*) from NewMarks where StudentId = Students.StudentId)