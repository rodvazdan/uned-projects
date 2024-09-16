-- Descripción del comportamiento del
-- circuito para las funciones lógicas F y G.
-- fichero: funciones_logicas_a1.vhd

library ieee;
use ieee.std_logic_1164.all;

architecture funciones_logicas_a1 of funciones_logicas is
	signal s0, s1, s2, s3, s4 : std_logic;
begin
	s0 <= not x and not y and not z;
	s1 <= not x and not y and z;
	s2 <= not x and y and z;
	s3 <= x and y and not z;
	s4 <= x and y and z;
	f  <= s1 or s2 or s3 or s4;
	g  <= s0 or s1 or s2;
end architecture funciones_logicas_a1;