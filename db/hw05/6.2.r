pi{StudentId}
(Students
diff
(pi{StudentId, StudentName, GroupId}(
    Students njoin 
    Marks njoin 
    Plan njoin 
    (sigma{LecturerName = :LecturerName}(Lecturers))
)))