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
     ) as name;