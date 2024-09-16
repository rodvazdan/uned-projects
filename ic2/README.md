# DAXPY Implementation in DLXV Assembly

This project implements various versions of the DAXPY (Double-precision A times X Plus Y) loop, part of the LINPACK benchmark, for the DLXV processor, using the WinDLXV simulator.

The loop implements the vector operation $Y := Y + a \cdot X$ for a vector of length $\frac{R4}{8}$ elements. `R4` contains the length in bytes of the total number of elements, with each element being 8 bytes long (double precision). The scalar assembly loop code for the DLXV processor is as follows:

```
inicio: LD    F2,0(R1)      ; load X(i)
        MULTD F4,F2,F0      ; multiply a*X(i)
        LD    F6,0(R2)      ; load Y(i)
        ADDD  F6,F4,F6      ; add a*X(i)+Y(i)
        SD    0(R2),F6      ; store Y(i)
        ADDI  R1,R1,8       ; increment X index
        ADDI  R2,R2,8       ; increment Y index
        SGT   R3,R1,R4      ; test if finished
        BEQZ  R3,inicio     ; loop if not finished
```

## Files

- `BUCLE_ESCALAR1.S`: Basic scalar implementation of the DAXPY loop.
- `BUCLE_ESCALAR2.S`: Optimized scalar version of the DAXPY loop.
- `BUCLE_DES1.S`: Loop unrolling optimization (version 1).
- `BUCLE_DES2.S`: Alternative loop unrolling optimization (version 2).
- `BUCLE_DES3.S`: Advanced optimization using variable renaming and copy propagation techniques.
