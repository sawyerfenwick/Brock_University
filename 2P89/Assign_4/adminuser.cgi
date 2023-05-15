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
user = os.environ['QUERY_STRING']
c = db.user.find_one({'username':user})
admin = c['admin']
id = c['_id']
start = "<html><head><title>"+user+"</title><body><h1 style='text-align:center'>I Sell, You Buy</h1>"
style = "<link rel='stylesheet' type='text/css' href='style.css'/>"
end = "</body></html>"
adminnavbar = """<div class='navbar'>
    <a href='home.cgi'>Home</a>
    <a href='products.cgi'>Products</a>
    <a href='wishlist.html'>Wishlist</a>
    <div class='navbar-right'>
        <a href='admin.cgi'>Admin</a>
        <a href='login.cgi'>Login/Logout</a>
     </div></div>"""
if admin=="Y":
    status="Administrator"
else:
    status="User"
current = check_logged_in()
print "Content-Type: text/html"
print 
print start
print style
displayStatus()
print adminnavbar
print "<div id='outer-div' class='screensize'>"+user+"<br/>"
print "Status: "+status
print "<form action='#' method='post'>"
if admin=="Y":
    print "<input name='remove' value='Remove Admin' type='submit'>"
else:
    print "<input name='make' value='Make Admin' type='submit'>"
print "<input value='Delete User' name='delete' type='submit'></form>"
form = cgi.FieldStorage()
if "make" in form:
    db.user.update_one({'username':user},{'$set':{'admin':"Y"}})
if "remove" in form:
    if current!=user: #cannot revoke own admin status
        db.user.update_one({'username':user},{'$set':{'admin':"N"}})
if "delete" in form:
    if current!=user: #cannot delete self
        db.user.remove({'username':user})
print end