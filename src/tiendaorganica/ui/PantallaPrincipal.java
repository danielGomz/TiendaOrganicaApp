package tiendaorganica.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import tiendaorganica.gestion.TiendaOrganica;
import tiendaorganica.modelo.Fruta;
import tiendaorganica.modelo.ProductoOrganico;
import tiendaorganica.modelo.Verdura;

public class PantallaPrincipal extends JFrame {

    public JTextField campoNombre, campoPrecio;
    private TiendaOrganica tienda;
    public JComboBox<String> comboTipo, comboSubtipo;
    public JButton botonAgregar, botonEliminar;
    public JTable tablaInventario;
    public DefaultTableModel modeloTabla;

    public PantallaPrincipal() {
        super("Tienda Orgánica - Inventario");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        tienda = new TiendaOrganica();

        // Panel formulario vacío con etiquetas y campos vacíos
        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(new BoxLayout(panelFormulario, BoxLayout.Y_AXIS));
        panelFormulario.setBackground(new Color(245, 245, 235));
        panelFormulario.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Agregar Producto");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        titulo.setForeground(new Color(30, 70, 30));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelFormulario.add(titulo);
        panelFormulario.add(Box.createVerticalStrut(15));

        campoNombre = crearCampo(panelFormulario, "Nombre:");
        campoPrecio = crearCampo(panelFormulario, "Precio:");

        panelFormulario.add(new JLabel("Tipo:"));
        comboTipo = new JComboBox<>(new String[] { "Fruta", "Verdura" });
        comboTipo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        comboTipo.setBackground(Color.WHITE);
        panelFormulario.add(comboTipo);

        panelFormulario.add(Box.createVerticalStrut(10));
        panelFormulario.add(new JLabel("Subtipo:"));
        comboSubtipo = new JComboBox<>();
        comboSubtipo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        comboSubtipo.setBackground(Color.WHITE);
        panelFormulario.add(comboSubtipo);

        panelFormulario.add(Box.createVerticalStrut(20));

        botonAgregar = new JButton("Agregar");
        botonAgregar.setBackground(new Color(100, 170, 100));
        botonAgregar.setForeground(Color.WHITE);
        botonAgregar.setFocusPainted(false);
        botonAgregar.setFont(new Font("SansSerif", Font.BOLD, 14));
        botonAgregar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        panelFormulario.add(botonAgregar);

        panelFormulario.add(Box.createVerticalStrut(10));

        botonEliminar = new JButton("Eliminar seleccionado");
        botonEliminar.setBackground(new Color(170, 60, 60));
        botonEliminar.setForeground(Color.WHITE);
        botonEliminar.setFocusPainted(false);
        botonEliminar.setFont(new Font("SansSerif", Font.BOLD, 14));
        botonEliminar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        panelFormulario.add(botonEliminar);

        // Panel tabla vacío preparado
        JPanel panelTabla = new JPanel(new BorderLayout());
        panelTabla.setBorder(new EmptyBorder(20, 10, 20, 20));
        panelTabla.setBackground(Color.WHITE);

        JLabel etiquetaTabla = new JLabel("Inventario");
        etiquetaTabla.setFont(new Font("SansSerif", Font.BOLD, 20));
        etiquetaTabla.setForeground(new Color(30, 70, 30));
        etiquetaTabla.setBorder(new EmptyBorder(0, 0, 10, 0));
        panelTabla.add(etiquetaTabla, BorderLayout.NORTH);

        modeloTabla = new DefaultTableModel(
                new String[] { "Nombre", "Tipo", "Subtipo", "Precio Base", "Precio de venta",
                        "Precio con descuento" },
                0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // tabla solo lectura
            }
        };
        tablaInventario = new JTable(modeloTabla);
        tablaInventario.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollTabla = new JScrollPane(tablaInventario);
        panelTabla.add(scrollTabla, BorderLayout.CENTER);

        add(panelFormulario, BorderLayout.WEST);
        add(panelTabla, BorderLayout.CENTER);

        // Inicializa subtipo según tipo seleccionado
        actualizarSubtipos();

