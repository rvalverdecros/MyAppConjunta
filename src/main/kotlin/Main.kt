import aplicacion.controlador.AppControlador
import aplicacion.vista.AppVista
import java.lang.ArithmeticException

fun main() {
    menuPrincipal()
}

fun menuPrincipal(){

    val vista = AppVista()
    val controlador = AppControlador(vista)
    controlador.conectarBD()
    controlador.createTable()

    println("Bienvenido usuario/a")

    var orden : Int

    do {

        println( "¿Que desea realizar?")
        println("--------------------------------------------")
        println("1. Añadir alumno a la Base de Datos")
        println("2. Mostrar todos los alumnos de la Base de Datos")
        println("3. Actualizar el DNI de un alumno de la Base de Datos")
        println("4. Eliminar un alumno de la Base de Datos por el DNI")
        println("0. Salir de la aplicacion")



        try {

            orden = readln().toInt()

        }catch (e : Exception){
            println("Error. No se admiten otro tipo de dato")
            orden = -1
        }

        when(orden){
            1->{
                println("¿Como se llamara el alumno?")
                var nombre = readln()
                println("¿Cual es el DNI del alumno?")
                var dni = readln()
                println("¿Cuantos anios tiene el alumno?")
                var anios : Int = 0
                try {

                    anios = readln().toInt()

                }catch (e : Exception){
                    println("Error. No se admiten otro tipo de dato")
                    menuPrincipal()
                }
                println("¿En que ciudad vive el alumno?")
                var ciudad = readln()
                controlador.createAlumn(dni,nombre, anios,ciudad)
            }
            2->{
                controlador.allAlumns()
            }
            3->{
                println("¿Como se llama el alumno?")
                var nombre = readln()
                println("¿Cual sera el DNI del alumno?")
                var dni = readln()
                controlador.modifyAlumn(nombre, dni)
            }
            4->{
                println("¿Cual es el DNI del alumno?")
                var dni = readln()
                controlador.deleteAlumnByDNI(dni)
            }
        }

    }while (orden != 0)
    controlador.desconectarBD()
}