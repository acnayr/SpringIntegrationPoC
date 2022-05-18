package com.poc.springintegration;

@RuleDefinition
public class PersonaNombreRegla implements Rule<Person, Person> {

    @Override
    public boolean condicion(Person person) {
        return person.getEdad() % 2 == 0;
    }

    @Override
    public Person accionPrincipal(Person person) {
        person.setNombre("CARLOS");
        System.out.println("Seteando nombre CARLOS");
        return person;
    }

    @Override
    public Person acccionAlterna(Person person) {
        person.setNombre("JOSE");
        System.out.println("Seteando nombre JOSE");
        return person;
    }
}
