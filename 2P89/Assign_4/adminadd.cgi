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

start = "<html><head><title>Admin Products (Add)</title><body><h1 style='text-align:center'>I Sell, You Buy</h1>"
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
    print "<form action='#add_"+record['name']+"' method='post'><input type='submit' value='Add to Product Page' name='"+record['name']+"'></form><br></div></div>"
form = cgi.FieldStorage()
if "fork" in form:
    db.products.update_one({"name":"fork"},{"$set":{"enable":"Y"}})
elif "knife" in form:
    db.products.update_one({"name":"knife"},{"$set":{"enable":"Y"}})
elif "spoon" in form:
    db.products.update_one({"name":"spoon"},{"$set":{"enable":"Y"}})
elif "bowl" in form:
    db.products.update_one({"name":"bowl"},{"$set":{"enable":"Y"}})
elif "cup" in form:
    db.products.update_one({"name":"cup"},{"$set":{"enable":"Y"}})
elif "mug" in form:
    db.products.update_one({"name":"mug"},{"$set":{"enable":"Y"}})
elif "pot" in form:
    db.products.update_one({"name":"pot"},{"$set":{"enable":"Y"}})
elif "pan" in form:
    db.products.update_one({"name":"pan"},{"$set":{"enable":"Y"}})
elif "cutting board" in form:
    db.products.update_one({"name":"cutting board"},{"$set":{"enable":"Y"}})
elif "whisk" in form:
    db.products.update_one({"name":"whisk"},{"$set":{"enable":"Y"}})
elif "spatula" in form:
    db.products.update_one({"name":"spatula"},{"$set":{"enable":"Y"}})
elif "infinity gauntlet" in form:
    db.products.update_one({"name":"infinity gauntlet"},{"$set":{"enable":"Y"}})
print "</body></html>"
