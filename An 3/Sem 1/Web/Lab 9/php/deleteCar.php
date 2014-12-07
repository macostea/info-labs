<?php
/**
 * Created by PhpStorm.
 * User: mihaicostea
 * Date: 07/12/14
 * Time: 13:01
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

if ($result = mysqli_query($link, "DELETE FROM `dealership`.`cars` WHERE `cars`.`id` = '$id'") == false) {
    die(mysqli_error($link));
}

$jsonData = array('code' => 200, 'message' => 'success');

echo json_encode($jsonData);

mysqli_close($link);