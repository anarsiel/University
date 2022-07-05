pi{StudentId, StudentName, GroupId}
(sigma{Mark = :Mark && CourseName = :CourseName}
	(Students njoin Courses njoin Marks))