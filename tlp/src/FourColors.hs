module FourColors where
---------------------------------
--- PRIMERA FASE: ADJACENCIES ---
---------------------------------

--- Tipos de datos
type Zone      = Int
type Row       = [Zone]
type Map       = [Row]
type Adjacency = (Zone,[Zone])

--- Función adjacency
--- Obtiene todas las adyacencias del mapa
adjacencies :: Map -> [Adjacency]
adjacencies map = adjacenciesAux map []

--- Función auxiliar adjacenciesAux
--- Obtiene todas las adyacencias del mapa mediante recursión
adjacenciesAux :: Map -> [Adjacency] -> [Adjacency]
adjacenciesAux (x:[]) list = compareAD x [] list
adjacenciesAux (x:xs:xss) list = adjacenciesAux (xs:xss) (compareAD x xs list)

--- Función auxiliar compareAD
--- Compara distintas zonas para determinar si hay adyacencias entre ellas
compareAD :: [Zone] -> [Zone] -> [Adjacency] -> [Adjacency]
compareAD (x:[]) [] list = list
compareAD (x:xs:xss) [] list
  | x < xs    = compareAD (xs:xss) [] (insertAD x xs list)
  | x > xs    = compareAD (xs:xss) [] (insertAD xs x list)
  | otherwise = compareAD (xs:xss) [] list
compareAD (x:[]) (y:[]) list
  | x < y     = insertAD x y list
  | x > y     = insertAD y x list
  | otherwise = list
compareAD (x:xs:xss) (y:ys) list
  | x < y && x < xs && y == xs = compareAD (xs:xss) ys (insertAD x y list)
  | x < y && x < xs && y < xs  = compareAD (xs:xss) ys (insertAD x y (insertAD x xs list))
  | x < y && x < xs && y > xs  = compareAD (xs:xss) ys (insertAD x xs (insertAD x y list))
  | x > y && x < xs            = compareAD (xs:xss) ys (insertAD y x (insertAD x xs list))
  | x < y && x > xs            = compareAD (xs:xss) ys (insertAD xs x (insertAD x y list))
  | x > y && x > xs && y == xs = compareAD (xs:xss) ys (insertAD y x list)
  | x > y && x > xs && y < xs  = compareAD (xs:xss) ys (insertAD y x (insertAD xs x list))
  | x > y && x > xs && y > xs  = compareAD (xs:xss) ys (insertAD xs x (insertAD y x list))
  | x < y && x == xs           = compareAD (xs:xss) ys (insertAD x y list)
  | x > y && x == xs           = compareAD (xs:xss) ys (insertAD y x list)
  | x == y && x < xs           = compareAD (xs:xss) ys (insertAD x xs list)
  | x == y && x > xs           = compareAD (xs:xss) ys (insertAD xs x list)
  | otherwise                  = compareAD (xs:xss) ys list

--- Función auxiliar insert
--- Inserta una nueva adyacencia
insertAD :: Zone -> Zone -> [Adjacency] -> [Adjacency]
insertAD x y [] = [(y,[x])]
insertAD x y [(zone,adj)]
  | y == zone && comparison == False = [(zone,(insertZN x adj))]
  | y == zone && comparison == True =  [(zone,adj)]
  | y  < zone = (y,[x]) : [(zone,adj)]
  | otherwise = (zone,adj) : [(y,[x])]
  where comparison = compareZN x adj
insertAD x y ((zone,adj):zs)
  | y == zone && comparison == False = ((zone,(insertZN x adj)):zs)
  | y == zone && comparison == True =  ((zone,adj):zs)
  | y  < zone = (y,[x]) : ((zone,adj):zs)
  | otherwise = (zone,adj) : insertAD x y zs
  where comparison = compareZN x adj

--- Función auxiliar compareZN
--- Comprueba si una zona ya ha sido añadida a una lista de zonas adyacentes
compareZN :: Zone -> [Zone] -> Bool
compareZN zone (a:as)
  | zone == a = True
  | zone /= a && length (a:as) > 1 = compareZN zone as
  | otherwise = False

--- Función auxiliar insertZN
--- Inserta una zona en una lista de zonas adyacentes
insertZN :: Zone -> [Zone] -> [Zone]
insertZN zone (a:[])
  | zone < a  = zone : [a]
  | otherwise = a : [zone]
insertZN zone (a:as)
  | zone < a  = zone : (a:as)
  | otherwise = a : insertZN zone as

---------------------------
--- SEGUNDA FASE: COLOR ---
---------------------------

--- Tipos de datos
data Color    = Red | Green | Blue | Yellow
  deriving (Enum,Eq,Show)
type Solution = [Color]
type Node     = ([Adjacency],Zone,Zone,Solution)

