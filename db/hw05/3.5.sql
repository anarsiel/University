select 
    StudentId, StudentName, GroupId
from 
    Marks
    natural join (
        select
            CourseId
        from
            Plan
        where
            LecturerId = :LecturerId
    ) as name
    natural join Students
where 
    Mark = :Mark;