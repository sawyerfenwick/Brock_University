#!/usr/bin/php-cgi
<!--Sawyer_Fenwick-->
<!--6005011-->
<!--sf15zx-->
<!--Assign3-->
<?php session_start(); /* Starts the session */

if(!isset($_SESSION['UserData']['Username'])){
	header("location:login.php");
	exit;
}
?>
