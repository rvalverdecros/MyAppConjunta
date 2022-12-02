package aplicacion.controlador

import aplicacion.modelo.GestorBDD
import aplicacion.vista.AppVista

class AppControlador(val vista: AppVista) {

    private val gestorBDD = GestorBDD.getInstance()
    fun allAlumns() {

        val lisAlumnos = gestorBDD.selectAllAlumnos()

        if (lisAlumnos?.size == null) {

            vista.baseDeDatosCaida()

        } else if (lisAlumnos.isEmpty()) {

            vista.noAlumns()

        } else if (lisAlumnos.size > 0) {

            for (alumno in lisAlumnos) {
                vista.showAlumn(alumno)
            }

        }

    }

    fun modifyAlumn(nombre : String, dni : String){

        val update = gestorBDD.actualizar(dni, nombre)

        if (update){
            vista.updateTrue()
        }else{
            vista.updateFalse()
        }

    }

    fun conectarBD(){
        gestorBDD.conectarBD()
    }

    fun desconectarBD() {
        gestorBDD.desconexion()
    }

}