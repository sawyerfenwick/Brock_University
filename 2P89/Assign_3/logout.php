#!/usr/bin/php-cgi
<!--Sawyer_Fenwick-->
<!--6005011-->
<!--sf15zx-->
<!--Assign3-->
<?php
	session_start();
	if($_SESSION['status']==="Offline"){
		$lastURL = $_SERVER["HTTP_REFERER"];
		setcookie("user", json_encode($_SESSION["status"]), time()+(86400*30),"/");
		header("Location:$lastURL");
	}else{
		$_SESSION["status"]="Offline";
		setcookie("user", json_encode($_SESSION["status"]), time()+(86400*30),"/");
		header("Location: home.php");
	}
?>