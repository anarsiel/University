select
    StudentId
from
    Students S
where
    not exists (
        select
            StudentId
        from
            Students S2, (
                select
                    CourseId, GroupId
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
            and S.StudentId = S2.StudentId
            and S.GroupId = CoursesNeeded.GroupId
    )