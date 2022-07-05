update Marks set Mark = (
	select Mark from NewMarks
		where Marks.StudentId = NewMarks.StudentId
		and Marks.CourseId = NewMarks.CourseId
) where exists (select Mark from NewMarks
		where Marks.StudentId = NewMarks.StudentId
		and Marks.CourseId = NewMarks.CourseId
)