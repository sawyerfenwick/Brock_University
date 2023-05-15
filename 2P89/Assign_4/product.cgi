#!/usr/bin/python
#Sawyer Fenwick
#6005011
#sf15zx
#Assign4
from pymongo import MongoClient
from bson.objectid import ObjectId
import cgi, os, uuid
username='sf15zx'
passwd='6005011'
client=MongoClient('mongodb://'+username+':'+passwd+'@127.0.0.1/'+username)
db=client[username] 

print "Content-Type: text/html"
print 
id = os.environ['QUERY_STRING']
c = db.products.find_one({'_id': ObjectId(id)})
name = c['name']
desc = c['desc']
cost = c['cost']
image = c['image']
tagarr = []
listarr = []
tagarr = c['tag']
start = "<html>"
script = """<script>
        if(sessionStorage.getItem("list") == null){
            var list = [];
        }else{
            list = JSON.parse(sessionStorage.getItem("list"));
        }
        function add(item){
            if(!list.includes(item)){
                list.push(item);
            }
            sessionStorage.setItem("list",JSON.stringify(list));
        }
</script>"""
def check_logged_in():
	if os.environ.has_key('HTTP_COOKIE'):
		user=None
		usid=None
		cookies=os.environ['HTTP_COOKIE'].split(';')
		for cookie in cookies:
			if cookie.split('=')[0].strip()=='user':
				user=cookie[cookie.find('=')+1:]
			elif cookie.split('=')[0].strip()=='usid':
				usid=cookie[cookie.find('=')+1:]
		if user and usid:
			rec=db.user.find_one({'username':user,'usid':usid})
			if rec!=None:
				return user
	return None
def checkAdmin():
    user = check_logged_in()
    if user:
        admin = db.user.find_one({'username':user,'admin':"Y"})
        if admin!=None:
            print adminnavbar
        else:
            print onlinenavbar
    else:
        print clientnavbar
def displayStatus():
    user=check_logged_in()
    if user:
        print "<div align='right'><span style='text-align:right'>Status: </span><span class='online'>Online</span> User: "+user+"</div>"
    else:
        print "<div align='right'><span style='text-align:right'>Status: </span><span class='offline'>Offline</span></div>"
head = "<head><title>"+name+"</title><body><h1 style='text-align:center'>I Sell, You Buy</h1>"
style = "<link rel='stylesheet' type='text/css' href='style.css'/>"
end = "</body></html>"
onlinenavbar = """<div class='navbar'>
    <a href='home.cgi'>Home</a>
    <a href='products.cgi'>Products</a>
    <a href='wishlist.cgi'>Wishlist</a>
    <div class='navbar-right'>
        <a href='login.cgi'>Login/Logout</a>
     </div></div>"""
clientnavbar = """<div class='navbar'>
    <a href='home.cgi'>Home</a>
    <a href='products.cgi'>Products</a>
    <a href='wishlist.cgi'>Wishlist</a>
    <div class='navbar-right'>
        <a href='login.cgi'>Login/Logout</a>
        <a href='signup.cgi'>Sign Up</a>
     </div></div>"""
adminnavbar = """<div class='navbar'>
    <a href='home.cgi'>Home</a>
    <a href='products.cgi'>Products</a>
    <a href='wishlist.cgi'>Wishlist</a>
    <div class='navbar-right'>
        <a href='admin.cgi'>Admin</a>
        <a href='login.cgi'>Login/Sign Up</a>
        <a href='logout.cgi'>Logout</a>
     </div></div>"""
print start
print script
print head
print style
displayStatus()
checkAdmin()
print "<div id='outer-div' class='screensize'><div id='inner-div' class='picborder'>"
print "<img src='"+image+"' alt='"+name+"' style='width:200px;height:200px;'>"
print "<div class='info'>"+name+"</div>"
if cost=="Everything":
    print "<div class='info'>Cost?<br>"+cost+"</div>"
else:
    print "<div class='info'>"+cost+"</div>"
print "<div class='info'>"+desc+"</div>"
print "<div class='info'>Tags:"
for i in range(len(tagarr)):
    print tagarr[i]+","
print "</div>"
print "<form action='#' method='post'><input type='text' placeholder='Add Tag' name='tag'><input type='submit' value='Go!'></form>"
online=check_logged_in()
if online:
    print "<form action='#' method ='post'><input type='submit' value='Add to WishList' name='"+name+"'></form>" 
else:
    print "<button onclick=add('"+name+"') style='display:block;margin:0 auto;'>Add to Wishlist</button></div></div>"
form = cgi.FieldStorage()
tag = form.getvalue('tag')
if tag!=None:
    c = db.products.find_one({'name':name,'tag':tag})
    if c==None:
        tagarr.append(tag)
        db.products.update_one({'name':name},{'$set':{'tag':tagarr}})
if name in form:
    u = db.user.find_one({'username':online,'list':name})
    if u==None:
        l = db.user.find_one({'username':online})
        listarr = l['list']
        listarr.append(name)
        db.user.update_one({'username':online},{'$set':{'list':listarr}})
print end