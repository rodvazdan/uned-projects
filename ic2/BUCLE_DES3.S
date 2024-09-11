   .data 0x3e8
X: .double 1.0, 1.1, 1.2, 1.3, 1.4, 1.5, 1.6, 1.7, 1.8, 1.9   ; vector X
   .data
Y: .double 2.0, 2.1, 2.2, 2.3, 2.4, 2.5, 2.6, 2.7, 2.8, 2.9   ; vector Y

a: .double 3.14   ; variable a

   .text
main:
  ld    F0, a           ; carga variable a
  addi  R1, R0, X       ; carga direccion de comienzo de X
  addi  R2, R0, Y       ; carga direccion de comienzo de Y
  addi  R4, R1, 72      ; carga penultima posicion de X
  addi  R5, R5, 10      ; numero de elementos de X

L:
  beqz  R5, E           ; bucle si quedan elementos por procesar
  ld    F2, 0(R1)       ; carga X(i)
  multd F4, F2, F0      ; multiplica a*X(i)
  ld    F6, 0(R2)       ; carga Y(i)
  addd  F6, F4, F6      ; suma a*X(i)+Y(i)
  sd    0(R2), F6       ; almacena Y(i)
  subi  R5, R5, 1       ; decrementa numero de elementos de X

  beqz  R5, E           ; bucle si quedan elementos por procesar
  ld    F8, 8(R1)       ; carga X(i)
  multd F10, F8, F0     ; multiplica a*X(i)
  ld    F12, 8(R2)      ; carga Y(i)
  addd  F14, F10, F12   ; suma a*X(i)+Y(i)
  sd    8(R2), F14      ; almacena Y(i)
  subi  R5, R5, 1       ; decrementa numero de elementos de X

  beqz  R5, E           ; bucle si quedan elementos por procesar
  ld    F16, 16(R1)     ; carga X(i)
  multd F18, F16, F0    ; multiplica a*X(i)
  ld    F20, 16(R2)     ; carga Y(i)
  addd  F22, F18, F20   ; suma a*X(i)+Y(i)
  sd    16(R2), F22     ; almacena Y(i)
  subi  R5, R5, 1       ; decrementa numero de elementos de X

  beqz  R5, E           ; bucle si quedan elementos por procesar
  ld    F24, 24(R1)     ; carga X(i)
  multd F26, F24, F0    ; multiplica a*X(i)
  ld    F28, 24(R2)     ; carga Y(i)
  addd  F30, F26, F28   ; suma a*X(i)+Y(i)
  sd    24(R2), F30     ; almacena Y(i)
  subi  R5, R5, 1       ; decrementa numero de elementos de X

  addi  R1, R1, 32      ; incrementa índice X
  addi  R2, R2, 32      ; incrementa índice Y

  j     L

E:
  trap  0