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
start = "<html><head><title>Admin Home Page</title><body><h1 style='text-align:center'>I Sell, You Buy</h1>"
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
        <a class='active'>Admin</a>
        <a href='login.cgi'>Login/Logout</a>
     </div></div>"""
content = """<div><p></p><button onclick="location.href='adminusers.cgi';"style='display:block;margin: 0 auto;'>Users</button><ul style=text-align:center><li>List all Users</li><li>Give/Remove Admin Status</li><li>Delete Users</li></ul>
<button onclick="location.href='adminadd.cgi';" style='display:block;margin:0 auto'>Add Products</button><ul style=text-align:center><li>Add Products for Users</li></ul>
<button onclick="location.href='adminremove.cgi';" style='display:block;margin:0 auto'>Remove Products</button><ul style=text-align:center><li>Remove Products for Users</li></ul><p></p></div>
"""
print start
print style
displayStatus()
print adminnavbar
print content
print end