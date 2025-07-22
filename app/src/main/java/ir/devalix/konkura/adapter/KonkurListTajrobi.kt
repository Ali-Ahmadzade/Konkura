package ir.devalix.konkura.adapter

data class KonkurListTajrobi(
    val year: String,
    val subButtons: List<SubButtonTajrobi>,
    var isExpanded: Boolean = false
)

data class SubButtonTajrobi(
    val id: String,
    val text: String
)