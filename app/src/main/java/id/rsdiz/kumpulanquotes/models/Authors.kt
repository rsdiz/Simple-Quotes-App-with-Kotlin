package id.rsdiz.kumpulanquotes.models

data class Authors(
    val name: String,
    val total: Int = 0,
    val quotes: List<String>
)
