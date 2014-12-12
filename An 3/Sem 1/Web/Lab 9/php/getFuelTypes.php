<?php
/**
 * Created by PhpStorm.
 * User: mihaicostea
 * Date: 08/12/14
 * Time: 12:20
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

$result = mysqli_query($link, "SELECT DISTINCT fuel FROM cars");

$jsonData = array();

while ($row = mysqli_fetch_array($result)) {
    $rowData = array();
    $rowData['fuel'] = $row['fuel'];

    $jsonData[] = $rowData;
}

echo json_encode($jsonData);

mysqli_close($link);