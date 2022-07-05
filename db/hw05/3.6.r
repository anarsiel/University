pi{StudentId, StudentName, GroupId}
(sigma{Mark = :Mark}
	(
		Marks 
		njoin (pi{CourseId}(sigma{LecturerName = :LecturerName}(Plan njoin Lecturers)))
		njoin Students
	)
)