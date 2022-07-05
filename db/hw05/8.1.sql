select
    sum(cast(Mark as real)) as SumMark
from
    Marks
where
    StudentId = :StudentId