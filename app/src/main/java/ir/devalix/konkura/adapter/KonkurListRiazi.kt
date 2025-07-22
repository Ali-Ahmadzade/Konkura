package ir.devalix.konkura.adapter

data class KonkurListRiazi(
    val year: String,
    val subButtons: List<SubButtonRiazi>,
    var isExpanded: Boolean = false
)

data class SubButtonRiazi(
    val id: String,
    val text: String
)