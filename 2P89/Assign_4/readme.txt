Please follow this guide for how to operate the site:

url: http://www.cosc.brocku.ca/~sf15zx/A4/home.cgi

USER PAGES: 

HOME.CGI
This is the home page. From here you can search for the current product listings using tags.
If several objects share a tag, all of them will appear. 
Enter "stabby" into the search bar.
2 items will be returned. The "fork" and the "knife" items. 
If you click on either of them it will take you to their individual product page.
If you enter a tag that does not exist, the following message will display:
We do not have anything 'tag you searched for' in stock. Please check back later!

PRODUCTS.CGI
This is the product landing page. From here you can view all products that are currently available.
Clicking on any of them will take you to their individual product page.

PRODUCT.CGI?_id
This is the individual product page. From here you can add the product to your server list
(if logged in) or your session storage list (if logged out). The individual page is generated
using the mongodb _id tag. Anyone can add tags to the products from here. They do not have to 
be single word. There are no restrictions on tags.

WISHLIST.CGI
This is the wishlist page. When logged out it displays your list from session storage.
When logged in it retrieves the list from mongodb and displays it. The client side list
is 100% operational. The server list does not have the ability to remove items from the list.
The list can be printed (reasonably) from the "Print Your List" link at the bottom.
I did not merge the lists. 

LOGIN.CGI
This is the log in/log out page. When logged in it displays a logout button. When logged out
it displays a login form. There are currently 2 users, admin and user. admins password is admin 
and users password is user. When logging in the password field takes the input, and compares the 
hash value of your input to the hash value of the stored password on mongodb. During login a usid
is set and cookies are set. On logout the cookies remain, but the usid is removed from the users 
profile on mongodb. (This is mostly the Lab8 login page with small tweaks). If you login unsuccessfully
you will be prompted to try again. 

SIGNUP.CGI
This is the sign up page. It will only be displayed if you are not already signed in. If you are 
signed in you cannot sign up. When you sign up, a profile is created for you consisting of 
username, password (hashed NOT plaintext), admin bit flag, standard _id and an empty list.

ADMIN PAGES:

ALL admin pages are ONLY ACCESSIBLE if you are an admin.

ADMIN.CGI
This is the admin main page. It displays links to 3 pages. One for administering users, 
one for adding products, and one for removing products. 

ADMINUSERS.CGI
This page lists all the current users. Clicking on a user will bring up their profile.

ADMINUSER.CGI?NAME
This is the user profile page. Here you can see the users name, and current status: Admin or User.
If they are an admin you can demote them to user and vice versa. You can also delete the account.
If you try to delete your own account it will not allow you to. If you try to revoke your own admin 
status it will not allow you to. After you have changed user status or deleted user, press "ADMIN" on the navbar
to return you to the main page.

ADMINADD.CGI
This page allows you to add the products to the user product page. If the product already exists 
nothing happens. If it does not then it is created on the user page. 

ADMINREMOVE.CGI
This page allows you to remove the products from the user product page. If the product is already 
removed nothing happens. If it exists it will be removed from the page. If the item exists in anyones 
wishlist it is automatically removed, before they even sign in.

GENERAL:
When logged out, "Status:Offline" will appear in the top right corner. When logged in 
"Status:Online" will appear as well as "User:" and your account name. The navbar changes
based on who is currently using it, a user and admin or a nonuser. 