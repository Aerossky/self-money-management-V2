package ModelUang

import java.io.Serializable

open class Uang(
    var pengenal: String,
    var jumlahUang: Int,
    var tanggal: String,
    var catatan: String
): Serializable