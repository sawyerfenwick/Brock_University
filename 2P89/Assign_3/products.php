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
	<title>Products</title>
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
	<a href="home.php">Home</a>
	<a class="active">Products</a>
	<a href="wishlist.php">Wishlist</a>
	<div class="navbar-right">
		<a href="login.php">Login</a>
		<a href="logout.php">Logout</a>
	</div>
</div>
<script>
var status = "<?php echo $_SESSION['status']?>";
if(sessionStorage.getItem("array")==null){
	var array = [];
}else{
	array = JSON.parse(sessionStorage.getItem("array"));
}
function add(id){
	if(status == "Offline"){
		if(!array.includes(id)){
			array.push(id);
		}
		sessionStorage.setItem("array",JSON.stringify(array));	
	}
	if(status == "Online"){
		var item = "id="+id;
		var req = new XMLHttpRequest();
		req.open("POST", "sessionList.php", true);
		req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		req.send(item);
		
	}
}
</script>
<?php
	function read($f){
		for($i = 0; $i<5; $i++){
			if($i==0){
				$id = fgets($f);
			}
			if($i==1){
				$name = fgets($f);
			}
			if($i==2){
				$price = fgets($f);
			}
			if($i==3){
				$pic = fgets($f);
			}
			if($i==4){
				$desc = fgets($f);
			}
		}
		buildProduct($id,$name,$price,$pic);
	}
	function buildProduct($id,$name,$price,$pic){
		echo "<div class='screensize'><div class='picborder'><a href='product.php?id=$id'>";
		echo "<img src='$pic' alt='$name' style='width:200px;height:200px;'>";
		echo "<div class='info'>$name</div></a>";
		echo "<button onclick='add($id)' style='display: block; margin: 0 auto;'>Add to Cart</button></div></div>";
	}
	$files = glob("txtfiles/*.txt");
	foreach($files as $file){
		$f = fopen($file, "r") or die ("Unable to Open File");
		read($f);
		fclose($f);
	}
?>