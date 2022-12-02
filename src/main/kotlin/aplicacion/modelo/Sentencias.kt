package aplicacion.modelo

object Setencias {
    val selectAll = "SELECT * FROM alumnos;"
    val actualizar = "UPDATE `alumnos` SET `DNI`=? WHERE `Nombre`=?;"
    val crear = "INSERT INTO `alumnos`(`DNI`, `Nombre`, `Edad`, `Ciudad`) VALUES (?,?,?,?);"
    val borrar = "DELETE FROM `alumnos` WHERE `Nombre`=?;"
}