<?php
/**
 * Created by PhpStorm.
 * User: mihaicostea
 * Date: 05/02/15
 * Time: 12:12
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

$name = $_POST['name'];
$type = $_POST['type'];
$price = $_POST['price'];
$description = $_POST['description'];

if ($result = mysqli_query($link ,"INSERT INTO `exam`.`Product` (`id`, `name`, `type`, `price`, `description`)
 VALUES (NULL, '$name', '$type', '$price', '$description');") == false) {
    die(mysqli_error($link));
}

$jsonData = array('code' => 200, 'message' => 'success');

echo json_encode($jsonData);

mysqli_close($link);
