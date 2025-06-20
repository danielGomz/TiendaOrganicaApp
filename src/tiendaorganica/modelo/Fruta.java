package tiendaorganica.modelo;

public class Fruta extends ProductoOrganico {
    private String tipoFruta;

    // Constructor que incluye todos los atributos
    public Fruta(String nombre, double precio, String tipoFruta) {
        super(nombre, "Fruta", precio); // Llama al constructor de ProductoOrganico
        this.tipoFruta = tipoFruta;
    }

    // Getter y setter para tipoFruta
    public String getTipoFruta() {
        return tipoFruta;
    }

    public void setTipoFruta(String tipoFruta) {
        this.tipoFruta = tipoFruta;
    }

    public double calcularPrecioVenta() {
        double precioBase = getPrecio();
        double ajuste = 0.0;

        // Ajuste de precio según tipo de fruta
        if (tipoFruta.equalsIgnoreCase("certificada")) {
            ajuste = 0.15; // 15% más
        } else if (tipoFruta.equalsIgnoreCase("especial")) {
            ajuste = 0.10; // 10% más
        } else if (tipoFruta.equalsIgnoreCase("local")) {
            ajuste = -0.10; // 10% menos
        } else if (tipoFruta.equalsIgnoreCase("de temporada")) {
            ajuste = -0.05; // 5% menos
        }

        return precioBase * (1 + ajuste);
    }

    public double aplicarDescuento() {
        double precioVenta = calcularPrecioVenta();

        // Descuento si el precio base es mayor a $3.00
        if (getPrecio() > 3.00) {
            return precioVenta * 0.85; // 15% de descuento
        } else {
            return precioVenta;
        }
    }

    // toString para mostrar la información completa de la fruta
    @Override
    public String toString() {
        return super.toString() + " | Tipo de fruta: " + tipoFruta +
                " | Precio con descuento: $" + aplicarDescuento() +
                " | Precio de venta: $" + calcularPrecioVenta();
    }
}
