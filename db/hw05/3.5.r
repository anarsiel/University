pi{StudentId, StudentName, GroupId}
(sigma{Mark = :Mark}
	(
		Marks 
		njoin (pi{CourseId}(sigma{LecturerId = :LecturerId}(Plan)))
		njoin Students
	)
)