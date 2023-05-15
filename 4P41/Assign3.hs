{- 
    Question 1 (10 marks, 1 mark for each of a)-d), 2 marks for each e)-g)):
    In this question you are implementing the abstract data type of a stack.
    a)  Define a NEW data type Stack a of stacks of elements from a. You may use a list
        to store the elements internally. You can use either construction "data" or "newtype".
        Hint: Derive Show for stacks for testing purposes.
    b)  Define the empty stack "empty" of type Stack a.
    c)  Write a function isEmpty :: Stack a -> Bool that returns true if the stack is empty and
        false otherwise. Use pattern matching and/or the function null (see above) and NOT ==,
        i.e., isEmpty should not use the constraint Eq a => ...
    d)  Write a function push that pushes an element to a stack (The new stack is returned by this
        function).
    e)  Write a function pop :: Stack a -> Maybe (a,Stack a) that returns the top element and the
        stack without the top element as an element of Maybe (a,Stack a) if the stack is not empty.
        The function should return Nothing if the stack is empty.
    f)  Write a function peek :: Stack a -> Maybe a that returns the top element of the stack as an
        element of Maybe a if the stack is not empty. The function should return Nothing if the
        stack is empty.
    g)  Write a function pop2 :: Stack a -> Maybe ((a,a),Stack a) that pops a stack twice and returns
        the pair of the two top elements and the remaining stack as an element of type 
        Maybe ((a,a),Stack a).
        Hint: Use the construction
              case pop st of
                Nothing     -> Nothing
                Just (x,st) -> ...
              twice in order to access the results of popping a stack.
-}

data Stack a = Stack [a] deriving Show 

empty :: Stack a 
empty = Stack [] 

isEmpty :: Stack a -> Bool 
isEmpty (Stack s) = null s 

push :: Stack a -> a -> Stack a 
push (Stack xs) x = Stack (x:xs)

pop :: Stack a -> Maybe (a, Stack a) 
pop (Stack []) = Nothing                -- return nothing if empty
pop (Stack (x:xs)) = Just (x, Stack xs) -- return just the head 

peek :: Stack a -> Maybe a
peek empty       = Nothing  -- empty stack, return nothing 
peek (Stack (x:xs)) = Just x -- otherwise return just the head of the list 

pop2 :: Stack a -> Maybe ((a,a), Stack a)
pop2 (Stack []) = Nothing -- empty stack, return nothing 
pop2 (Stack [x]) = Nothing -- need 2 elements, if theres only 1 return nothing  
pop2 (Stack (x:y:xs)) = Just ((x,y), Stack xs) -- return Just the first 2 elements x and y

{- 
    Question 2 (10 marks, 5 marks for each a) and b)):
    In this question you are going to implement a stack machine using the stack data type from the
    previous question. The type SMInst (see below) is an enumerated type of stack machine
    instructions with the elements PUSH n, ADD, MINUS, MULT, DIV. The type synonym SMProg (see below)
    is the type of stack machine programs, i.e., lists of SMInst's.
    a)  Define a function evalSMInst :: Stack Int -> SMInst -> Maybe (Stack Int) that executes a 
        SMInst on a given stack. The instruction PUSH n simply pushes n to the stack. All other operations pop the two top elements from stack, apply the corresponding operation to these elements, and push the result back to the stack. Please note that the result of evalSMInst
        is of type Maybe (Stack Int), i.e., the function returns Nothing if any of the operations 
        fail.
        Hint: Use the function pop2 from Question 1(g) and the construction
              case pop2 st of
                Nothing         -> Nothing
                Just ((x,y),st) -> ...
    b)  Define a function evalSMProg :: Stack Int -> SMProg -> Maybe (Stack Int) that executes a
        program on the given stack. Use a) and pattern matching to implement this.
        Hint: Use the function evalSMInst from Question a) and the construction
              case evalSMInst st i of
                Nothing  -> Nothing
                Just st1 -> ...
        Examples: In the first example St is the constructor of my implementation of the type 
            Stack a. You may have to replace this by your constructor.
                evalSMProg empty exampleProg1 == Just (St [27])
                evalSMProg empty exampleProg2 == Nothing
-}

--data NOp = Succ | Add | Mult | If deriving (Eq, Show)

--executeNOp :: NOp -> [Int] -> Int
--executeNOp Succ [n]     = n+1
--executeNOp Add  [m,n]   = m+n
--executeNOp Mult [m,n]   = m*n
--executeNOp If   [m,n,p] = if m==0 then n else p

data SMInst = PUSH Int | ADD | MINUS | MULT | DIV deriving Show

type SMProg = [SMInst]

exampleProg1 :: SMProg
exampleProg1 = [PUSH 3, PUSH 4, PUSH 5, ADD, MULT]

exampleProg2 :: SMProg
exampleProg2 = [PUSH 3, PUSH 4, ADD, DIV]
                        
evalSMInst :: Stack Int -> SMInst -> Maybe (Stack Int) 
evalSMInst (Stack s) (PUSH n)       = Just (push (Stack s) n)
evalSMInst (Stack s) ADD            = case pop2 (Stack s) of Nothing -> Nothing
                                                             Just ((x,y), s) -> Just (push s (x+y))   
evalSMInst (Stack s) MINUS          = case pop2 (Stack s) of Nothing -> Nothing
                                                             Just ((x,y), s) -> Just (push s (x-y))
evalSMInst (Stack s) MULT           = case pop2 (Stack s) of Nothing -> Nothing
                                                             Just ((x,y), s) -> Just (push s (x*y))
evalSMInst (Stack s) DIV            = case pop2 (Stack s) of Nothing -> Nothing
                                                             Just ((x,y), s) -> Just (push s (div x y))   

evalSMProg :: Stack Int -> SMProg -> Maybe (Stack Int)
evalSMProg (Stack s) (x:xs)        = case evalSMInst (Stack s) i of Nothing -> Nothing                                   