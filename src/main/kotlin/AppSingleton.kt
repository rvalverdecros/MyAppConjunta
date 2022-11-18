import java.sql.Connection
import java.sql.DriverManager

class AppSingleton private constructor(){

    companion object{
        @Volatile
        private var instance:AppSingleton? = null
        fun getInstance():AppSingleton{
            if (instance == null){
                instance = AppSingleton()
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
        } else{
            println("[Conexión ya existente]")
        }
    }

    fun select(){

    }

    fun desconexion(){
        if (conn != null){
            conn?.close()
            println("[Desconexión realizada]")
        }else{
            println("[No existe conexcion a la Base de Datos]")
        }
    }

}