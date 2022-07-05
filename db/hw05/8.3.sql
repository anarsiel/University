select SumMark, GroupName 
from 
    Groups 
    left join (
        select GroupId, Sum(Mark) as SumMark 
        from
            Students 
            natural join Marks
        group by 
            GroupId) as name
    on Groups.GroupId = name.GroupId