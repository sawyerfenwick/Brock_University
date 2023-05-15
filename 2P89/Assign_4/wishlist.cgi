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
start = "<html><head><title>Wishlist</title></head>"
bodyOn = "<body><h1 style='text-align:center'>I Sell, You Buy</h1>"
bodyOff = "<body onload='createWishList()'><h1 style='text-align:center'>I Sell, You Buy</h1>"
style = "<script type='text/javascript' src='script.js'></script><link rel='stylesheet' type='text/css' href='style.css'/><link rel='stylesheet' type='text/css' media='print' href='print.css'/>"
end = "</body></html>"
clientnavbar = """<div class='navbar'>
    <a href='home.cgi'>Home</a>
    <a href='products.cgi'>Products</a>
    <a class='active'>Wishlist</a>
    <div class='navbar-right'>
        <a href='login.cgi'>Login/Logout</a>
        <a href='signup.cgi'>Sign Up</a>
     </div></div>"""
adminnavbar = """<div class='navbar'>
    <a href='home.cgi'>Home</a>
    <a href='products.cgi'>Products</a>
    <a class='active'>Wishlist</a>
    <div class='navbar-right'>
        <a href='admin.cgi'>Admin</a>
        <a href='login.cgi'>Login/Logout</a>
     </div></div>"""
onlinenavbar = """<div class='navbar'>
    <a href='home.cgi'>Home</a>
    <a href='products.cgi'>Products</a>
    <a href='wishlist.cgi'>Wishlist</a>
    <div class='navbar-right'>
        <a href='login.cgi'>Login/Logout</a>
     </div></div>"""
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

online = check_logged_in()
print start
#wrote twice? online=check_logged_in()
if online:
    print bodyOn
else:
    print bodyOff
print style
displayStatus()
checkAdmin()
if online:
    print "<div class='print' id='list' align='center'><h1 align='center'>Your Cart</h1>"
    rec = db.user.find_one({'username':online})
    wl = rec['list']
    for i in range(len(wl)):
        print wl[i]+"<br>"
    print "</div>" 
else:
    print "<div class='print' id='list' align='center'><h1 align='center'>Your Cart</h1></div>"
if online:
    print 
else:
    print "<button onclick='clearAll()' style='display: block; margin: 0 auto;'>Clear All</button>"
print "<br><br><div align='center'><a href='javascript:window.print()'>Print Your List</a></div>"
print end