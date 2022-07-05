select
    AvgMark, StudentName
from
   (select Students.StudentId, avg(cast(Mark as real)) as AvgMark
    from
        Students
        left join Marks
    on Students.StudentId = Marks.StudentId
    group by
        Students.StudentId) as name
    natural join Students;