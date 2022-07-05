select 
    StudentId, StudentName, GroupId
from 
    Marks
    natural join (
        select
            CourseId
        from
            Plan natural join Lecturers
        where
            LecturerName = :LecturerName
    ) as name
    natural join Students
where 
    Mark = :Mark;