<?php
	$file = fopen("p1.txt", "r") or die("Unable to open file");
	for($i = 0; $i<4; $i++){
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
	}
	function build($id){
		echo "$id";
	}
	fclose($file);
	build($id);
?>
