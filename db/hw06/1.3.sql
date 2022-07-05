select distinct
    S.StudentId, S.StudentName, S.GroupId
from
    Students S, Marks M
where
    S.StudentId = M.StudentId
    and Mark = :Mark
    and CourseId = :CourseId;