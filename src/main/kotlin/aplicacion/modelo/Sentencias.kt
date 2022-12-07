package aplicacion.modelo

object Setencias {
    val selectAll = "SELECT * FROM alumnos;"
    val actualizar = "UPDATE `alumnos` SET `DNI`=? WHERE `Nombre`=?;"
    val crear = "INSERT INTO `alumnos`(`DNI`, `Nombre`, `Edad`, `Ciudad`) VALUES (?,?,?,?);"
    val borrar = "DELETE FROM `alumnos` WHERE `DNI`=?;"
    val createTabla = "CREATE TABLE `alumnos` (" +
            "  `DNI` varchar(9) NOT NULL," +
            "  `Nombre` varchar(30) NOT NULL," +
            "  `Edad` int(3) NOT NULL," +
            "  `Ciudad` varchar(30) NOT NULL" +
            "); "
}