#Sawyer Fenwick | sf15zx | 6005011 
#COSC_2P05 | Assignment 2
import os
import platform 

#determines how to write paths for Windows/Linux machines 
system = platform.system()
#to keep track of current and previour directories 
cur_dir = os.getcwd() 
prev_dir = cur_dir
list = [] 
def dir():
    """
    Returns current working directory.
    
    Returns:
    String: current working directory
    """
    return cur_dir

def help_head():
    """
    Returns the help string for the 'head' command
    
    Returns:
    String: description of 'head' command
    """
    return """head (filename) [# of lines] --- preview of a file's contetns\n\n       # of lines defaults to 5 unless specified"""

def help_run():
    """
    Returns the help string for the 'run' command
    
    Returns:
    String: description of 'run' command
    """
    return """run (script name) --- uses a file to specify the commands to execute"""

def help_help():
    """
    Returns the help string for the 'help' command
    
    Returns:
    String: description of 'help' command
    """
    return """help --- displays the list of all commands\nhelp [command] --- shows how to use that commands"""

def help_vn():
    """
    Returns the help string for the 'vn' command
    
    Returns:
    String: description of 'vn' command
    """
    return """vn (note name) --- displays the contents of a note"""

def help_cd():
    """
    Returns the help string for the 'cd' command
    
    Returns:
    String: description of 'cd' command
    """
    return """cd (path) --- changes the durrent directory\n\n       path = '-' will change the directory to the one you just came from"""

def help_pwd():
    """
    Returns the help string for the 'pwd' command
    
    Returns:
    String: description of 'pwd' command
    """
    return """pwd --- prints the current working directory"""

def help_find():
    """
    Returns the help string for the 'find' command
    
    Returns:
    String: description of 'find' command
    """
    return """find (pattern) --- recursively follows folders off current working directory to find files"""

def help_md5():
    """
    Returns the help string for the 'md5' command
    
    Returns:
    String: description of 'md5' command
    """
    return """md5 (filename) --- displays an md5 hash of the contents of a file"""

def help_search():
    """
    Returns the help string for the 'search' command
    
    Returns:
    String: description of 'search' command
    """
    return """search --- recursively follows folders off working directory to find files with matching content"""

def help_ln():
    """
    Returns the help string for the 'ln' command
    
    Returns:
    String: description of 'ln' command
    """
    return """ln --- displays names of all notes files"""

def help_tt():
    """
    Returns the help string for the 'tt' command
    
    Returns:
    String: description of 'tt' command
    """
    return """tt (filename) --- infers the OS convention of the specified file"""

def help_tn():
    """
    Returns the help string for the 'tn' command
    
    Returns:
    String: description of 'tn' command
    """
    return """tn (note name) --- create a new note, dictated from the user, to save in a .notename file\n\n       if filename exists, filename will be overwritten"""

def help_ls():
    """
    Returns the help string for the 'ls' command
    
    Returns:
    String: description of 'ls' command
    """
    return """ls [pattern] --- displays the contents of the current directory\n   
       if a pattern is supplied, only show file/folder names that contain that text"""

def help_exit():
    """
    Returns the help string for the 'exit' command
    
    Returns:
    String: description of 'exit' command
    """
    return """exit --- exits the program"""

def help(com):
    """
    Calls the help function with the associated command, creates a library for all possible help commands
    
    Parameters:
    com(String): Command given by the User to be put in multiple selection
    
    Returns:
    String: see: help_cd, help_exit, help_find, help_head, help_help, help_ln, help_ls, 
                 help_md5, help_pwd, help_run, help_search, help_tn, help_tt, help_vn. 
    """
    if com == None:
        return """---\nhead\nrun\nhelp\nvn\ncd\npwd\nfind\nmd5\nsearch\nln\ntt\ntn\nls\nexit\n---"""
    switch = {
        "head":help_head(),
        "run":help_run(),
        "help":help_help(),
        "vn":help_vn(),
        "cd":help_cd(),
        "pwd":help_pwd(),
        "find":help_find(),
        "md5":help_md5(),
        "search":help_search(),
        "ln":help_ln(),
        "tt":help_tt(),
        "tn":help_tn(),
        "ls":help_ls(),
        "exit":help_exit()
    }
    s = switch.get(com)
    if s == None:
        return "Invalid Command"
    return s
    
