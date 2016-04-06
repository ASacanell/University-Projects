----------------------------------------------------------------------------------
-- Company:        Universidad Complutense de Madrid
-- Engineer:       TOC&TC
-- 
-- Create Date:    
-- Design Name:    sort
-- Module Name:    uc - rtl 
-- Project Name:   Practica 5
-- Target Devices: Spartan-3 
-- Tool versions:  ISE 14.1
-- Description:    Unidad control de ASM ordenamiento burbuja
-- Dependencies: 
-- Revision: 
-- Additional Comments: 
--
----------------------------------------------------------------------------------
library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;

entity uc is
  port (
    clk    : in  std_logic;                      -- clock
    rst_n  : in  std_logic;                      -- reset
    ini    : in  std_logic;                      -- External control signal
    fin    : out std_logic;                      -- External control signal
    ctrl   : out std_logic_vector(6 downto 0);   -- Control vector
    status : in  std_logic_vector(2 downto 0));  -- Status vector
end entity uc;

architecture rtl of uc is
  type t_st is (s0, s1, s2, s3, s4, s5, s6, s7, s8, s9);
  signal current_state, next_state : t_st;
  -- signal cmp_i, cmp_j, cmp_mem     : std_logic; 

begin

  p_next_state : process (current_state, status, ini) is
    variable cmp_i   : std_logic;       -- Senal de estado del comparador cntri
    variable cmp_j   : std_logic;       -- Senal de estado del comparador cntrj
    variable cmp_mem : std_logic;       -- Senal de estado del comparador mem
    begin
    (cmp_i,
     cmp_j,
     cmp_mem) := status;

    case current_state is
      when s0 =>
        if ini = '1' then
		     next_state <= s1;
		  else
		     next_state <= current_state;
		  end if;
      when s1 =>
        next_state <= s2;
      when s2 =>
        if cmp_i = '1' then
		     next_state <= s3;
		  else
		     next_state <= s0;
		  end if;
      when s3 =>
        next_state <= s4;
      when s4 =>
        if cmp_j = '1' then
		     next_state <= s5;
		  else
		     next_state <= s9;
		  end if;
      when s5 =>
        next_state <= s6;
      when s6 =>
        if cmp_mem = '1' then
		     next_state <= s7;
		  else
		     next_state <= s8;
		  end if;
      when s7 =>
        next_state <= s8;
      when s8 =>
        next_state <= s4;
      when s9 =>
        next_state <= s2;
      when others => null;
    end case;
  end process p_next_state;

  p_outputs : process (current_state)
    constant c_cntri_ld : std_logic_vector(6 downto 0) := "0000001";  
    constant c_cntri_cu : std_logic_vector(6 downto 0) := "0000010";
	 constant c_cntrj_ld : std_logic_vector(6 downto 0) := "0000100";
	 constant c_cntrj_cu : std_logic_vector(6 downto 0) := "0001000";
    constant c_wea : std_logic_vector(6 downto 0) := "0010000";
    constant c_web : std_logic_vector(6 downto 0) := "0100000";
    constant c_debug_mode : std_logic_vector(6 downto 0) := "1000000";
	 constant c_zero : std_logic_vector(6 downto 0) := (others => '0');

  begin
    ctrl <= (others => '0');
    case current_state is
      when s0 =>
        ctrl <= c_debug_mode;
        fin  <= '1';
      when s1 =>
        ctrl <= c_cntri_ld;
        fin  <= '0';
      when s2 =>
        ctrl <= c_zero;
        fin  <= '0';
      when s3 =>
        ctrl <= c_cntrj_ld;
        fin  <= '0';
      when s4 =>
        ctrl <= c_zero;
        fin  <= '0';
      when s5 =>
        ctrl <=c_zero;
        fin  <= '0';
      when s6 =>
        ctrl <= c_zero;
        fin  <= '0';
      when s7 =>
        ctrl <= c_web OR c_wea;
        fin  <= '0';
      when s8 =>
        ctrl <= c_cntrj_cu;
        fin  <= '0';
      when s9 =>
        ctrl <= c_cntri_cu;
        fin  <= '0';
      when others => null;
    end case;
  end process p_outputs;

  p_status_reg : process (clk, rst_n) is
  begin
    if rst_n = '0' then
      current_state <= s0;
    elsif rising_edge(clk) then
      current_state <= next_state;
    end if;
  end process p_status_reg;

end architecture rtl;
