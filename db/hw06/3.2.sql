select
    StudentName, CourseName
from
    Students S, Courses C, (
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
                Marks
    ) as SC
where
    SC.StudentId = S.StudentId and SC.CourseId = C.CourseId;