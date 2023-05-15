//Sawyer Fenwick
//6005011
//sf15zx
//Assign_2

//creates the entire wishlist
function createWishList(){
	//variable names
	var hp1 = "HP DeskJet 3755"
	var hp2 = "HP ENVY 5010"
	var hp3 = "HP OfficeJet 3833"
	var ca1 = "Canon PIXMA TS3129";
	var ca2 = "Canon PIXMA TR4527";
	var ca3 = "Canon PIXMA All-in-One";
	var br1 = "Brother MFC-J491DW";
	var br2 = "Brother HLL2390DW";
	var br3 = "Brother HLL2370DW";
	//for counting
	var hp1count = hp2count = hp3count = 0;
	var ca1count = ca2count = ca3count = 0;
	var br1count = br2count = br3count = 0;
	var hptot = catot = brtot = 0;
	//checking if arrays exist
	if(localStorage.getItem("arrayHP") == null){
		var arrayHP = [];
	}else{
		arrayHP = JSON.parse(localStorage.getItem("arrayHP"));
	}
	if(localStorage.getItem("arrayCA") == null){
		var arrayCA = [];
	}else{
		arrayCA = JSON.parse(localStorage.getItem("arrayCA"));
	}
	if(localStorage.getItem("arrayBR") == null){
		var arrayBR = [];
	}else{
		arrayBR = JSON.parse(localStorage.getItem("arrayBR"));
	}
	//iterating over arrays, checking for doubles
	for(var i = 0; i < arrayHP.length; i++){
		if(arrayHP[i] == 'hp1'){
			hp1count++;
		}
		else if(arrayHP[i] == 'hp2'){
			hp2count++;
		}
		else{
			hp3count++;
		}
	}
	for(var i = 0; i < arrayCA.length; i++){
		if(arrayCA[i] == 'ca1'){
			ca1count++;
		}
		else if(arrayCA[i] == 'ca2'){
			ca2count++;
		}
		else{
			ca3count++;
		}
	}
	for(var i = 0; i < arrayBR.length; i++){
		if(arrayBR[i] == 'br1'){
			br1count++;
		}
		else if(arrayBR[i] == 'br2'){
			br2count++;
		}
		else{
			br3count++;
		}
	}
	//totaling and saving counts
	hptot=hp1count+hp2count+hp3count;
	localStorage.setItem("hptot",JSON.stringify(hptot));
	catot=ca1count+ca2count+ca3count;
	localStorage.setItem("catot",JSON.stringify(catot));
	brtot=br1count+br2count+br3count;
	localStorage.setItem("brtot",JSON.stringify(brtot));
	//create elements for list
	if(hp1count!=0){
		var p = document.createElement("P");
		p.innerHTML = hp1+": x"+hp1count;
		var btn = document.createElement("BUTTON");
		btn.innerHTML = "Remove";
		btn.addEventListener("click", function(){
			removeH('hp1');
			location.reload();
		});
		p.appendChild(btn);
		document.getElementById('list').appendChild(p);
	}
	if(hp2count!=0){
		var p = document.createElement("P");
		p.innerHTML = hp2+": x"+hp2count;
		var btn = document.createElement("BUTTON");
		btn.innerHTML = "Remove";
		btn.addEventListener("click", function(){
			removeH('hp2');
			location.reload();
		});
		p.appendChild(btn);
		document.getElementById('list').appendChild(p);
	}
	if(hp3count!=0){
		var p = document.createElement("P");
		p.innerHTML = hp3+": x"+hp3count;
		var btn = document.createElement("BUTTON");
		btn.innerHTML = "Remove";
		btn.addEventListener("click", function(){
			removeH('hp3');
			location.reload();
		});
		p.appendChild(btn);
		document.getElementById('list').appendChild(p);
	}
	if(ca1count!=0){
		var p = document.createElement("P");
		p.innerHTML = ca1+": x"+ca1count;
		var btn = document.createElement("BUTTON");
		btn.innerHTML = "Remove";
		btn.addEventListener("click", function(){
			removeC('ca1');
			location.reload();
		});
		p.appendChild(btn);
		document.getElementById('list').appendChild(p);
	}
	if(ca2count!=0){
		var p = document.createElement("P");
		p.innerHTML = ca2+": x"+ca2count;
		var btn = document.createElement("BUTTON");
		btn.innerHTML = "Remove";
		btn.addEventListener("click", function(){
			removeC('ca2');
			location.reload();
		});
		p.appendChild(btn);
		document.getElementById('list').appendChild(p);
	}
	if(ca3count!=0){
		var p = document.createElement("P");
		p.innerHTML = ca3+": x"+ca3count;
		var btn = document.createElement("BUTTON");
		btn.innerHTML = "Remove";
		btn.addEventListener("click", function(){
			removeC('ca3');
			location.reload();
		});
		p.appendChild(btn);
		document.getElementById('list').appendChild(p);
	}
	if(br1count!=0){
		var p = document.createElement("P");
		p.innerHTML = br1+": x"+br1count;
		var btn = document.createElement("BUTTON");
		btn.innerHTML = "Remove";
		btn.addEventListener("click", function(){
			removeB('br1');
			location.reload();
		});
		p.appendChild(btn);
		document.getElementById('list').appendChild(p);
	}
	if(br2count!=0){
		var p = document.createElement("P");
		p.innerHTML = br2+": x"+br2count;
		var btn = document.createElement("BUTTON");
		btn.innerHTML = "Remove";
		btn.addEventListener("click", function(){
			removeB('br2');
			location.reload();
		});
		p.appendChild(btn);
		document.getElementById('list').appendChild(p);
	}
	if(br3count!=0){
		var p = document.createElement("P");
		p.innerHTML = br3+": x"+br3count;
		var btn = document.createElement("BUTTON");
		btn.innerHTML = "Remove";
		btn.addEventListener("click", function(){
			removeB('br3');
			location.reload();
		});
		p.appendChild(btn);
		document.getElementById('list').appendChild(p);
	}
}
//add to an array
function addHP(item){
	arrayHP.push(item);
	localStorage.setItem("arrayHP", JSON.stringify(arrayHP));
	inCart(arrayHP);
	createWishList();
}
//add item to array
function addCA(item){
	arrayCA.push(item);
	localStorage.setItem("arrayCA", JSON.stringify(arrayCA));
	inCart2(arrayCA);
	createWishList();
}
//add item to array
function addBR(item){
	arrayBR.push(item);
	localStorage.setItem("arrayBR", JSON.stringify(arrayBR));
	inCart3(arrayBR);
	createWishList();
}
//check if item is in cart
function inCart(array){
	if(!Array.isArray(array) || !array.length){
		document.getElementById('carthp1').innerHTML = "Not in Cart.";
		document.getElementById('carthp2').innerHTML = "Not in Cart.";
		document.getElementById('carthp3').innerHTML = "Not in Cart.";
	}
	if(array.includes('hp1') == true){
		document.getElementById('carthp1').innerHTML = "In Cart.";
	}
	if(array.includes('hp1') == false){
		document.getElementById('carthp1').innerHTML = "Not in Cart.";
	}
	if(array.includes('hp2') == true){
		document.getElementById('carthp2').innerHTML = "In Cart.";
	}
	if(array.includes('hp2') == false){
		document.getElementById('carthp2').innerHTML = "Not in Cart.";
	}
	if(array.includes('hp3') == true){
		document.getElementById('carthp3').innerHTML = "In Cart.";
	}
	if(array.includes('hp3') == false){
		document.getElementById('carthp3').innerHTML = "Not in Cart.";
	}
	
}
//check if item is in cart
function inCart2(array){
	if(!Array.isArray(array) || !array.length){
		document.getElementById('cartca1').innerHTML = "Not in Cart.";
		document.getElementById('cartca2').innerHTML = "Not in Cart.";
		document.getElementById('cartca3').innerHTML = "Not in Cart.";
	}
	if(array.includes('ca1') == true){
		document.getElementById('cartca1').innerHTML = "In Cart.";
	}
	if(array.includes('ca1') == false){
		document.getElementById('cartca1').innerHTML = "Not in Cart.";
	}
	if(array.includes('ca2') == true){
		document.getElementById('cartca2').innerHTML = "In Cart.";
	}
	if(array.includes('ca2') == false){
		document.getElementById('cartca2').innerHTML = "Not in Cart.";
	}
	if(array.includes('ca3') == true){
		document.getElementById('cartca3').innerHTML = "In Cart.";
	}
	if(array.includes('ca3') == false){
		document.getElementById('cartca3').innerHTML = "Not in Cart.";
	}
	
}
//check if item is in cart
function inCart3(array){
	if(!Array.isArray(array) || !array.length){
		document.getElementById('cartbr1').innerHTML = "Not in Cart.";
		document.getElementById('cartbr2').innerHTML = "Not in Cart.";
		document.getElementById('cartbr3').innerHTML = "Not in Cart.";
	}
	if(array.includes('br1') == true){
		document.getElementById('cartbr1').innerHTML = "In Cart.";
	}
	if(array.includes('br1') == false){
		document.getElementById('cartbr1').innerHTML = "Not in Cart.";
	}
	if(array.includes('br2') == true){
		document.getElementById('cartbr2').innerHTML = "In Cart.";
	}
	if(array.includes('br2') == false){
		document.getElementById('cartbr2').innerHTML = "Not in Cart.";
	}
	if(array.includes('br3') == true){
		document.getElementById('cartbr3').innerHTML = "In Cart.";
	}
	if(array.includes('br3') == false){
		document.getElementById('cartbr3').innerHTML = "Not in Cart.";
	}
	
}
//removes a specific element from the list
function removeH(key){
	arrayHP = JSON.parse(localStorage.getItem("arrayHP"));
	for(var i = 0; i < arrayHP.length; i++){
		if(arrayHP[i] == key){
			arrayHP.splice(i,1);
			break;
		}
	}
	localStorage.setItem("arrayHP", JSON.stringify(arrayHP));
}
//removes a specific element from the list
function removeC(key){
	arrayCA = JSON.parse(localStorage.getItem("arrayCA"));
	for(var i = 0; i < arrayCA.length; i++){
		if(arrayCA[i] === key){
			arrayCA.splice(i,1);
			break;
		}
	}
	localStorage.setItem("arrayCA", JSON.stringify(arrayCA));
}
//removes a specific element from the list
function removeB(key){
	arrayBR = JSON.parse(localStorage.getItem("arrayBR"));
	for(var i = 0; i < arrayBR.length; i++){
		if(arrayBR[i] === key){
			arrayBR.splice(i,1);
			break;
		}
	}
	localStorage.setItem("arrayBR", JSON.stringify(arrayBR));
}
//remove from array and update cart counter
function removeHP(key){
	removeH(key);
	if(arrayHP.includes('hp1') == false){
		document.getElementById('carthp1').innerHTML = "Not in Cart.";
	}
	if(arrayHP.includes('hp2') == false){
		document.getElementById('carthp2').innerHTML = "Not in Cart.";
	}
	if(arrayHP.includes('hp3') == false){
		document.getElementById('carthp3').innerHTML = "Not in Cart.";
	}
	createWishList();
}
//remove from array and update cart counter
function removeCA(key){
	removeC(key);
	if(arrayCA.includes('ca1') == false){
		document.getElementById('cartca1').innerHTML = "Not in Cart.";
	}
	if(arrayCA.includes('ca2') == false){
		document.getElementById('cartca2').innerHTML = "Not in Cart.";
	}
	if(arrayCA.includes('ca3') == false){
		document.getElementById('cartca3').innerHTML = "Not in Cart.";
	}
	createWishList();
}
//remove from array and update cart counter
function removeBR(key){
	removeB(key);
	if(arrayBR.includes('br1') == false){
		document.getElementById('cartbr1').innerHTML = "Not in Cart.";
	}
	if(arrayBR.includes('br2') == false){
		document.getElementById('cartbr2').innerHTML = "Not in Cart.";
	}
	if(arrayBR.includes('br3') == false){
		document.getElementById('cartbr3').innerHTML = "Not in Cart.";
	}
	createWishList();
}
//delete everything 
function clearAll(){
	localStorage.clear() //removes all local storage
	location.reload();  //reloads the list page
	arrayHP.length = 0; //removes all elements from my arrays
	arrayCA.length = 0;
	arrayBR.length = 0;
	localStorage.setItem("arrayHP", JSON.stringify(arrayHP));//save the empty arrays back to local storage
	localStorage.setItem("arrayCA", JSON.stringify(arrayCA));
	localStorage.setItem("arrayBR", JSON.stringify(arrayBR));
	writeCount(0,0,0);
}
//delete only HP list
function clearHP(array){
	array.length = 0;
	localStorage.setItem("arrayHP", JSON.stringify(array));
	hptot = 0;
	localStorage.setItem("hptot", JSON.stringify(hptot));
	location.reload();
}
//delete only CA list
function clearCA(array){
	array.length = 0;
	localStorage.setItem("arrayCA", JSON.stringify(array));
	catot = 0;
	localStorage.setItem("catot", JSON.stringify(catot));
	location.reload();
}
//delete only BR list
function clearBR(array){
	array.length = 0;
	localStorage.setItem("arrayBR", JSON.stringify(array));
	brtot = 0;
	localStorage.setItem("brtot", JSON.stringify(brtot));
	location.reload();
	
}
//display total counts
function writeCount(c1, c2, c3){
	document.getElementById("hinc").innerHTML = "Hewlett-Packard Printers in Cart:  x" + c1;
	document.getElementById("cinc").innerHTML = "Canon Printers in Cart:  x" + c2;
	document.getElementById("binc").innerHTML = "Brother Printers in Cart:  x" + c3;
}
