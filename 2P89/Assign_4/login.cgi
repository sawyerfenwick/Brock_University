#!/usr/bin/python
#Sawyer Fenwick
#6005011
#sf15zx
#Assign4
from pymongo import MongoClient
import cgi, os, uuid
username='sf15zx'
password='6005011'
client=MongoClient('mongodb://'+username+':'+password+'@127.0.0.1/'+username)
db=client[username]

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

def errornope():
	print "Content-Type: text/html"
	print 
	print "<html><head><title>Error: Bad Login</title></head><body>"
	print "<a href='login.cgi'>Invalid Username/Password. Please Try Again</a>"
	print "</body></html>"
	exit()

def create_session(username):
	sid=uuid.uuid1().hex
	db.user.update_one({'username':username},{'$set':{'usid':sid}})
	print "Set-Cookie: user="+username
	print "Set-Cookie: usid="+sid
	print "Location: ./home.cgi"
	print 
	exit()

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

def actually_sign_out(username):
	db.user.update_one({'username':username},{'$unset':{'usid':''}})
	print "Location: ./home.cgi"

def signoutbox():
	return "<form action='#' method='post'><input type='submit' name='logout' value='Logout'></form>"

def userbox():
	fs=cgi.FieldStorage()
	uname=fs.getfirst('uname')
	pw=fs.getfirst('passwd')
	if uname!=None and pw!=None:
		uname=str(uname); pw=str(pw); pw=hash(pw)
		rec=db.user.find_one({'username':uname,'passwd':pw})
		if rec==None:
			errornope()
		else:
			create_session(uname)
	user=check_logged_in()
	if user:
		if fs.getfirst('logout')!=None:
			actually_sign_out(user)
		else:
			return signoutbox()
	box="""<form action='#' method='post'>
	Username: <input type='text' name='uname'/><br/><br/>
	Password: <input type='text' name='passwd'/>
	<br/><br/><input type='submit' value='Sign in'/>
	</form>"""
	return box
ub=userbox()
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
        <a class='active'>Login/Logout</a>
     </div></div>"""
onlinenavbar = """<div class='navbar'>
    <a href='home.cgi'>Home</a>
    <a href='products.cgi'>Products</a>
    <a href='wishlist.cgi'>Wishlist</a>
    <div class='navbar-right'>
        <a href='login.cgi'>Login/Logout</a>
     </div></div>"""
clientnavbar = """<div class='navbar'>
    <a class='active'>Home</a>
    <a href='products.cgi'>Products</a>
    <a href='wishlist.cgi'>Wishlist</a>
    <div class='navbar-right'>
        <a class='active'>Login/Logout</a>
        <a href='signup.cgi'>Sign Up</a>
     </div></div>"""
print "Content-Type: text/html"
print 
print "<html><title>Login/Logout</title><body>"
print "<link rel='stylesheet' type='text/css' href='style.css'/>"
print "<h1 style='text-align:center'>I Sell, You Buy</h1>"
displayStatus()
checkAdmin()
print "<br/><div align='center' id='user'>"+ub+"</div>"
print "</body></html>"
