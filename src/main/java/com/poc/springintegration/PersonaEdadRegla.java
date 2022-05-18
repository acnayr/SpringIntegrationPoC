package com.poc.springintegration;

@RuleDefinition
public class PersonaEdadRegla implements Rule<Person, Person> {

    @Override
    public boolean condicion(Person person) {
        return person.getEdad() > 18;
    }

    @Override
    public Person accionPrincipal(Person person) {
        person.setNombre("Mayor edad");
        System.out.println("Seteando nombre mayor edad");
        return person;
    }

    @Override
    public Person acccionAlterna(Person person) {
        person.setNombre("Menor edad");
        System.out.println("Seteando nombre menor edad");
        return person;
    }

}
