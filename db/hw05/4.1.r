Students diff
 (pi{StudentId, StudentName, GroupId}
 	(sigma{CourseName = :CourseName}
 		(
 			Students njoin
 			Marks njoin
 			Courses
 		)
 	)
 )