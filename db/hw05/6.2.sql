select distinct
    StudentId
from 
    (select * from Students
    except
        select distinct
            StudentId, StudentName, GroupId
        from
            Students natural join Marks natural join Plan natural join (
        select * from
            Lecturers
        where
            LecturerName = :LecturerName
        ) as name) as name2;