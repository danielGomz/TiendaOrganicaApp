package tiendaorganica.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

public class SplashScreen extends JFrame {

    public SplashScreen() {
        setTitle("Tienda Orgánica");
        setSize(535, 600);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
    }

    private void initComponents() {
        Color textoVerde = Color.decode("#2a362b");

        // Cargar imagen desde el classpath
        URL logoURL = getClass().getClassLoader().getResource("logo.png");
        ImageIcon labelIcon;

        if (logoURL != null) {
            ImageIcon originalIcon = new ImageIcon(logoURL);
            Image originalImage = originalIcon.getImage();

            int originalWidth = originalIcon.getIconWidth();
            int originalHeight = originalIcon.getIconHeight();

            int maxWidth = 200; // ancho máximo permitido

            // Calcular altura proporcional al nuevo ancho
            int newWidth = maxWidth;
            int newHeight = (originalHeight * newWidth) / originalWidth;

            Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            labelIcon = new ImageIcon(scaledImage);
        } else {
            // Imagen vacía o alternativa si no se encuentra el logo
            labelIcon = new ImageIcon();
            System.err.println("No se pudo encontrar logo.png");
        }

        JLabel labelLogo = new JLabel(labelIcon);
        labelLogo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titulo = new JLabel("Tienda Orgánica");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(textoVerde);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Crear panel vertical
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));

        Color fondo = Color.decode("#fcf8ef");

        panelCentral.setBackground(fondo);

        panelCentral.add(Box.createVerticalGlue()); // Espacio flexible arriba
        panelCentral.add(labelLogo);
        panelCentral.add(Box.createRigidArea(new Dimension(0, 5))); // Separación pequeña
        panelCentral.add(titulo);
        panelCentral.add(Box.createVerticalGlue()); // Espacio flexible abajo

        add(panelCentral, BorderLayout.CENTER);
    }
}
