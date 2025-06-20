package tiendaorganica.gestion;

import tiendaorganica.modelo.Fruta;
import tiendaorganica.modelo.Verdura;
import tiendaorganica.modelo.ProductoOrganico;
import java.util.ArrayList;
import java.util.List;

public class TiendaOrganica {

    private List<ProductoOrganico> inventario;

    public TiendaOrganica() {
        this.inventario = new ArrayList<>();
    }

    public void agregarProducto(ProductoOrganico producto) {
        if (producto == null) {
            throw new IllegalArgumentException("No se puede agregar un producto nulo.");
        }
        inventario.add(producto);
    }

    public boolean eliminarProducto(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede ser vacío.");
        }
        for (int i = 0; i < inventario.size(); i++) {
            if (inventario.get(i).getNombre().equalsIgnoreCase(nombre)) {
                inventario.remove(i);
                return true;
            }
        }
        return false;
    }

    public List<ProductoOrganico> getInventario() {
        return new ArrayList<>(inventario);
    }

    // **Nuevos métodos para crear productos específicos y agregarlos**

    public void agregarFruta(String nombre, double precio, String tipoFruta) {
        Fruta fruta = new Fruta(nombre, precio, tipoFruta);
        agregarProducto(fruta);
    }

    public void agregarVerdura(String nombre, double precio, String tipoVerdura) {
        Verdura verdura = new Verdura(nombre, precio, tipoVerdura);
        agregarProducto(verdura);
    }
}
