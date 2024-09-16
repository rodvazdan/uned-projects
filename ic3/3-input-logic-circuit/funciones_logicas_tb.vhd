-- Banco de pruebas para el circuito que
-- implementa las funciones lógicas F y G.
-- fichero: funciones_logicas_tb.vhd

library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;

entity funciones_logicas_tb is
end entity funciones_logicas_tb;

architecture funciones_logicas_tb_a of funciones_logicas_tb is
	-- Circuito que está siendo probado
	component funciones_logicas is
		port	(f, g    : out std_logic;
			 	x, y, z  : in  std_logic);
	end component funciones_logicas;
	-- Conexiones del UUT
	signal f, g, x, y, z : std_logic;
begin
	uut : component funciones_logicas port map (f, g, x, y, z);
	-- Generar los vectores de test y comprobar el resultado
	gen_vec_test : process
		variable inputTest : std_logic_vector (2 downto 0) := "000";
		variable expectedOutput : std_logic_vector (1 downto 0);
		variable errorNumber : integer := 0;
	begin
		for i in 0 to 7 loop
			x <= inputTest(2); y <= inputTest(1); z <= inputTest(0);
			wait for 10 ns;
			expectedOutput(1) := (not x and z) or (x and y);
			expectedOutput(0) := (not x and not y) or (not x and z);
			if (expectedOutput(1) /= f or expectedOutput(0) /= g) then
				report "Error en el instante " & time'image(now) &
					": Se esperaba: " & std_logic'image(expectedOutput(1)) &
					std_logic'image(expectedOutput(0)) &
					"; Actual: " & std_logic'image(f) & std_logic'image(g);
				errorNumber := errorNumber + 1;
			end if;
			inputTest := std_logic_vector(unsigned(inputTest) + 1);
		end loop;
		if (errorNumber = 0) then
			report "Simulación finalizada sin errores";
		else
			report "Simulación finalizada. " & integer'image(errorNumber) & " error(es)";
		end if;
	end process gen_vec_test;
end architecture funciones_logicas_tb_a;