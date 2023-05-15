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
	<title>Home</title>
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
<p>Username is 'username'</p>
<p>Password is 'password'</p>
</body>
</html>