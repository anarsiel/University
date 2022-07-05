select distinct
    StudentId
from
    Students natural join Marks natural join Plan natural join (
        select * from
            Lecturers
        where
            LecturerName = :LecturerName
        ) as name;