        // Listener para actualizar subtipo cuando cambie tipo
        comboTipo.addActionListener(e -> actualizarSubtipos());

        // Agregar producto al pulsar botón
        botonAgregar.addActionListener(e -> {
            String nombre = campoNombre.getText().trim();
            String tipo = (String) comboTipo.getSelectedItem();
            String subtipo = (String) comboSubtipo.getSelectedItem();
            String precioTexto = campoPrecio.getText().trim();

            if (nombre.isEmpty() || precioTexto.isEmpty() || subtipo == null || tipo == null) {
                JOptionPane.showMessageDialog(this, "Por favor completa todos los campos.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            double precio;
            try {
                precio = Double.parseDouble(precioTexto);
                if (precio < 0)
                    throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Precio inválido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (tipo.equals("Fruta")) {
                tienda.agregarFruta(nombre, precio, subtipo);
            } else if (tipo.equals("Verdura")) {
                tienda.agregarVerdura(nombre, precio, subtipo);
            }

            ProductoOrganico nuevo = tienda.getInventario().get(tienda.getInventario().size() - 1);

            String subtipoFinal = "";
            double precioVenta = 0.0;
            double precioConDescuento = 0.0;
            double descuento = 0.0;

            if (nuevo instanceof Verdura) {
                Verdura v = (Verdura) nuevo;
                subtipoFinal = v.getTipoVerdura();
                precioVenta = v.calcularPrecioVenta();
                precioConDescuento = v.aplicarDescuento();
            } else if (nuevo instanceof Fruta) {
                Fruta f = (Fruta) nuevo;
                subtipoFinal = f.getTipoFruta();
                precioVenta = f.calcularPrecioVenta();
                precioConDescuento = f.aplicarDescuento();
            }

            // Ahora sí podés llenar la tabla:
            modeloTabla.addRow(new Object[] {
                    nuevo.getNombre(),
                    nuevo.getTipo(),
                    subtipoFinal,
                    String.format("%.2f", nuevo.getPrecio()),
                    String.format("%.2f", precioVenta),
                    String.format("%.2f", precioConDescuento)

            });

            // Limpiar campos tras agregar
            campoNombre.setText("");
            campoPrecio.setText("");
            comboTipo.setSelectedIndex(0);
            actualizarSubtipos();
        });

        // Eliminar fila seleccionada
        botonEliminar.addActionListener(e -> {
            int filaSeleccionada = tablaInventario.getSelectedRow();
            if (filaSeleccionada >= 0) {
                // Extraer el nombre de la columna 0 (Nombre)
                String nombreProducto = (String) modeloTabla.getValueAt(filaSeleccionada, 0);

                // eliminar producto de  la tienda
                tienda.eliminarProducto(nombreProducto);

                // Eliminar fila
                modeloTabla.removeRow(filaSeleccionada);
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un producto para eliminar.", "Advertencia",
                        JOptionPane.WARNING_MESSAGE);
            }
        });

        setVisible(true);
    }

    private void actualizarSubtipos() {
        comboSubtipo.removeAllItems();
        String tipo = (String) comboTipo.getSelectedItem();

        if ("Fruta".equalsIgnoreCase(tipo)) {
            comboSubtipo.addItem("Certificada");
            comboSubtipo.addItem("Especial");
            comboSubtipo.addItem("Local");
            comboSubtipo.addItem("De temporada");
        } else if ("Verdura".equalsIgnoreCase(tipo)) {
            comboSubtipo.addItem("Orgánica");
            comboSubtipo.addItem("Local");
            comboSubtipo.addItem("Fuera de temporada");
        }
    }

    private JTextField crearCampo(JPanel padre, String etiquetaTexto) {
        JLabel etiqueta = new JLabel(etiquetaTexto);
        JTextField campo = new JTextField();
        campo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        campo.setBorder(new LineBorder(Color.LIGHT_GRAY));
        padre.add(etiqueta);
        padre.add(campo);
        padre.add(Box.createVerticalStrut(10));
        return campo;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PantallaPrincipal::new);
    }
}
