package com.poc.springintegration;

@RuleDefinition
public class PersonaClienteRegla implements Rule<Person, Person> {

    @Override
    public boolean condicion(Person person) {
        return person.getEdad() == 30;
    }

    @Override
    public Person accionPrincipal(Person person) {
        person.setNombre("Cliente");
        System.out.println("Seteando nombre cliente");
        return person;
    }

    @Override
    public Person acccionAlterna(Person person) {
        person.setNombre("No Cliente");
        System.out.println("Seteando nombre no-cliente");
        return person;
    }

}