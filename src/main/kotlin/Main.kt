fun main() {
val prueba = GestorBDD.getInstance()
    prueba.conectarBD()
    prueba.actualizar("98765432Z","Rafa")
    prueba.selectAll()
    prueba.desconexion()
}