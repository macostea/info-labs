/*
 *	Where
 */
select * from Articles where Articles.author = 2
select * from Articles where Articles.date > '2000-1-1'
select * from Articles where attachment = 2
select * from Authors where name like '%Joe%'
select * from Comments where Comments.readerId = 0

/*
 *	Group By
 */
select Authors.name as 'Author name', count(*) as 'Number of articles' from Articles
join Authors
on Articles.author = Authors.id
group by Authors.name, Articles.author
select Readers.username as 'Reader', count(*) as 'Number of comments' from Comments
join Readers
on Comments.readerId = Readers.id
group by Readers.username
select Articles.date, count(*) as 'Number of articles' from Articles group by Articles.date

/*
 *	Distinct
 */
select distinct Articles.date, Articles.text from Articles /* Text of articles from dates when only one article was written */
select distinct Articles.author, Articles.text from Articles /* Text of article written by authors who have only written one article */

/*
 *	Having
 */
 /* Name of authors who have written more than 2 articles */
 select Authors.name, COUNT(Articles.id) as NumberOfArticles
 from (Articles inner join Authors
 on Articles.author = Authors.id)
 group by name
 having count(Articles.id) > 2
/* Days when 2 or more articles where written */
select Articles.date, COUNT(Articles.id) as NumberOfArticles
from Articles
group by date
having count(Articles.id) >= 2

/*
 *	Joining more than two tables
 */

 select Authors.name, Brands.brandName, Articles.subject from Brands
 join Authors on Brands.authorId = Authors.id
 join Articles on Brands.articleId = Articles.id

 select Readers.username, Comments.text as 'Comment', Articles.subject from Comments
 join Readers on Comments.readerId = Readers.id
 join Articles on Comments.articleId = Articles.id

 select Attachment.url, Articles.subject, Authors.name from Articles
 join Attachment on Articles.attachment = Attachment.id
 join Authors on Articles.author = Authors.id



