package ar.edu.unsl.fmn.gida.apis.registration.utils.data.interpreters;

import ar.edu.unsl.fmn.gida.apis.registration.exceptions.ConvertionException;
import ar.edu.unsl.fmn.gida.apis.registration.model.Person;
import ar.edu.unsl.fmn.gida.apis.registration.utils.cypher.Cypher;

public class QrDataConverter implements Converter<Person> {

    private Cypher cypher;

    public QrDataConverter(Cypher cypher) {
        this.cypher = cypher;
    }

    @Override
    public Person objectify(String data) throws ConvertionException {
        Person ret = new Person();

        ret.setId(Integer.parseInt(this.cypher.decrypt(data)));

        return ret;
    }

    @Override
    public String stringify(Person object) throws ConvertionException {
        return this.cypher.encrypt("" + object.getId().intValue());
    }
}
