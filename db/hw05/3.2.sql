select 
    StudentId, StudentName, GroupId
from 
    Students natural join Courses natural join Marks
where 
    Mark = :Mark and CourseName = :CourseName;