#!/usr/bin/python
#Sawyer_Fenwick
#6005011
#sf15zx
#Assign4
from pymongo import MongoClient
import cgi, os, uuid
username='sf15zx'
passwd='6005011'
client=MongoClient('mongodb://'+username+':'+passwd+'@127.0.0.1/'+username)
db=client[username]

print"Content-Type: text/html"
print

start = "<html><head><title>Admin Products (Remove)</title><body><h1 style='text-align:center'>I Sell, You Buy</h1>"
style = "<link rel='stylesheet' type='text/css' href='style.css'/>"
end = "</body></html>"
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
def displayStatus():
    user=check_logged_in()
    if user:
        print "<div align='right'><span style='text-align:right'>Status: </span><span class='online'>Online</span> User: "+user+"</div>"
    else:
        print "<div align='right'><span style='text-align:right'>Status: </span><span class='offline'>Offline</span></div>"
adminnavbar = """<div class='navbar'>
    <a href='home.cgi'>Home</a>
    <a href='products.cgi'>Products</a>
    <a href='wishlist.cgi'>Wishlist</a>
    <div class='navbar-right'>
        <a href='admin.cgi'>Admin</a>
        <a href='login.cgi'>Login/Logout</a>
     </div></div>"""
print start
print style 
displayStatus()
print adminnavbar
c = db.products.find()
for record in c:
    tagarr = []
    tagarr = record['tag']
    print "<div class='screensize'><div class='picborder'>"
    print "<img src='"+record['image']+"' alt='"+record['name']+"' style='width:200px;height:200px;'>"
    print "<div class='info'>"+record['name']+"</div>"
    print "<div class='info'>Tags:"
    for i in range(len(tagarr)):
        print tagarr[i]+","
    print "</div>"
    print "<form action='#remove_"+record['name']+"' method='post'><input type='submit' value='Remove from Product Page' name='"+record['name']+"'></form><br></div></div>"
form = cgi.FieldStorage()
c = db.user.find()
if "fork" in form:
    db.products.update_one({"name":"fork"},{"$set":{"enable":"N"}})
    for users in c:
        name=users['username']
        db.user.update_one({"username":name},{'$pull':{"list": "fork"}})
elif "knife" in form:
    db.products.update_one({"name":"knife"},{"$set":{"enable":"N"}})
    for users in c:
        name=users['username']
        db.user.update_one({"username":name},{'$pull':{"list": "knife"}})
elif "spoon" in form:
    db.products.update_one({"name":"spoon"},{"$set":{"enable":"N"}})
    for users in c:
        name=users['username']
        db.user.update_one({"username":name},{'$pull':{"list": "spoon"}})
elif "bowl" in form:
    db.products.update_one({"name":"bowl"},{"$set":{"enable":"N"}})
    for users in c:
        name=users['username']
        db.user.update_one({"username":name},{'$pull':{"list": "bowl"}})
elif "cup" in form:
    db.products.update_one({"name":"cup"},{"$set":{"enable":"N"}})
    for users in c:
        name=users['username']
        db.user.update_one({"username":name},{'$pull':{"list": "cup"}})
elif "mug" in form:
    db.products.update_one({"name":"mug"},{"$set":{"enable":"N"}})
    for users in c:
        name=users['username']
        db.user.update_one({"username":name},{'$pull':{"list": "mug"}})
elif "pot" in form:
    db.products.update_one({"name":"pot"},{"$set":{"enable":"N"}})
    for users in c:
        name=users['username']
        db.user.update_one({"username":name},{'$pull':{"list": "pot"}})
elif "pan" in form:
    db.products.update_one({"name":"pan"},{"$set":{"enable":"N"}})
    for users in c:
        name=users['username']
        db.user.update_one({"username":name},{'$pull':{"list": "pan"}})
elif "cutting board" in form:
    db.products.update_one({"name":"cutting board"},{"$set":{"enable":"N"}})
    for users in c:
        name=users['username']
        db.user.update_one({"username":name},{'$pull':{"list": "cutting board"}})
elif "whisk" in form:
    db.products.update_one({"name":"whisk"},{"$set":{"enable":"N"}})
    for users in c:
        name=users['username']
        db.user.update_one({"username":name},{'$pull':{"list": "whisk"}})
elif "spatula" in form:
    db.products.update_one({"name":"spatula"},{"$set":{"enable":"N"}})
    for users in c:
        name=users['username']
        db.user.update_one({"username":name},{'$pull':{"list": "spatula"}})
elif "infinity gauntlet" in form:
    db.products.update_one({"name":"infinity guantlet"},{"$set":{"enable":"N"}})
    for users in c:
        name=users['username']
        db.user.update_one({"username":name},{'$pull':{"list": "infinity gauntlet"}})
    
print "</body></html>"
