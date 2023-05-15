#!/usr/bin/php-cgi
<?php 
	session_start();
	$list = json_decode($_COOKIE["list"],true);
	echo implode(",",$list);
?>