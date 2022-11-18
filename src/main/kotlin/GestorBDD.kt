import java.sql.Connection
import java.sql.DriverManager
import Setencias
import java.sql.SQLException

class GestorBDD private constructor(){

    companion object{
        @Volatile
        private var instance:GestorBDD? = null
        fun getInstance():GestorBDD{
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
    private val selectAll = "SELECT * FROM alumnos;"




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

    fun selectAll(){
        val st = conn?.createStatement()
        val rs1 = st?.executeQuery(Setencias.selectAll)
        if (rs1 != null) {
            while (rs1.next()){
                println("--------------------------------------------------------------------------")
                println(rs1.getString("DNI"))
                println(rs1.getString("Nombre"))
                println(rs1.getString("Edad"))
                println(rs1.getString("Ciudad"))
                println("--------------------------------------------------------------------------")
            }
        }
    }

    fun actualizar(dni:String,nombre:String){
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
        } catch (e: SQLException) {
            printSQLException(e)
        }

    }

    fun crear(dni:String,nombre:String,edad:String,ciudad:String){
        val crear = "INSERT INTO `alumnos`(`DNI`, `Nombre`, `Edad`, `Ciudad`) VALUES ('$dni','$nombre','$edad','$ciudad');"
        val st = conn?.createStatement()
        val rs1 = st?.executeUpdate(crear)
        if (rs1 != null){
            println("Total de lineas creadas: $rs1")
        }
    }

    fun deletePorNombre(nombre:String){
        val borrar = "DELETE FROM `alumnos` WHERE `Nombre`='$nombre';"
        val st = conn?.createStatement()
        val rs1 = st?.executeUpdate(borrar)

        if (rs1 != null){
            println("Total de lineas eliminadas: $rs1")
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