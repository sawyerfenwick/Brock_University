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
	function build($id,$name,$price,$pic,$desc){
		echo "<title>$name</title>";
		echo "<body id='$name'><p>$desc</p></body>";
		echo "<div id='outer-div' class='screensize'><div id='inner-div' class='picborder'>";
		echo "<img src='$pic' alt='$name' style='width:200px;height:200px;'>";
		echo "<div class='info'>$name<br>$$price</div></a>";
		echo "<button onclick='add($id)' style='display: block; margin: 0 auto;'>Add to Cart</button></div></div>";
	}
	function read($file){
		for($i = 0; $i<5; $i++){
			if($i==0){
				$id = fgets($file);
			}
			if($i==1){
				$name = fgets($file);
			}
			if($i==2){
				$price = fgets($file);
			}
			if($i==3){
				$pic = fgets($file);
			}
			if($i==4){
				$desc = fgets($file);
			}
		}
		build($id,$name,$price,$pic,$desc);
	}
	function redirect(){
		echo"<title>404 Error</title>";
		echo "<body><h1 text-align:center;>404: Page Not Found!</h1></body>";
	}
	$url = $_SERVER['REQUEST_URI'];
	$parts = parse_url($url);
	parse_str($parts['query'], $query);
	$id = $query['id'];
	$file=fopen("txtfiles/$id.txt","r") or die(redirect());
?>
<html>
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
<head>
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
	<a href="products.php">Products</a>
	<a href="wishlist.php">Wishlist</a>
	<div class="navbar-right">
		<a href="login.php">Login</a>
		<a href="logout.php">Logout</a>
	</div>
</div>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="style.css"/>
</head>
</html>
<?php
	read($file);
	fclose($file);
?>