package aplicacion.modelo

import aplicacion.modelo.Setencias.createTabla
import aplicacion.modelo.clases.Alumno
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class GestorBDD private constructor(){

    companion object{
        @Volatile
        private var instance: GestorBDD? = null
        fun getInstance(): GestorBDD {
            if (instance == null){
                instance = GestorBDD()
            }
            return instance!!
        }
    }

    private val url = "jdbc:mysql://localhost/"
    private val bd = "bddeprueba"
    private val user = "root"
    private val pass = ""




    @Volatile
    private var conn: Connection? = null

    fun conectarBD(){
        if (conn == null){
            println("[Conexión realizada]")
            conn = DriverManager.getConnection(url+bd,user,pass)
            conn?.autoCommit=false
        } else{
            println("[Conexión ya existente]")
        }
    }

    fun selectAllAlumnos() : List<Alumno>? {



        val lisAlumnos = mutableListOf<Alumno>()

        try {

            val st = conn?.createStatement()
            val rs1 = st?.executeQuery(Setencias.selectAll)
            if (rs1 != null) {
                while (rs1.next()){
                    val alumno = Alumno()

                    alumno.dni = rs1.getString("DNI")
                    alumno.nombre = rs1.getString("Nombre")
                    alumno.edad = rs1.getInt("Edad")
                    alumno.ciudad = rs1.getString("Ciudad")

                    lisAlumnos.add(alumno)
                }
            }

            return lisAlumnos

        }catch (e : SQLException){
            return null
        }
    }

    fun actualizar(dni:String,nombre:String) : Boolean{
        val actualizar = Setencias.actualizar
        try {
            conn?.prepareStatement(actualizar).use { st ->
                st?.setString(1,dni)
                st?.setString(2,nombre)
                if (st != null) {
                    st.executeUpdate() > 0
                }
            }
            conn?.commit()
            return true
        } catch (e: SQLException) {
            conn?.rollback()
            return false
        }

    }

    fun crear(dni:String,nombre:String,edad:Int,ciudad:String):Boolean{
        val crear = Setencias.crear
        try {
            conn?.prepareStatement(crear).use { st ->
                st?.setString(1,dni)
                st?.setString(2,nombre)
                st?.setInt(3,edad)
                st?.setString(4,ciudad)
                if (st != null) {
                    st.executeUpdate() > 0
                }
            }
            conn?.commit()
            return true
        } catch (e: SQLException) {
            conn?.rollback()
            return false
        }
    }

    fun deletePorDNI(dni:String):Boolean{
        val borrar = Setencias.borrar
        try {
            conn?.prepareStatement(borrar).use { st ->
                st?.setString(1,dni)
                if (st != null) {
                    st.executeUpdate() > 0
                }
            }
            conn?.commit()
            return true
        } catch (e: SQLException) {
            conn?.rollback()
            return false
        }
    }

     fun createTable():Boolean {
         if (selectAllAlumnos() == null) {
             var tablacreada = false
             try {

                 conn?.createStatement().use { st ->
                     st?.execute(createTabla)
                     tablacreada = true
                 }
                 conn?.commit()
             } catch (e: SQLException) {
                 conn?.rollback()
             }
             return tablacreada
         }else{
             return true
         }
    }

    fun desconexion(){
        if (conn != null){
            conn?.close()
            println("[Desconexión realizada]")
        }else{
            println("[No existe conexcion a la Base de Datos]")
        }
    }
    private fun printSQLException(ex: SQLException) {
        for (e in ex) {
            if (e is SQLException) {
                e.printStackTrace(System.err)
                System.err.println("SQLState: " + e.sqlState)
                System.err.println("Error Code: " + e.errorCode)
                System.err.println("Message: " + e.message)
                var t = ex.cause
                while (t != null) {
                    println("Cause: $t")
                    t = t.cause
                }
            }
        }
    }
}