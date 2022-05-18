package com.poc.springintegration;

public interface Rule<I, O> {

    boolean condicion(I i);
    O accionPrincipal(I i);
    O acccionAlterna(I i);
}
