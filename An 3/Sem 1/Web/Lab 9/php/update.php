<?php
/**
 * Created by PhpStorm.
 * User: mihaicostea
 * Date: 07/12/14
 * Time: 13:46
 */

$id = $_GET['id'];

$user = 'root';
$password = 'root';
$db = 'dealership';
$host = 'localhost';
$port = 3306;

$link = mysqli_connect($host,
    $user,
    $password,
    $db,
    $port);

$result = mysqli_query($link, "SELECT * FROM cars WHERE `id` = '$id'");
$row = mysqli_fetch_assoc($result);

echo $row['model'];

?>

<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>Edit Car</title>
</head>

<body>

<form method="post" action="updateCar.php?id=<?php echo $id; ?>">
    Model: <input type="text" name="model" id="model" value="<?php echo $row['model']; ?>" /><br>
    Power: <input type="text" name="power" id="power" value="<?php echo $row['engine_power']; ?>" /><br>
    Fuel: <input type="text" name="fuel" id="fuel" value="<?php echo $row['fuel']; ?>" /><br>
    Price: <input type="text" name="price" id="price" value="<?php echo $row['price']; ?>" /><br>
    Color: <input type="text" name="color" id="color" value="<?php echo $row['color']; ?>" /><br>
    Year: <input type="text" name="year" id="year" value="<?php echo $row['manufacturing_year']; ?>" /><br>
    <input type="submit" value="Update Car" />
</form>

</body>

</html>