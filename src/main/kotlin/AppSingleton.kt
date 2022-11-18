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
    private val selectAll = "SELECT * FROM alumnos;"
    private val actualizar = "UPDATE `alumnos` SET `DNI`='98765431Z' WHERE `Nombre`='Rafa';"
    private val crear = "INSERT INTO `alumnos`(`DNI`, `Nombre`, `Edad`, `Ciudad`) VALUES ('12345678G','Ruth','23','San Fernando');"
    private val borrar = "DELETE FROM `alumnos` WHERE `Nombre`='Ruth';"

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

    fun selectAll(){
        val st = conn?.createStatement()
        val rs1 = st?.executeQuery(selectAll)
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

    fun desconexion(){
        if (conn != null){
            conn?.close()
            println("[Desconexión realizada]")
        }else{
            println("[No existe conexcion a la Base de Datos]")
        }
    }

}