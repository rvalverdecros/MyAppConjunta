object Setencias {
    val selectAll = "SELECT * FROM alumnos;"
    val actualizar = "UPDATE `alumnos` SET `DNI`=? WHERE `Nombre`=?;"
    val crear = "INSERT INTO `alumnos`(`DNI`, `Nombre`, `Edad`, `Ciudad`) VALUES ('12345678G','Ruth','23','San Fernando');"
    val borrar = "DELETE FROM `alumnos` WHERE `Nombre`='Ruth';"
}