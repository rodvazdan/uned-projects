-- Interfaz del circuito que implementa
-- las funciones lógicas F y G.
-- fichero: funciones_logicas.vhd

library ieee;
use ieee.std_logic_1164.all;

entity funciones_logicas is
	port	(f, g    : out std_logic;
		 x, y, z : in  std_logic);
end entity funciones_logicas;