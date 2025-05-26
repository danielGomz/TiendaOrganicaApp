## TiendaOrganicaApp
------

**Descripción corta**
Sistema simple de gestión para una tienda de alimentos orgánicos. Permite almacenar y gestionar productos (`Fruta`, `Verdura`) en un inventario utilizando principios de Programación Orientada a Objetos en Java.

**Estructura del proyecto**

```
TiendaOrganicaApp/
├── lib/                   # Bibliotecas externas (.jar), si se usan
├── README.md              # Documentación general del proyecto
└── src/                   # Código fuente Java
    ├── App.java           # Clase principal, contiene el método `main`
    └── tiendaorganica/    # Paquetes organizados
        ├── modelo/        # Clases de dominio o modelo de datos
        │   ├── ProductoOrganico.java   # Clase abstracta base para todos los productos
        │   ├── Fruta.java              # Subclase concreta para frutas, hereda de ProductoOrganico
        │   └── Verdura.java            # Subclase concreta para verduras, hereda de ProductoOrganico
        ├── gestion/       # Lógica de negocio del sistema
        │   └── TiendaOrganica.java     # Clase que gestiona el inventario (agregar, eliminar, listar productos)
        └── ui/            # Interfaz de usuario (consola o GUI futura)
            └── TiendaOrganicaGUI.java  # Clase para una futura interfaz gráfica con Swing o JavaFX
```

**Cómo compilar y ejecutar**

1. Compilar:

   ```bash
   javac -d out/ src/**/*.java
   ```
2. Ejecutar:

   ```bash
   java -cp out/ App
   ```

¡Listo para probar tu tienda orgánica!
