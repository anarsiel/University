select 
    StudentId, StudentName, GroupName
from
    Students S, Groups G
where
    S.GroupId = G.GroupId
    and StudentId not in (
        select
            StudentId
        from
            Marks M, Courses C
        where
            M.CourseId = C.CourseId
            and C.CourseName = :CourseName
    )
    and StudentId in (
        select
            StudentId
        from
            Students S, Plan P, Courses C
        where
            P.GroupId = S.GroupId
            and P.CourseId = C.CourseId
            and C.CourseName = :CourseName
    )