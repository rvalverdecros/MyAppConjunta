package aplicacion.vista

import aplicacion.modelo.clases.Alumno

class AppVista() {
    fun baseDeDatosCaida() {
        println("Base de datos caida")
    }


    fun showAlumn(alumno: Alumno) {
        println("Nombre : " + alumno.nombre)
        println("Edad : " + alumno.edad)
        println("DNI : " + alumno.dni)
        println("Ciudad : " + alumno.ciudad)
    }

    fun noAlumns() {
        println("No hay alumnos disponibles")
    }

    fun updateTrue() {
        println("Se ha modificado correctamente")
    }

    fun updateFalse() {
        println("Ha ocurrido un error en la modificación")
    }

    fun createTrue() {
        println("Se ha creado correctamente")
    }

    fun createFalse() {
        println("Ha ocurrido un error en la creación")
    }

    fun deleteTrue() {
        println("Se ha eliminado correctamente")
    }

    fun deleteFalse() {
        println("Ha ocurrido un error en el borrado")
    }

    fun prepareTrue() {
        println("Se preparo la Tabla correctamente")
    }

    fun prepareFalse() {
        println("Hubo un error en la creacion de la tabla")
    }


}