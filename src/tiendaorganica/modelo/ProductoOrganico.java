package tiendaorganica.modelo;

/**
 * Representa un producto orgánico con nombre, tipo y precio.
 * Esta clase es abstracta y sirve como base para tipos específicos de productos
 * orgánicos.
 */
public abstract class ProductoOrganico {

    private String nombre;
    private String tipo;
    private double precio;

    /**
     * Crea un nuevo producto orgánico con el nombre, tipo y precio especificados.
     *
     * @param nombre el nombre del producto
     * @param tipo   el tipo o categoría del producto
     * @param precio el precio del producto; debe ser mayor o igual a 0
     * @throws IllegalArgumentException si el precio es negativo
     */
    public ProductoOrganico(String nombre, String tipo, double precio) {
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
    }

    /**
     * Obtiene el nombre del producto.
     *
     * @return el nombre del producto
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el tipo del producto.
     *
     * @return el tipo o categoría del producto
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Obtiene el precio del producto.
     *
     * @return el precio actual del producto
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Establece un nuevo nombre para el producto.
     *
     * @param nombre el nuevo nombre del producto
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Establece un nuevo tipo para el producto.
     *
     * @param tipo el nuevo tipo o categoría del producto
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Establece un nuevo precio para el producto.
     *
     * @param precio el nuevo precio; debe ser mayor o igual a 0
     * @throws IllegalArgumentException si el precio es negativo
     */
    public void setPrecio(double precio) {
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        this.precio = precio;
    }

    /**
     * Devuelve una representación en cadena del producto.
     *
     * @return una cadena con la información del producto
     */
    @Override
    public String toString() {
        return "ProductoOrganico{" +
                "nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", precio=" + precio +
                '}';
    }
}