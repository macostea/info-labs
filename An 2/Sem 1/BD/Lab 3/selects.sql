select * from Articles where Articles.author = 2

select Authors.name as 'Author name', count(*) as 'Number of articles' from Articles
join Authors
on Articles.author = Authors.id
group by Authors.name, Articles.author

select * from Articles where Articles.date > '2000-1-1'

select Articles.date, count(*) as 'Number of articles' from Articles group by Articles.date

select Readers.username as 'Reader', count(*) as 'Number of comments' from Comments
join Readers
on Comments.readerId = Readers.id
group by Readers.username

select * from Comments where Comments.readerId = 0

select distinct Articles.date, Articles.text from Articles /* Text of articles from dates when only one article was written */
