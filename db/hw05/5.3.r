pi{StudentName, CourseName}
((pi{StudentName, CourseName, StudentId}
        (Students njoin Plan njoin Courses))
diff
(pi{StudentName, CourseName, StudentId}
        (Students njoin
         (sigma{Mark = 4 || Mark = 5} (Marks)) njoin
         Courses)))