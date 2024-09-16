-- Implementación de la puerta lógica OR
-- de dos entradas.
-- fichero: or2.vhd

library ieee;
use ieee.std_logic_1164.all;

entity or2 is
	port	(y0     : out std_logic;
		 x0, x1 : in  std_logic);
end entity or2;

architecture or2_a of or2 is
begin
	y0 <= x0 or x1;
end architecture or2_a;