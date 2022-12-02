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


}