update Students set Marks =	
	(select count(*) from Marks where StudentId = :StudentId)
where StudentId = :StudentId;