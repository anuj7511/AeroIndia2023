package universal.appfactory.aeroindia2023

data class UpdateErrorResponse(
    val name: Array<String> = arrayOf("No name error"),
    val id: Array<String> = arrayOf("No ID error")
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UpdateErrorResponse

        if (!name.contentEquals(other.name)) return false
        if (!id.contentEquals(other.id)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.contentHashCode()
        result = 31 * result + id.contentHashCode()
        return result
    }
}
