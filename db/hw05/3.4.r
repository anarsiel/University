pi{StudentId, StudentName, GroupId}
(sigma{Mark = :Mark && LecturerName = :LecturerName}
	(Students njoin Plan njoin Marks njoin Lecturers))