def cd(path):
    """
    Change the current working directory 
    
    Parameters:
    path(String): Directory name to change to, OR '-' to go back to previous directory
    
    Returns:
    String: Current working directory (after change)
    """
    global cur_dir  #using global directory variables
    global prev_dir
    if path == None:
        return cur_dir
    if path == "-": #if going back a directory
        cur_dir = prev_dir #set current = to previous
        return cur_dir
    if system == "Windows":
        dir = cur_dir + "\\" + path
    else:
        dir = cur_dir + "/" + path
    if os.path.isdir(dir):
        prev_dir = cur_dir #set previous = to current
        os.chdir(dir) #change directory
        cur_dir = os.getcwd() #update current directory
        return cur_dir

def tt(filename):
    """
    Checks type of file based on line-endings. Windows = \r\n, UNIX = \n, Binary otherwise
    
    Parameters:
    filename(String): name of file being checked
    
    Returns:
    String: type of file: Windows, UNIX or Binary
    """
    global cur_dir
    if filename == None:
        return "File not Found"
    if system == "Windows":
        path = cur_dir + "\\" + filename
    else:
        path = cur_dir + "/" + filename 
    if os.path.isfile(path):
        file = open(path, "rb").read()
        if "\r\n" in file:
            return "Windows"
        elif "\n" in file:
            return "UNIX"
        else:
            return "Binary"
    else:
        return "File not Found"

def ls(pattern):
    """
    Displays the contents of the current directory. 
    
    Only display file/folder names that contain pattern if supplied.
    
    Parameters:
    pattern(String): pattern to search for entered by User
    
    Returns:
    list: list of files in the directory, OR 
          the list of files in the directory that match the pattern
    """
    global cur_dir
    list = ""
    entries = os.listdir(cur_dir)
    if pattern == None:
        for entry in entries:
            truncate = 0
            if system == "Windows":
                path = cur_dir + "\\" + entry
            else:
                path = cur_dir + "/" + entry 
            if os.path.isdir(path):
                if len(entry) > 78: #truncate at 78 for directories to account for []
                    truncate = len(entry) - 78
                list = list + "[" + str(entry[truncate:]) + "]" + "\n"
            elif os.path.isfile(path):
                if len(entry) > 80: #truncate at 80 for files
                    truncate = len(entry) - 80
                list = list + str(entry[truncate:]) + "\n"
        return list
    else:
        for entry in entries:
            truncate = 0
            if pattern in entry:
                if system == "Windows":
                    path = cur_dir + "\\" + entry 
                else:
                    path = cur_dir + "/" + entry
                if os.path.isdir(path):
                    if len(entry) > 78: #truncate at 78 for directories to account for []
                        truncate = len(entry) - 78
                    list = list + "[" + str(entry[truncate:]) + "]" + "\n"
                elif os.path.isfile(path):
                    if len(entry) > 80: #truncate at 80 for files
                        truncate = len(entry) - 80
                    list = list + str(entry[truncate:]) + "\n"    
        if list == "":
            return "No Files Found"
        else:
            return list        

def md5(filename):
    """
    Computes a hash of the files content
    
    Parameters:
    filename(String): name of file given by User
    
    Returns:
    String: hash of filename's content
    """
    global cur_dir
    import md5
    hash = md5.new()
    if filename == None:
        return "File not Given"
    else:
        if system == "Windows":
            path = cur_dir + "\\" + filename
        else:
            path = cur_dir+ "/" +filename
        if os.path.exists(path):
            f = open(filename, 'r')
            content = f.read()
            hash.update(content)
            return hash.hexdigest()
        else:
            return "File Not Found"
            
