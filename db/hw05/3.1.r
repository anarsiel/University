pi{StudentId, StudentName, GroupId}
(sigma{Mark = :Mark && CourseId = :CourseId}
	(Students njoin Marks))