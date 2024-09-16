-- Descripción de la estructura del
-- circuito para las funciones lógicas F y G.

library ieee;
use ieee.std_logic_1164.all;

architecture funciones_logicas_a2 of funciones_logicas is
	signal s0, s1, s2, s3, s4, s5 : std_logic;
	-- Puerta lógica NOT
	component not1 is
		port	(y0 : out std_logic;
			 x0 : in  std_logic);
	end component not1;
	-- Puerta lógica AND de dos entradas
	component and2 is
		port	(y0     : out std_logic;
			 x0, x1 : in  std_logic);
	end component and2;
	-- Puerta lógica OR de dos entradas
	component or2 is
		port	(y0     : out std_logic;
			 x0, x1 : in  std_logic);
	end component or2;
begin
	n1 : not1 port map (x0 => x,  y0 => s0);
	n2 : not1 port map (x0 => y,  y0 => s1);
	a1 : and2 port map (x0 => x,  x1 => y,  y0 => s2);
	a2 : and2 port map (x0 => s0, x1 => z,  y0 => s3);
	a3 : and2 port map (x0 => s0, x1 => s1, y0 => s4);
	o1 : or2  port map (x0 => s2, x1 => s3, y0 => f);
	o2 : or2  port map (x0 => s3, x1 => s4, y0 => g);
end architecture funciones_logicas_a2;