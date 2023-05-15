<!--Sawyer_Fenwick-->
<!--6005011-->
<!--sf15zx-->
<!--Assign3-->
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Home</title>
	<link rel="stylesheet" type="text/css" href="style.css"/>
</head>
<body>
<h1 style="text-align:center">Carbonated Drinks R Us</h1>
<p id="status" style="text-align:right">Status: </p>
<div class="navbar">
	<a class="home.php">Home</a>
	<a href="active">Products</a>	<!--add URL  here -->
	<a href="wishlist.php">Wishlist</a>
	<div class="navbar-right">
		<a href="login.php">Login</a>
		<a href="logout.php">Logout</a>
	</div>
</div>
<?php
	function read($f){
		for($i = 0; $i<4; $i++){
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
		}
		buildProduct($id,$name,$price,$pic);
	}
	function buildProduct($id,$name,$price,$pic){
		echo "<div class='screensize'><div class='picborder'><a href='home.php'>";
		echo "<img src='$pic' alt='$name' style='width:200px;height:200px;'>";
		echo "<div class='info'>$name</div></a>";
		echo "<button>Add to Cart</button></div></div>";
	}
	function build($id,$name,$price,$pic){
		echo "$id" . "<br>";
		echo "$name" . "<br>";
		echo "$price" . "<br>";
		echo "$pic" . "<br>";
		echo "<br>";
	}
	$files = glob("txtfiles/*.txt");
	foreach($files as $file){
		$f = fopen($file, "r") or die ("Unable to Open File");
		read($f);
		fclose($f);
	}
?>