select 
    StudentId, CourseId
from
    Students S, Plan P
where
    S.GroupId = P.GroupId
union
    select
        StudentId, CourseId
    from
        Marks;