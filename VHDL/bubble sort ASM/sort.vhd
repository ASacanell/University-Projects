----------------------------------------------------------------------------------
-- Company:        Universidad Complutense de Madrid
-- Engineer:       TOC&TC
-- 
-- Create Date:    
-- Design Name:    sort
-- Module Name:    sort - estructural
-- Project Name:   Practica 5
-- Target Devices: Spartan-3 
-- Tool versions:  ISE 14.1
-- Description:    Ordenación de los contenidos de una memoria de doble puerto
--                 mediante el metodo de la burbuja
-- Dependencies: 
-- Revision: 
-- Additional Comments: 
--
----------------------------------------------------------------------------------
library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;


entity sort is
  port (clk        : in  std_logic;
        rst_n      : in  std_logic;
        ini        : in  std_logic;
        fin        : out std_logic;
        debug_addr : in  std_logic_vector(4 downto 0);
        debug_data  : in  std_logic_vector(3 downto 0);
        debug_we   : in  std_logic;
        debug_out : out std_logic_vector(3 downto 0)
        );
end sort;

architecture struct of sort is

  component cd is
    port (
      clk        : in  std_logic;                      -- clock
      rst_n      : in  std_logic;                      -- asynch active low reset
      ctrl       : in  std_logic_vector(6 downto 0);   -- Control
      status     : out std_logic_vector(2 downto 0);   -- Status
      debug_addr : in  std_logic_vector(4 downto 0);   -- Address for debugging
      debug_we   : in  std_logic;                      -- Write enable for debugging
      debug_data  : in  std_logic_vector(3 downto 0);   -- Data for debugging
      debug_out : out std_logic_vector(3 downto 0));  -- Memory output during debugging
  end component cd;

  component uc is
    port (
      clk    : in  std_logic;                      -- clock
      rst_n  : in  std_logic;                      -- reset
      ini    : in  std_logic;                      -- External control signal
      fin    : out std_logic;                      -- External control signal
      ctrl   : out std_logic_vector(6 downto 0);   -- Control vector
      status : in  std_logic_vector(2 downto 0));  -- Status vector
  end component uc;

  signal ctrl   : std_logic_vector(6 downto 0);    -- Control vector
  signal status : std_logic_vector(2 downto 0);    -- Status vector
begin

  i_cd : cd port map (
    clk        => clk,
    rst_n      => rst_n,
    ctrl       => ctrl,
    status     => status,
    debug_we   => debug_we,
    debug_addr => debug_addr,
    debug_data  => debug_data,
    debug_out => debug_out);

  i_uc : uc port map (
    clk    => clk,
    rst_n  => rst_n,
    ini    => ini,
    fin    => fin,
    ctrl   => ctrl,
    status => status);

end struct;
