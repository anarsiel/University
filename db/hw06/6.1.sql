select
    GroupId, CourseId
from
    Groups G, Courses C
where
    not exists (
        select
            G2.GroupId, C2.CourseId
        from
            Students S, Groups G2, Courses C2
        where
            not exists (
                select
                    Mark
                from
                    Marks M
                where
                    M.StudentId = S.StudentId
                    and M.CourseId = C2.CourseId
            )
            -- and S.StudentId = S2.StudentId
            and G.GroupId = G2.GroupId
            and S.GroupId = G2.GroupId
            and C.CourseId = C2.CourseId
    )