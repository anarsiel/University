select distinct
    StudentName, CourseName
from
    Students S, Courses C, Plan P
where
    S.GroupId = P.GroupId
    and P.CourseId = C.CourseId
    and not exists (
        select
            StudentId, CourseId
        from
            Marks M
        where
            M.StudentId = S.StudentId
            and M.CourseId = C.CourseId
    )