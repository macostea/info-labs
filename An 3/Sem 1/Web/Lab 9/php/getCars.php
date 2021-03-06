<?php
/**
 * Created by PhpStorm.
 * User: mihaicostea
 * Date: 07/12/14
 * Time: 12:10
 */


header('Cache-Control: no-cache, must-revalidate');
header('Expires: Mon, 01 Jan 1996, 00:00:00 GMT');

header('Content-Type: application/json; charset=UTF-8');

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

$fuel_type = $_GET['fuelType'];

$result = mysqli_query($link, "SELECT * FROM cars");

if ($fuel_type != null && $fuel_type != "All") {
    $result = mysqli_query($link, "SELECT * FROM cars WHERE fuel = '$fuel_type'");
}

$jsonData = array();

while ($row = mysqli_fetch_array($result)) {
    $rowData = array();
    $rowData['id'] = $row['id'];
    $rowData['model'] = $row['model'];
    $rowData['engine_power'] = $row['engine_power'];
    $rowData['fuel'] = $row['fuel'];
    $rowData['price'] = $row['price'];
    $rowData['color'] = $row['color'];
    $rowData['year'] = $row['manufacturing_year'];

    $jsonData[] = $rowData;
}

echo json_encode($jsonData);

mysqli_close($link);
