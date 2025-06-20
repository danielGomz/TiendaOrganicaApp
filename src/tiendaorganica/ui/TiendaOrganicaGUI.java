package tiendaorganica.ui;

import javax.swing.*;

public class TiendaOrganicaGUI {

    public TiendaOrganicaGUI() {
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");
        SplashScreen splash = new SplashScreen();
        splash.setVisible(true);

        try {
            Thread.sleep(3000); // Mostrar el splash por 3 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        splash.dispose(); // Cerrar splash

        // Mostrar la pantalla principal
        PantallaPrincipal principal = new PantallaPrincipal();
        principal.setVisible(true);
    }
}
