select
    StudentName, CourseName
from 
    (select
        StudentName, CourseName, StudentId
    from
        Students natural join Plan natural join Courses
    except
        select
            StudentName, CourseName, StudentId
        from
            Students natural join Marks natural join Courses
        where
            Mark = 4 or Mark = 5
     ) as name;