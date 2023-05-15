#!/usr/bin/php-cgi
<?php 
	session_start();
	$item = $_POST["id"];
	$list = json_decode($_COOKIE["list"],true);
	for($i = 0; $i < count($list); $i++){
		if($list[$i] == $item){
			unset($list[$i]);
		}
	}
	setcookie("list", json_encode($list),time()+(86400*30), "/");
	echo implode(",",$list);
?>