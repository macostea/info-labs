<?php
/**
 * Created by PhpStorm.
 * User: mihaicostea
 * Date: 05/02/15
 * Time: 10:52
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

//echo file_get_contents('php://input');

$data = json_decode(file_get_contents('php://input'), true);

$fields = json_decode($data['fields'], true);
$term = $data['term'];

$result = mysqli_query($link, "SELECT * FROM product");


if (count($fields) != 0 && $term != null) {
    $fields_query_string = "";
    for ($i = 0; $i < count($fields); $i++) {
        $field = $fields[$i];
        $fields_query_string .= "$field LIKE '%$term%'";
        if ($i != count($fields) - 1) {
            $fields_query_string .= " OR ";
        }
    }

    $result = mysqli_query($link, "SELECT * FROM product WHERE $fields_query_string") or die(mysqli_error($link));
}

$jsonData = array();

$tableData = array();


while ($row = mysqli_fetch_array($result)) {
    $rowData = array();
    $rowData['id'] = $row['id'];
    $rowData['name'] = $row['name'];
    $rowData['type'] = $row['type'];
    $rowData['price'] = $row['price'];
    $rowData['description'] = $row['description'];

    $tableData[] = $rowData;
}

$jsonData['results'] = $tableData;
$jsonData['ip'] = $_SERVER['REMOTE_ADDR'];

echo json_encode($jsonData);

mysqli_close($link);