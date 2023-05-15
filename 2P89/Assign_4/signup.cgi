#!/usr/bin/python
#Sawyer Fenwick
#6005011
#sf15zx
#Assign4
from pymongo import MongoClient
import cgi, os, uuid
username='sf15zx'
passwd='6005011'
client=MongoClient('mongodb://'+username+':'+passwd+'@127.0.0.1/'+username)
db=client[username]

start = "<html><head><title>Sign Up</title><body><h1 style='text-align:center'>I Sell, You Buy</h1>"
style = "<link rel='stylesheet' type='text/css' href='style.css'/>"
end = "</body></html>"
adminnavbar = """<div class='navbar'>
    <a href='home.cgi'>Home</a>
    <a href='products.cgi'>Products</a>
    <a href='wishlist.cgi'>Wishlist</a>
    <div class='navbar-right'>
        <a href='admin.cgi'>Admin</a>
        <a href='login.cgi'>Login/Logout</a>
        <a class='active'>Sign Up</a>
     </div></div>"""
clientnavbar = """<div class='navbar'>
    <a href='home.cgi'>Home</a>
    <a href='products.cgi'>Products</a>
    <a href='wishlist.cgi'>Wishlist</a>
    <div class='navbar-right'>
        <a href='login.cgi'>Login/Logout</a>
        <a class='active'>Sign Up</a>
     </div></div>"""

def checkAdmin():
    user = check_logged_in()
    if user:
        admin = db.user.find_one({'username':user,'admin':"Y"})
        if admin!=None:
            print adminnavbar
        else:
            print clientnavbar
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
def userbox():
	fs=cgi.FieldStorage()
	uname=fs.getfirst('uname')
	pw=fs.getfirst('passwd')
	if uname!=None and pw!=None:
		uname=str(uname); pw=str(pw); passwd=hash(pw)
		rec=db.user.find_one({'username':uname})
		if rec==None:
			db.user.insert_one({'username':uname,'passwd':passwd,'admin':"N",'list':[]})
	box="""<form action='' onsubmit='test' method='post'>
	Username: <input type='text' name='uname'/><br/><br/>
	Password: <input type='password' name='passwd'/>
	<br/><br/><input type='submit' value='Sign Up'/>
	</form>"""
	return box
ub=userbox()
print "Content-Type: text/html"
print 
print start
print style
displayStatus()
checkAdmin()
print "<div align='center' id='user'><h2>Sign Up!</h2>"+ub+"</div>"
print end