def find(pattern):
    """
    Recursively searches folders off current working directory to find files containing 'pattern'
    
    Parameters:
    pattern(String): pattern to search for 
    
    Returns:
    list: directories containing 'pattern'
    """
    global list 
    list = []
    if pattern == None:
        return "File(s) not Found"
    r = find_recurse(cur_dir,prev_dir,pattern)
    if len(r) == 0:
        return "File(s) not Found"
    else:
        for element in r:
            truncate = 0
            if len(element) > 80: #truncate at 80
                truncate = len(element) - 80
            print element[truncate:]
        return "File(s) Found"
    
def find_recurse(cd, pd, pattern):
    """
    Recursively searches all directories for the file name 
    
    Parameters:
    cd(String): variable to keep track of current directory during recursion 
    pd(String): variable to keep track of previous directory during recursion
    pattern(String): pattern to search for
    
    Returns:
    list: list of all directories containing the files searched for 
    """
    global list 
    entries = os.listdir(cd)
    for entry in entries:
        if system == "Windows":
            path = cd + "\\" + entry
        else:
            path = cd + "/" + entry 
        if os.path.isdir(path):
            os.chdir(path)
            pd = cd
            cd = os.getcwd()
            find_recurse(cd,pd,pattern)
            cd = pd
    for entry in entries:
        if pattern in entry:
            if system == "Windows":
                path = cd + "\\" + entry
            else:
                path = cd + "/" + entry 
            list.append(path)
    return list
    
def search(pattern):
    """
    Calls the recursive function to search directories for pattern
    
    Parameters:
    pattern(String): the string to search for in the files 
    
    Returns:
    String: Tells user whether files are found or not 
    """
    global list 
    list = []
    if pattern == None:
        return "File(s) not Found"
    r = search_recurse(cur_dir,prev_dir,pattern)
    if len(r) == 0:
        return "File(s) not Found"
    else:
        for element in r:
            truncate = 0 
            if len(element) > 80: #truncate at 80
                truncate = len(element) - 80
            print element[truncate:]
        return "File(s) Found"
    

def search_recurse(cd, pd, pattern):
    """
    Recursively searches all directories and checks all files for content matching pattern
    
    Parameters:
    cd(String): variable to keep track of current directory during recursion 
    pd(String): variable to keep track of previous directory during recursion
    pattern(String): pattern to search for

    Returns:
    list: list of directories with matching file/content 
    """
    global list 
    entries = os.listdir(cd)
    for entry in entries:
        if system == "Windows":
            path = cd + "\\" + entry
        else:
            path = cd + "/" + entry 
        if os.path.isdir(path):
            os.chdir(path)
            pd = cd
            cd = os.getcwd()
            search_recurse(cd,pd,pattern)
            cd = pd
    for entry in entries:
        if system == "Windows":
            path = cd + "\\" + entry
        else:
            path = cd + "/" + entry 
        if os.path.isfile(path):
            file = open(path, "rb").read()
            if pattern in file:
                list.append(path)
    return list

def head(filename, lines):
    """
    Displays first 'x' number of lines in a file (default 5).
    
    Truncates right side at 80 characters.
    
    Parameters:
    filename(String): file to read first 'x' number of lines
    lines(int): number of lines to read 
    
    Returns:
    list: list of all the first 'x' number of lines in filename
    """
    list = ""
    if filename == None:
        return "No File Given"
    if system == "Windows":
        path = cur_dir + "\\" + filename
    else:
        path = cur_dir + "/" + filename 
    if os.path.isfile(path):
        f = open(path, "rb")
        if lines == 0:
            while lines < 5:
                file = f.readline()
                list = list + file[:80] 
                lines = lines + 1
            return list 
        else:
            i = 0
            while i < lines:
                file = f.readline()
                list = list + file[:80]
                i = i + 1 
            return list
    else:
        return "File not Found"

def ln():
    """
    Displays name of all notes files (.notenote)
    
    Returns:
    list: list of all .notenote files in the directory
    """
    list = ""
    entries = os.listdir(cur_dir)
    for entry in entries:
        if ".notenote" in entry:
            e = entry.rpartition('.notenote') #partition from the right to remove file type
            list = list + e[0] + "\n"
    return list

