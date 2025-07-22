package ir.devalix.konkura.adapter

data class KonkurListEnsani(
    val year: String,
    val subButtons: List<SubButtonEnsani>,
    var isExpanded: Boolean = false
)

data class SubButtonEnsani(
    val id: String,
    val text: String
)