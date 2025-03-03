package br.com.fiap.meuscontatos.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_contato") // mantendo a convenção do nome da tabela
data class Contato(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var nome: String = "",
    var telefone: String = "",
    @ColumnInfo(name = "is_amigo") // manterndo a convenção do nome da coluna
    var isAmigo: Boolean = false
    )
