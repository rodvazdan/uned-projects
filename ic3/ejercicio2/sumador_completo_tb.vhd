-- Banco de pruebas para el circuito
-- sumador de dos números con signo de
-- N bits con acarreo de entrada.
-- fichero: sumador_completo_tb.vhd

library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;

entity sumador_completo_tb is
	constant n : positive;
end entity sumador_completo_tb;

architecture sumador_completo_tb_a of sumador_completo is
	-- Entradas y salidas del UUT
	signal a_i, b_i, cin_vector, res_o : signed (n-1 downto 0) := (others => '0');
	signal desbor_o, cero_o, signo_o, cin_i : std_logic := '0';
	-- Circuito que va a ser probado
	component sumador_completo is
		generic (n : positive);
		port 	(res : out signed (n-1 downto 0);
				 desbordamiento, cero, signo : out std_logic;
				 a, b : in signed (n-1 downto 0);
				 cin  : in std_logic);
	end component sumador_completo;
begin
	uut : component sumador_completo
			generic map (n => n)
			port map    (res_o, desbor_o, cero_o, signo_o, a_i, b_i, cin_i);

	-- Generar los vectores de test y comprobar el resultado
	gen_test_vector : process
		constant zero : std_logic_vector (n-1 downto 0) := (others => '0');
		variable errorNumber : integer := 0;
		variable expectedDesbor, expectedCero, expectedSigno : std_logic := '0';
		variable expectedResult : signed (n-1 downto 0) := (others => '0');
		variable inputTest : signed (n*2 downto 0) := (others => '0');
	begin
		for count in 0 to 2**n loop
			a_i <= inputTest(n*2 downto n*2-(n-1));	   -- n bits para el primer operando	
			b_i <= inputTest(n*2-n downto n*2-n-(n-1));  -- n bits para el segundo operando
			cin_i <= inputTest(0);
			cin_vector(0) <= cin_i;
			wait for 100 ns;
			expectedResult := a_i + b_i + cin_vector;
			expectedDesbor := (not a_i(a_i'high) and not b_i(b_i'high) and result(result'high)) or (a_i(a_i'high) and b_i(b_i'high) and not result(result'high));
			expectedSigno  := expectedResult(expectedResult'high) xor expectedDesbor;
			if (to_integer(expectedResult) = to_integer(signed(zero))) then
				expectedCero := '1';
			else
				expectedCero := '0';
			end if;

			-- Comprobar la señal de salida 'res'
			if (expectedResult /= res_o) then
				report "ERROR de 'res' en el instante " & time'image(now) &
				": Se esperaba " &  integer'image(to_integer(expectedResult)) &
				"; Actual es " & integer'image(to_integer(res_o));
				errorNumber := errorNumber + 1;
			end if;
			-- Comprobar la señal de salida 'desbor'
			if (expectedDesbor /= desbor) then
				report "ERROR de 'desbor' en el instante " & time'image(now) &
				": Se esperaba " &  std_logic'image(expectedDesbor) &
				"; Actual es " & std_logic'image(desbor);
				errorNumber := errorNumber + 1;
			end if;
			-- Comprobar la señal de salida 'cero'
			if (expectedCero /= cero_o) then
				report "ERROR de 'cero' en el instante " & time'image(now) &
				": Se esperaba " &  std_logic'image(expectedCero) &
				"; Actual es " & std_logic'image(cero_o);
				errorNumber := errorNumber + 1;
			end if;
			-- Comprobar la señal de salida 'signo'
			if (expectedSigno /= signo_o) then
				report "ERROR de 'signo' en el instante " & time'image(now) &
				": Se esperaba " &  std_logic'image(expectedSigno) &
				"; Actual es " & std_logic'image(signo_o);
				errorNumber := errorNumber + 1;
			end if;
			inputTest := inputTest + 1;
		end loop;
		report "Simulación finalizada con " & integer'image(errorNumber) & " error(es)";
	end process gen_test_vector;
end architecture sumador_completo_tb_a;