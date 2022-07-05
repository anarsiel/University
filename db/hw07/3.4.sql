update Students set Marks =	(
		select count(*) from (
			select distinct CourseId from Marks where Marks.StudentId = Students.StudentId
		) BLAGOI where StudentId = Students.StudentId
	)