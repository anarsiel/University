select AvgMark, GroupName 
from 
    Groups 
    left join (
        select GroupId, avg(cast(Mark as real)) as AvgMark 
        from
            Students 
            natural join Marks
        group by 
            GroupId) as name
    on Groups.GroupId = name.GroupId