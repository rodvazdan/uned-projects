-- Implementación de la puerta lógica AND
-- de dos entradas.
-- fichero: and2.vhd

library ieee;
use ieee.std_logic_1164.all;

entity and2 is
	port	(y0     : out std_logic;
		 x0, x1 : in  std_logic);
end entity and2;

architecture and2_a of and2 is
begin
	y0 <= x0 and x1;
end architecture and2_a;