def tn(notename):
    """
    Creates a notes file (notename), with users input. 
    
    Parameters:
    notename(String): name of file to be created
    
    Returns:
    String: Tells user the file was created
    """
    if notename == None:
        return "No filename given. Cannot create file."
    else:
        file = notename+".notenote"
        print "Enter as many lines as needed. Start a line with . to stop."
        i = True
        list = ""
        while i:
            input = raw_input()
            if input[0] == '.':
                i = False
                break;
            else:
                list = list + input + "\n"
        f = open(file,'w').write(list)
        return "File created."
        
def vn(notename):
    """
    Finds 'notename' in directory, reads contents and prints to display.
    
    Parameters:
    notename(String): name of file to read  
    
    Returns:
    String: contents of file: 'notename'.notenote
    """
    if notename == None:
        return "File not Found" 
    else:    
        file = notename + ".notenote"
        if system == "Windows":
            path = cur_dir + "\\" + file
        else:
            path = cur_dir + "/" + file
        entries = os.listdir(cur_dir)
        for entry in entries: 
            if entry == file: #if file found 
                f = open(path, "rb").readlines()
                x = 0 
                for line in f:
                    print line 
                    x += 1
                    if x % 20 == 0:
                        raw_input(":::>")   
                return "File Found"
        else:
            return "File not Found"

def run(scriptname):
    """
    Runs a script from a file (using these shell commands)
    
    Parameters:
    scriptname(String) name of the script to run 
    
    Returns:
    String: output of script 
    """
    if scriptname == None:
        return "File not Found"
    else:
        if system == "Windows":
            path = cur_dir + "\\" + scriptname + ".scriptscript"
        else:
            path = cur_dir + "/" + scriptname + ".scriptscript"
        if os.path.isfile(path):
            file = open(path,"rb").readlines()
            for f in file:
                arg_list = f.split()
                try:
                    arg = arg_list[0]
                except IndexError:
                    arg = None
                try:
                    com = arg_list[1]
                except IndexError:
                    com = None
                try:
                    lin = arg_list[2]
                except:
                    lin = None
                if arg == "exit":
                    break
                else:
                    shell(arg,com,lin)
                    return "Script Executed"
        else:
            return "File not Found"
    
def shell(argument, com, lines):
    """
    Runs the shell program, creates a library for all possible commands
    
    Parameters:
    argument(String): the first word given by the user
    com(String): the second word given by the user
    lines(int): number of lines to read in the case of 'head' command
    
    Returns:
    String: the result of user command
    """
    switcher = {
        "pwd":dir, #0
        "help":help, #1 
        "cd":cd, #1 
        "ls":ls,#1
        "md5":md5,#1
        "tt":tt,#1
        "find":find,#1
        "search":search,#0
        "head":head,#2
        "ln":ln,#0
        "tn":tn,#1
        "vn":vn,#1
        "run":run#1
    }
    b = False
    for s in switcher:
        func = ""
        if argument == s:
            b = True
            try:
                func = switcher[argument]()
            except TypeError:
                try:
                    func = switcher[argument](com)
                except TypeError:
                    try:
                        func = switcher[argument](com, lines)
                    except:
                        print "Incorrect Input"
            print func
    if argument == exit:
        return ""
    elif b == False:
        print "Incorrect Input"
    
#runs the shell
input = ""
#wait for user input until the user wishes to exit
while input != "exit":    
    num = 0
    input = raw_input(">")
    if input != "exit":
        arg_list = input.split()
        if len(arg_list) > 2:
            if arg_list[0] == "head":
                num = int(arg_list[2], base=10)
                try:
                    command = arg_list[1]
                except IndexError:
                    command = None
                shell(arg_list[0],command,num)
            else:
                print "Invalid Input"
        else:
            try:
                command = arg_list[1]
            except IndexError:
                command = None
            shell(arg_list[0],command,num)