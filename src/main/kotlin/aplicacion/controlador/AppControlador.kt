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
                println("----------------------------------------")
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

    fun createAlumn(dni: String, nombre: String, edad: Int, ciudad: String){
        val create = gestorBDD.crear(dni,nombre,edad,ciudad)

        if (create){
            vista.createTrue()
        }else{
            vista.createFalse()
        }
    }

    fun deleteAlumnByDNI(dni: String){
        val delete = gestorBDD.deletePorDNI(dni)

        if (delete){
            vista.deleteTrue()
        }else{
            vista.deleteFalse()
        }
    }
    fun createTable(){
        val crearTabla = gestorBDD.createTable()
        if (crearTabla){
            vista.prepareTrue()
        }else{
            vista.prepareFalse()
        }
    }

    fun conectarBD(){
        gestorBDD.conectarBD()
    }

    fun desconectarBD() {
        gestorBDD.desconexion()
    }

}