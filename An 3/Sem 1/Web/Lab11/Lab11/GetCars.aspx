<%@ Page Language="C#" Inherits="Lab11.GetCars"%>
<!DOCTYPE html>
<html>
<head runat="server">
	<title>GetCars</title>
</head>
<body>
	<form id="form1" runat="server">
		<asp:Table id="CarsTable" runat="server"
			CellPadding="10"
			GridLines="Both"
			HorizontalAlign="Center">
			<asp:TableRow>
				<asp:TableHeaderCell>
					ID
				</asp:TableHeaderCell>
				<asp:TableHeaderCell>
					Model
				</asp:TableHeaderCell>
				<asp:TableHeaderCell>
					Power
				</asp:TableHeaderCell>
				<asp:TableHeaderCell>
					Fuel
				</asp:TableHeaderCell>
				<asp:TableHeaderCell>
					Price
				</asp:TableHeaderCell>
				<asp:TableHeaderCell>
					Color
				</asp:TableHeaderCell>
				<asp:TableHeaderCell>
					Year
				</asp:TableHeaderCell>
				<asp:TableHeaderCell>
					Delete?
				</asp:TableHeaderCell>
				<asp:TableHeaderCell>
					Update?
				</asp:TableHeaderCell>
			</asp:TableRow>
		</asp:Table>
	</form>

	<form id="Form2" runat="server">
		Model: <asp:TextBox id="Model" runat="server"></asp:TextBox> <br>
		Power: <asp:TextBox id="Power" runat="server"></asp:TextBox> <br>
		Fuel: <asp:TextBox id="Fuel" runat="server"></asp:TextBox> <br>
		Price: <asp:TextBox id="Price" runat="server"></asp:TextBox> <br>
		Color: <asp:TextBox id="Color" runat="server"></asp:TextBox> <br>
		Year: <asp:TextBox id="Year" runat="server"></asp:TextBox> <br>

	    <asp:Button id="AddCarButton" runat="server" Text="Adauga" OnClick="AddCarButton_Click" />
	</form>
</body>
</html>

