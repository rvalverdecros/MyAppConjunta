fun main() {
val prueba = AppSingleton.getInstance()
    prueba.conectarBD()
    prueba.selectAll()
}