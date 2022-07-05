insert into Marks (StudentId, CourseId, Mark)
select * from NewMarks
	where CourseId not in (select CourseId from Marks
						 where StudentId = NewMarks.StudentId)