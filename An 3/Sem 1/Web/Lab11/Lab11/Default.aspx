<%@ Page Language="C#" Inherits="Lab11.Default" %>
<!DOCTYPE html>
<html>
<head runat="server">
	<title>Default</title>
</head>
<body>
	<form id="form1" runat="server">
		username <asp:TextBox id="Username" runat="server"></asp:TextBox> <br>
		password <asp:TextBox id="Password" runat="server" TextMode="Password"></asp:TextBox> <br>
		<asp:Button id="Login" runat="server" text="Login" OnClick="LoginButton_Click"></asp:Button> <br>
		<asp:Label id="LoginError" runat="server"></asp:Label>
	</form>
</body>
</html>
