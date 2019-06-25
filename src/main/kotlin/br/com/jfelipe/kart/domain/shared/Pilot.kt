package br.com.jfelipe.kart.domain.shared

data class Pilot(val id: Int, val name: String) {

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Pilot)
            return false

        return id == other.id
    }

    override fun hashCode() = id.hashCode()

    class Builder {
        private var id: Int? = null
        private var name: String? = null

        fun id(id: String) = apply { this.id = id.toInt() }
        fun name(name: String) = apply { this.name = name }

        fun build() = Pilot(
                id ?: error("Pilot ID is required"),
                name ?: error("Pilot name is required")
        )
    }
}