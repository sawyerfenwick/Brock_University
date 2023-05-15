#!/usr/bin/php-cgi
<?php 
	session_start();
	$item = $_POST["id"];
	$list = json_decode($_COOKIE["list"],true);
	if(!in_array($item, $list)){
		array_push($list,$item);
	}
	setcookie("list", json_encode($list), time() +(86400*30), "/");
?>
