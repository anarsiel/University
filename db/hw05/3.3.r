pi{StudentId, StudentName, GroupId}
(sigma{Mark = :Mark && LecturerId = :LecturerId}
    (Students njoin Plan njoin Marks))