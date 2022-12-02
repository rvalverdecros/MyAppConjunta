import aplicacion.controlador.AppControlador
import aplicacion.modelo.GestorBDD
import aplicacion.vista.AppVista

fun main() {

    val vista = AppVista()
    val controlador = AppControlador(vista)

    controlador.conectarBD()

    controlador.allAlumns()

    controlador.desconectarBD()
}