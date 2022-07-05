select
    StudentId, StudentName, S.GroupId
from
    Students S, Groups G
where
    S.GroupId = G.GroupId and GroupName = :GroupName;