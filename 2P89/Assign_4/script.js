//Sawyer Fenwick
//6005011
//sf15zx
//Assign_4
//Used for creating local client side wishlist

function createWishList(){
	if(sessionStorage.getItem("list") == null){
		var list = [];
	}else{
		list = JSON.parse(sessionStorage.getItem("list"));
	}
	for(var i = 0; i < list.length; i++){
		var p = document.createElement("P");
		p.innerHTML = list[i];
		item = list[i];
		var btn = document.createElement("BUTTON");
		btn.innerHTML = "Remove";
		btn.classList.add("hide-button");
		btn.addEventListener("click",function(){
			remove(item);
			location.reload();
		});
		p.appendChild(btn);
		document.getElementById("list").appendChild(p);
	}
}

function clearAll(){
	sessionStorage.clear();
	location.reload();
	list.length = 0;
	sessionStorage.setItem("list", JSON.stringify(list));
}

function remove(item){
	list = JSON.parse(sessionStorage.getItem("list"));
	for(var i = 0; i < list.length; i++){
		if(list[i] == item){
			list.splice(i,1);
			break;
		}
    }
    sessionStorage.setItem("list",JSON.stringify(list));
}