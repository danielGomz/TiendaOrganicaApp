package tiendaorganica.modelo;

public class Verdura extends ProductoOrganico {
    private String tipoVerdura;

    public Verdura(String nombre, double precio, String tipoVerdura) {
        super(nombre, "Verdura", precio);
        this.tipoVerdura = tipoVerdura;
    }

    public String getTipoVerdura() {
        return tipoVerdura;
    }

    public void setTipoVerdura(String tipoVerdura) {
        this.tipoVerdura = tipoVerdura;
    }

    public double calcularPrecioVenta() {
        double precioBase = getPrecio();
        double recargo = 0.0;

        // Lógica más natural y enfocada en lo orgánico
        if (tipoVerdura.equalsIgnoreCase("orgánica")) {
            recargo = 0.10; // 10% más por ser orgánica
        } else if (tipoVerdura.equalsIgnoreCase("local")) {
            recargo = 0.05; // 5% por ser de producción local
        } else if (tipoVerdura.equalsIgnoreCase("fuera de temporada")) {
            recargo = 0.20; // 20% más por escasez
        }

        return precioBase * (1 + recargo);
    }

    public double aplicarDescuento() {
        if (getPrecio() > 5.00) {
            return calcularPrecioVenta() * 0.90;
        } else {
            return calcularPrecioVenta();
        }
    }

    @Override
    public String toString() {
        return "Verdura: " + getNombre() + ", Tipo: " + tipoVerdura +
                ", Precio base: $" + getPrecio() +
                ", Precio con descuento: $" + String.format("%.2f", aplicarDescuento());
    }
}