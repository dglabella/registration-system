package ar.edu.unsl.fmn.gida.apis.registration.utils.formatters;

@FunctionalInterface
public interface Formatter<Entity> {
    void format(Entity entity);
}
