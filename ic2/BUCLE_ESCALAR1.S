   .data 0x3e8
X: .double 1.0, 1.1, 1.2, 1.3, 1.4, 1.5, 1.6, 1.7, 1.8, 1.9   ; vector X
   .data
Y: .double 2.0, 2.1, 2.2, 2.3, 2.4, 2.5, 2.6, 2.7, 2.8, 2.9   ; vector Y

a: .double 3.14   ; variable a

   .text
main:
  ld    F0, a        ; carga variable a
  addi  R1, R0, X    ; carga direccion de comienzo de X
  addi  R2, R0, Y    ; carga direccion de comienzo de Y
  addi  R4, R1, 72   ; carga penultima posicion de X

inicio:
  ld    F2, 0(R1)    ; carga X(i)
  multd F4, F2, F0   ; multiplica a*X(i)
  ld    F6, 0(R2)    ; carga Y(i)
  addd  F6, F4, F6   ; suma a*X(i)+Y(i)
  sd    0(R2), F6    ; almacena Y(i)
  addi  R1, R1, 8    ; incrementa índice X
  addi  R2, R2, 8    ; incrementa índice Y
  sgt   R3, R1, R4   ; test por si finalizado
  beqz  R3, inicio   ; bucle si no finalizado
  trap  0