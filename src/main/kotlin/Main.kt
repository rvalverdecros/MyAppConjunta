fun main() {
val prueba = GestorBDD.getInstance()
    prueba.conectarBD()
    prueba.deletePorNombre("Juan")
    prueba.selectAll()
    prueba.desconexion()
}