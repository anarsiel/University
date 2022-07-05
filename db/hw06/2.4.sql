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
            Marks M
        where
            M.CourseId = :CourseId
    )
    and StudentId in (
        select
            StudentId
        from
            Students S, Plan P
        where
            P.GroupId = S.GroupId
            and P.CourseId = :CourseId
    )