select distinct
    S.StudentId, S.StudentName, S.GroupId
from
    Students S, Marks M, Courses C
where
    S.StudentId = M.StudentId
    and C.CourseId = M.CourseId
    and Mark = :Mark
    and CourseName = :CourseName;