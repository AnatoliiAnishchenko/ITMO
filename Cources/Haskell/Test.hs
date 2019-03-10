module Test where

integration :: (Double -> Double) -> Double -> Double -> Double
integration f a b | a < b  = integration' f a b ((b - a) / 100000)
                  | a == b = 0
                  | a > b  = -(integration' f b a ((a - b) / 100000))
    where
		integration' f s e d | s > e     = 0
                             | otherwise = d * ((f s + f (s + d)) / 2) + integration' f (s + d) e d