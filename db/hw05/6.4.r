pi{StudentId, CourseId, GroupId}
    (Marks ljoin Students)
gd
(pi{CourseId, GroupId}(
    sigma{LecturerName = :LecturerName}
    (Plan ljoin Lecturers)
))