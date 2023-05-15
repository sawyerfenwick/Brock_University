#!/usr/bin/php-cgi
<!--Sawyer_Fenwick-->
<!--6005011-->
<!--sf15zx-->
<!--Assign3-->
<?php
	session_start();
	if(!isset($_SESSION["status"])){
		$_SESSION["status"]="Offline";
		setcookie("user", json_encode($_SESSION["status"]), time()+(86400*30),"/");
	}
?>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Login</title>
	<link rel="stylesheet" type="text/css" href="style.css"/>
</head>
<body>
<h1 style="text-align:center">Carbonated Drinks R Us</h1>
<?php 
	if($_SESSION["status"]=="Offline"){
		echo "<div align='right'><span style='text-align:right'>Status: </span><span class='offline'>".$_SESSION["status"]."</span></div>";
	}else{
		echo "<div align='right'><span style='text-align:right'>Status: </span><span class='online'>".$_SESSION["status"]."</span></div>";
	}
?>
<div class="navbar">
	<a class="active">Home</a>
	<a href="products.php">Products</a>
	<a href="wishlist.php">Wishlist</a>
	<div class="navbar-right">
		<a href="login.php">Login</a>
		<a href="logout.php">Logout</a>
	</div>
</div>
<div align="center">
<form action="" method="post">
	<table width="200" border="0">
	<tr>
		<td> Username </td>
		<td> <input type="text" name="user"></td>
	</tr>
	<tr>
		<td> Password </td>
		<td><input type="password" name="pass"></td>
	</tr>
	<tr>
		<td><input type="submit" name="login" value="LOGIN"></td>
		<td></td>
	</tr>
	</table>
</form></div>
</body>
</html>
<?php
	if(($_SESSION['status']=="Online")){
		$lastURL = $_SERVER["HTTP_REFERER"];
		header("Location:$lastURL");
	}
	if(isset($_POST['login'])){
		$user = $_POST['user'];
		$pass = $_POST['pass'];
		
		if($user == "username" && $pass = "password"){
			$_SESSION["status"] = "Online";
			setcookie("user",json_encode($_SESSION["status"]), time()+(86400*30),"/");
			if(!isset($_SESSION["list5"])){
				$_SESSION["list5"] = array();
			}
			if(!isset($_COOKIE["list"])){
				setcookie("list", json_encode($_SESSION["list5"]), time()+(86400*30),"/");
			}
			echo '<script type="text/javascript">window.open("home.php","_self");</script>';
		}
		else{
			echo "<p class='centertext'>Invalid Username or Password</p>"; 
		}
	}
?>