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
	<a href="products.php">Products</a>
	<a class="active">Wishlist</a>
	<div class="navbar-right">
		<a href="login.php">Login</a>
		<a href="logout.php">Logout</a>
	</div>
</div>
<h1 align="center">Your Cart</h1>
<div id="list" align="center"></div>
<p id="hi"></p>
<script>
var user = "<?php echo $_SESSION['status']?>";
if(user == "Online"){
	check();
}
if(user == "Offline"){		
	buildClientSide();
}
function check(){
	if(sessionStorage.length == 0){
		request();
	}
	else{
		buildMerged();
	}
}

function buildMerged(){
	array = JSON.parse(sessionStorage.getItem("array"));
	for(var i = 0; i < array.length; i++){
		var item = "id="+array[i];
		var req = new XMLHttpRequest();
		req.open("POST", "sessionList.php", true);
		req.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		req.send(item);
	}
	request();
}

function buildServerList(array){
	for(var i = 0; i < array.length; i++){
		var p = document.createElement("P");
		var btn = document.createElement("BUTTON");
		btn.innerHTML = "Remove";
		if(array[i] == 1){
			p.innerHTML = "Coca Cola ";
			btn.addEventListener("click", function(){
				removeReq(1);
			});
			p.appendChild(btn);
		}
		if(array[i] == 2){
			p.innerHTML = "Pepsi ";
			btn.addEventListener("click", function(){
				removeReq(2);
			});
			p.appendChild(btn);
		}
		if(array[i] == 3){
			p.innerHTML = "Sprite ";
			btn.addEventListener("click", function(){
				removeReq(3);
			});
			p.appendChild(btn);
		}
		if(array[i] == 4){
			p.innerHTML = "Crush ";
			btn.addEventListener("click", function(){
				removeReq(4);
			});
			p.appendChild(btn);
		}
		if(array[i] == 5){
			p.innerHTML = "Dr. Pepper ";
			btn.addEventListener("click", function(){
				removeReq(5);
			});
			p.appendChild(btn);
		}
		document.getElementById('list').appendChild(p);
	}
}

function request(){
	
	var req = new XMLHttpRequest();
	req.onreadystatechange = function(){
		if(req.readyState == 4 & req.status == 200){
			var list = req.responseText;
			var b = list.toString();
			var c = b.split(",");
			buildServerList(c);
		}
	}
	req.open("GET","list.php",true);
	req.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	req.send();
}

function removeReq(key){
	var item = "id="+key;
	var req = new XMLHttpRequest();
	if(req.readyState == 4 & req.status == 200){
		var list = req.responseText;
		var b = list.toString();
		var c = b.split(",");
		buildServerList(c);
	}
	req.open("POST", "remove.php", true);
	req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	req.send(item);
}

function buildClientSide(){
	if(sessionStorage.getItem("array")==null){
		var array = [];
	}else{
		array = JSON.parse(sessionStorage.getItem("array"));
	}
	for(var i = 0; i < array.length; i++){
		var p = document.createElement("P");
		var btn = document.createElement("BUTTON");
		btn.innerHTML = "Remove";
		switch(array[i]){
			case 1:
				p.innerHTML = "Coca Cola ";
				btn.addEventListener("click", function(){
					remove(1);
				});
				p.appendChild(btn);
				break;
			case 2:
				p.innerHTML = "Pepsi ";
				btn.addEventListener("click", function(){
					remove(2);
				});
				p.appendChild(btn);
				break;
			case 3:
				p.innerHTML = "Sprite ";
				btn.addEventListener("click", function(){
					remove(3);
				});
				p.appendChild(btn);
				break;
			case 4:
				p.innerHTML = "Crush ";
				btn.addEventListener("click", function(){
					remove(4);
				});
				p.appendChild(btn);
				break;
			case 5:
				p.innerHTML = "Dr. Pepper ";
				btn.addEventListener("click", function(){
					remove(5);
				});
				p.appendChild(btn);
				break;
		}
		document.getElementById('list').appendChild(p);
	}
}
function remove(key){
	array = JSON.parse(sessionStorage.getItem("array"));
	for(var i = 0; i < array.length; i++){
		if(array[i] == key){
			array.splice(i,1);
			break;
		}
	}
	sessionStorage.setItem("array", JSON.stringify(array));
	if(array.length == 0){
		sessionStorage.clear();
	}
	location.reload();
}
</script>