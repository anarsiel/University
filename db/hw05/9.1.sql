select
    avg(cast(Mark as real)) as AvgMark
from
    Marks
where
    StudentId = :StudentId