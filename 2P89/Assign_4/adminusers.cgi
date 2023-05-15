#!/usr/bin/python
#Sawyer_Fenwick
#6005011
#sf15zx
#Assign4
from pymongo import MongoClient
import cgi,os,uuid
username='sf15zx'
passwd='6005011'
client=MongoClient('mongodb://'+username+':'+passwd+'@127.0.0.1/'+username)
db=client[username]

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

print"Content-Type: text/html"
print
start = "<html><head><title>Admin User Page</title><body><h1 style='text-align:center'>I Sell, You Buy</h1>"
style = "<link rel='stylesheet' type='text/css' href='style.css'/>"
end = "</body></html>"
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
print """<div align='center'><h2>Users</h2>""" 
c = db.user.find()
for record in c:
    user=record['username']
    print "<div align='center'><a href='adminuser.cgi?"+str(user)+"'>"+user+"</a></div><br/>"
print end