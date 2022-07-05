merge into Marks M
using NewMarks NM
on (exists (
	select Mark from NewMarks
	where Marks.StudentId = NewMarks.StudentId
	and Marks.CourseId = NewMarks.CourseId))
when matched then update set Marks.Mark = NewMarks.Mark where NewMarks.Mark > Marks.Mark
when not mathed then insert (StudentId, CourseId, Mark)
	values (NewMarks.StudentId, NewMarks.CourseId, NewMarks.Mark);