select
    SumMark, StudentName
from
   (select Students.StudentId, sum(Mark) as SumMark
    from
        Students
        left join Marks
    on Students.StudentId = Marks.StudentId
    group by
        Students.StudentId) as name
    natural join Students;