create procedure removeAttachment(@url varchar(200))

as
begin

delete from Attachment where Attachment.url like @url

end