--- Función color
--- Busca una solución para colorear un mapa con un máximo de cuatro colores
---   -Si existe solución, devuelve un String con la solución
---   -Si no existe solución, devuelve un String indicando que no existe
color :: Map -> String
color map = takeFirst (solution map)
  where --- Función takeFirst
        --- Devuelve la solución contenida en el primer elemento de una lista de nodos solución
        --- Si la lista es vacía, devuelve una cadena indicando que no hay soluciones
        takeFirst :: [Node] -> String
        takeFirst []             = "No solution!\n"
        takeFirst ((_,_,_,c):ss) = "Solution: " ++ (show c) ++ "\n"

--- Función solution
--- Devuelve TODOS los nodos solución del problema
solution :: Map -> [Node]
solution map = bt esSol comp (initialNode map)

--- Función del esquema de Backtracking
--- Esta función aplica el esquema de Backtracking a cualquier problema
bt :: (a -> Bool) -> (a -> [a]) -> a -> [a]
bt esSol comp node
  | esSol node = [node]
  | otherwise  = concat (map (bt esSol comp) (comp node))

--- Función colorList
--- Devuelve la enumeración de todos los elementos del tipo Color
colorList :: [Color]
colorList = [toEnum 0::Color ..]

--- Función esSol
--- Comprueba si un nodo es solución o no
esSol :: Node -> Bool
esSol (_, len, zone, sol) = length sol == len && zone == len + 1

--- Función comp
--- Calcula las compleciones del nodo actual coloreando la zona actual con los colores utilizables
comp :: Node -> [Node]
comp ([], len, zone, sol) = compAux ([], len, zone, sol) colorList
comp (((z,adj):zs), len, zone, sol)
  | z /= zone = compAux (((z,adj):zs), len, zone, sol) colorList
  | otherwise = compAux (zs, len, zone, sol) avColors
  where avColors = availableColors (usedColors adj sol)

--- Función auxiliar compAux
--- Calcula las compleciones del nodo actual coloreando la zona actual con los colores utilizables
compAux :: Node -> [Color] -> [Node]
compAux (_, len, zone, sol) [] = []
compAux ([], len, zone, sol) (c:cs) = ([], len, zone + 1, sol ++ [c]) : compAux ([], len, zone, sol) cs
compAux ([(z,adj)], len, zone, sol) (c:cs)
  | z /= zone = ([(z,adj)], len, zone + 1, sol ++ [c]) : compAux ([(z,adj)], len, zone, sol) cs
  | otherwise = ([], len, zone + 1, sol ++ [c]) : compAux ([(z,adj)], len, zone, sol) cs
compAux (((z,adj):zs), len, zone, sol) (c:cs)
  | z /= zone = (((z,adj):zs), len, zone + 1, sol ++ [c]) : compAux (((z,adj):zs), len, zone, sol) cs
  | otherwise = (zs, len, zone + 1, sol ++ [c]) : compAux (((z,adj):zs), len, zone, sol) cs

--- Función auxiliar usedColors
--- Calcula los colores ya usados
usedColors :: [Zone] -> [Color] -> [Color]
usedColors _ [] = []
usedColors (z:[]) cs = [cs !! (z - 1)]
usedColors (z:zs) cs = (cs !! (z - 1)) : usedColors zs cs

--- Función auxiliar availableColors
--- Calcula qué colores pueden usarse dada una lista de colores ya usados
availableColors :: [Color] -> [Color]
availableColors [] = colorList
availableColors cs = (canBeUseRed cs) ++ (canBeUseGreen cs) ++ (canBeUseBlue cs) ++ (canBeUseYellow cs)
  where
    --- Función canBeUseRed
    canBeUseRed (c:[])
      | c == Red  = []
      | otherwise = [Red]
    canBeUseRed (c:cs)
      | c == Red  = []
      | otherwise = canBeUseRed cs
    --- Función canBeUseGreen
    canBeUseGreen (c:[])
      | c == Green = []
      | otherwise  = [Green]
    canBeUseGreen (c:cs)
      | c == Green = []
      | otherwise  = canBeUseGreen cs
    --- Función canBeUseBlue
    canBeUseBlue (c:[])
      | c == Blue = []
      | otherwise = [Blue]
    canBeUseBlue (c:cs)
      | c == Blue = []
      | otherwise = canBeUseBlue cs
    --- Función canBeUseYellow
    canBeUseYellow (c:[])
      | c == Yellow = []
      | otherwise   = [Yellow]
    canBeUseYellow (c:cs)
      | c == Yellow = []
      | otherwise   = canBeUseYellow cs

--- Función initialNode
--- Construye el nodo inicial para comenzar el Backtracking
initialNode :: Map -> Node
initialNode map = (adj, len, 1, [])
  where adj = adjacencies map
        len = if adj == [] then 1 else fst (last adj)