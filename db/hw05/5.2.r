pi{StudentName, CourseName}
((pi{StudentName, CourseName, StudentId}
        (Students njoin Plan njoin Courses))
diff
(pi{StudentName, CourseName, StudentId}
        (Students njoin Marks njoin Courses)))