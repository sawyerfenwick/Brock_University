-- Exercise

-- Define a function max3 :: Int -> Int -> Int -> (Int,Int,Int) that
-- orders the three inputs, e.g., max3 2 1 4 == (1,2,4) and 
-- max3 5 1 3 == (1,3,5)

max3 :: Int -> Int -> Int -> (Int,Int,Int)
max3 x y z      | x <= y && x <= z  = if y <= z then (x,y,z) else (x,z,y)
                | y <= x && x <= z  = if x <= z then (y,x,z) else (y,z,x)
                | otherwise         = if x <= y then (z,x,y) else (z,y,x)
--max3 = error "not yet implemented"

-- Write a function isSorted using recursion and pattern matching
-- that checks whether a given list of integers is sorted, e.g.
-- isSorted [1,2,3,3,5,6] == True and isSorted [1,2,4,3,5,6] == False

isSorted :: [Int] -> Bool
isSorted [] = True 
isSorted [x] = True 
isSorted (x:y:xs)   | x <= y        = isSorted (y:xs) 
                    | otherwise     = False 
--isSorted = error "not yet implemented"

-- A triple (x,y,z) of positive integers is called pythagorenian if
-- x^2 + y^2 == z^2. Using list comprehension, define a function 
-- pyths :: Int -> [(Int,Int,Int)] that returns the list of all 
-- pythagorenian triples whose components are less or equal the given
-- limit, e.g., pyths 10 == [(3,4,5),(4,3,5),(6,8,10),(8,6,10)]

pyths :: Int -> [(Int,Int,Int)]
pyths n = [(x,y,z) | x <- [1..n], y <- [1..n], z <- [1..n], x^2 + y^2 == z^2]
--pyths = error "not yet implemented"

-- Write a function sqrAll1 :: [Int] -> [Int] that squares all numbers in
-- a list using list comprehesion, e.g., sqrAll1 [1,2,3] == [1,4,9]

sqrAll1 :: [Int] -> [Int]
sqrAll1 list = [n^2 | n <- list]
--sqrAll1 = error "not yet implemented"

-- Rewrite the function of the previous question using map.
-- Call this definition sqrAll2.

sqrAll2 :: [Int] -> [Int]
sqrAll2 = map (^2)
--sqrAll2 = error "not yet implemented"

-- Write a function noMultiples1 using recursion and pattern matching
-- that returns for a given integer x and list l the list of all elements 
-- in l that are not multiples of x, e.g., noMultiples1 1 [1,2,4,3,5,6]
-- == [] and noMultiples1 2 [1,2,4,3,5,6] == [1,3,5]

noMultiples1 :: Int -> [Int] -> [Int]
noMultiples1 x [] = [] 
noMultiples1 x (y:ys) | mod y x == 0    = noMultiples1 x ys 
                      | otherwise       = y:noMultiples1 x ys 
--noMultiples1 = error "not yet implemented"
                      
-- Rewrite the function of the previous question using list comprehension.
-- Call this definition noMultiples2.
              
noMultiples2 :: Int -> [Int] -> [Int]
noMultiples2 x l = [n | n <- l, mod n x /= 0]
--noMultiples2 = error "not yet implemented"

-- Rewrite the function of the previous question using foldr. Call this 
-- definition noMultiples3.

noMultiples3 :: Int -> [Int] -> [Int]
noMultiples3 x = foldr (\y r -> if y `mod` x == 0 then r else y:r) []
--noMultiples3 = error "not yet implemented"

-- Write a function sub n l that subtracts all values from the list l from n
-- using foldl, e.g., sub 10 [1,2,3] == 4

sub :: Int -> [Int] -> Int
sub = foldl (-)
--sub = error "not yet implemented"

-- Write a function dec2Int :: [Int] -> Int that converts a list of digits 
-- (Int value from 0 to 9) into its corresponding Int using foldl, e.g., 
-- dec2Int [2,3,4,5] == 2345

dec2Int :: [Int] -> Int
dec2Int = foldl (\r d -> 10*r+d) 0
--dec2Int = error "not yet implemented"

-- Write a function app that appends two lists using foldr.

app :: [a] -> [a] -> [a]
app xs ys = foldr (:) ys xs  
--app = error "not yet implemented"

what = foldr (:) [1,2,3] [4,5,6]
multipleOf = foldl (flip(:)) [1,2,3] (reverse[4,5,6])