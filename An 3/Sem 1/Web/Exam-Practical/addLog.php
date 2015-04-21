<?php
/**
 * Created by PhpStorm.
 * User: mihaicostea
 * Date: 05/02/15
 * Time: 12:35
 */

header('Cache-Control: no-cache, must-revalidate');
header('Expires: Mon, 01 Jan 1996, 00:00:00 GMT');

header('Content-Type: application/json; charset=UTF-8');

$user = 'root';
$password = 'root';
$db = 'exam';
$host = 'localhost';
$port = 3306;

$link = mysqli_connect($host,
    $user,
    $password,
    $db,
    $port);

$data = json_decode(file_get_contents('php://input'), true);

$date = date("Y-m-d H:i:s");
$ip = $_SERVER['REMOTE_ADDR'];
$event_type = $data['event_type'];
$event = $data['event'];
$idProduct = $data['id_product'];

if ($result = mysqli_query($link ,"INSERT INTO `exam`.`log` (`id`, `date`, `ipAddress`, `eventType`, `event`, `idProduct`)
 VALUES (NULL, '$date', '$ip', '$event_type', '$event', '$idProduct');") == false) {
    die(mysqli_error($link));
}

$jsonData = array('code' => 200, 'message' => 'success');

echo json_encode($jsonData);

mysqli_close($link);
