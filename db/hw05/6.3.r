pi{StudentId, CourseId}(Marks)
div
pi{CourseId}(
    sigma{LecturerName = :LecturerName}
    (Plan njoin Lecturers)
)