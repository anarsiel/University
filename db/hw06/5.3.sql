select
    StudentId
from
    Students S
where
    StudentId not in (
        select
            StudentId
        from
            Students S, (
                select
                    CourseId
                from
                    Plan P, Lecturers L
                where
                    P.LecturerId = L.LecturerId
                    and LecturerName = :LecturerName
            ) as CoursesNeeded
        where
            not exists (
                select
                    Mark
                from
                    Marks M
                where
                    M.StudentId = S.StudentId
                    and M.CourseId = CoursesNeeded.CourseId
            )
    )