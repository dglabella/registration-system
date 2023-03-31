package ar.edu.unsl.fmn.gida.apis.registration.utils.data.interpreters;

import ar.edu.unsl.fmn.gida.apis.registration.model.Person;
import ar.edu.unsl.fmn.gida.apis.registration.utils.cypher.Cypher;

public class QrDataConverter implements Converter<Person> {

    private Cypher cypher;

    public QrDataConverter(Cypher cypher) {
        this.cypher = cypher;
    }

    @Override
    public Person objectify(String data) {
        Person ret = new Person();

        ret.setId(Integer.parseInt(this.cypher.decrypt(data)));

        return ret;
    }

    @Override
    public String stringify(Person object) {

        return this.cypher.encrypt("" + object.getId().intValue());
    }
}
