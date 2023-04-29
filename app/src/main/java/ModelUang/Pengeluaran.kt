package ModelUang

class Pengeluaran(
    pengenal: String,
    jumlahUang: Int,
    tanggal: String,
    catatan: String,
    var kategori: String
) : Uang(pengenal, jumlahUang, tanggal, catatan)