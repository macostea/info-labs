<%@ Page Language="C#" Inherits="Lab11.UpdateCar" %>
<!DOCTYPE html>
<html>
<head runat="server">
	<title>UpdateCar</title>
</head>
<body>
	<form id="form1" runat="server">
		Model: <asp:TextBox id="Model" runat="server"></asp:TextBox> <br>
		Power: <asp:TextBox id="Power" runat="server"></asp:TextBox> <br>
		Fuel: <asp:TextBox id="Fuel" runat="server"></asp:TextBox> <br>
		Price: <asp:TextBox id="Price" runat="server"></asp:TextBox> <br>
		Color: <asp:TextBox id="Color" runat="server"></asp:TextBox> <br>
		Year: <asp:TextBox id="Year" runat="server"></asp:TextBox> <br>

	    <asp:Button id="UpdateCarButton" runat="server" Text="Modifica" OnClick="UpdateCarButton_Click" />
	</form>
</body>
</html>

