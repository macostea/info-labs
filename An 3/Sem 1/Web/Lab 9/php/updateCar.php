<?php
/**
 * Created by PhpStorm.
 * User: mihaicostea
 * Date: 07/12/14
 * Time: 12:29
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

$id = $_GET['id'];

$model = $_POST['model'];
$power = $_POST['power'];
$fuel = $_POST['fuel'];
$price = $_POST['price'];
$color = $_POST['color'];
$year = $_POST['year'];

if ($result = mysqli_query($link, "UPDATE `dealership`.`cars` SET `model` = '$model', `engine_power` = '$power',
`fuel` = '$fuel', `price` = '$price', `color` = '$color', `manufacturing_year` = '$year' WHERE `id` = '$id'") == false) {
    die(mysqli_error($link));
}

$jsonData = array('code' => 200, 'message' => 'success');

echo json_encode($jsonData);

mysqli_close($link);
