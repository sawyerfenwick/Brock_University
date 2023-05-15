<?php 
	session_start();
	$client_list = $_POST['item'];
	echo implode(",",$client_list);
	//$server_list = json_decode($_COOKIE["list"],true);
	//$merge = array_merge($client_list,$server_list);
	//$unique = array_unique($merge);
	//implode(",",$unique);
	//setcookie("list", json_encode($client_list), time() +(86400*30), "/");
?>