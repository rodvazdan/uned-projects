-- Implementación del circuito sumador de
-- dos números con signo de N bits con
-- acarreo de entrada.
-- fichero: sumador_completo.vhd

library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;

entity sumador_completo is
	generic (n : positive);
	port 	(res : out signed (n-1 downto 0);
		     desbordamiento, cero, signo : out std_logic;
			 a, b : in signed (n-1 downto 0);
			 cin  : in std_logic);
end entity sumador_completo;

architecture sumador_completo_a of sumador_completo is
	signal result, cin_vector : signed (n-1 downto 0) := (others => '0');
	signal desbor_vector, cero_vector : std_logic_vector (n-1 downto 0) := (others => '0');
	signal desbor : std_logic := '0';
begin
	cin_vector(0) <= cin;
	result <= a + b + cin_vector;
    res <= result;
	desbor <= (not a(a'high) and not b(b'high) and result(result'high)) or (a(a'high) and b(b'high) not result(result'high));
	desbordamiento <= desbor; 
	desbor_vector(0) <= desbor;
	cero_vector <= not std_logic_vector(result) and not desbor_vector;
	cero <= cero_vector(0);
	signo <= result(result'high) xor desbor;
end architecture sumador_completo_a;