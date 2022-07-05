create view AllMarks as
select StudentId,
 ((select count(*) from Marks where StudentId = Students.StudentId)
	+ (select count(*) from NewMarks where StudentId = Students.StudentId)) Marks from Students