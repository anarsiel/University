create view StudentMarks as
select StudentId,
 (select count(*) from Marks where StudentId = Students.StudentId) Marks from Students