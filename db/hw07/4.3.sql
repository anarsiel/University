update Marks set Mark = (
	select Mark from NewMarks
		where Marks.StudentId = NewMarks.StudentId
		and Marks.CourseId = NewMarks.CourseId
		and NewMarks.Mark > Marks.Mark
) where exists (select Mark from NewMarks
		where Marks.StudentId = NewMarks.StudentId
		and Marks.CourseId = NewMarks.CourseId
		and NewMarks.Mark > Marks.Mark
);