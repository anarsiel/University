select distinct
    S.StudentId
from
    Students S, Marks M, Plan P, Lecturers L
where
    S.StudentId = M.StudentId
    and P.CourseId = M.CourseId
    and S.GroupId = P.GroupId
    and P.LecturerId = L.LecturerId
    and LecturerName = :LecturerName