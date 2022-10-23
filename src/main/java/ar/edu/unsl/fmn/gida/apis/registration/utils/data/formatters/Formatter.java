package ar.edu.unsl.fmn.gida.apis.registration.utils.data.formatters;

@FunctionalInterface
public interface Formatter<Entity> {
    void format(Entity entity);
}
