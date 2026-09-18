// Nama dan NIM Team Assignment 2 DSSA (LEC)
// CHRISTIAN HOTASI VICCARE HUTAGAOL - 2902804014
// FAIZA KURNIAWATI - 2902807880
// ARI JUNIANTO - 2902828701
// UTAS PRASOJO - 2902809551
// HANDSON PANGGABEAN - 2902820005

import java.util.Scanner;

public class PlaylistArray {
    // Scanner untuk menerima input dari pengguna
    static Scanner input = new Scanner(System.in);

    // Array untuk menyimpan maksimal 10 objek Lagu
    static Lagu[] playlist = new Lagu[10];

    // Menyimpan jumlah lagu yang saat ini ada di playlist
    static int jumlahLagu = 0;

    public static void main(String[] args) {
        int pilihan;

        // Perulangan menu utama
        do {
            System.out.println("\n================================");
            System.out.println("       MENU PLAYLIST MUSIK");
            System.out.println("================================");
            System.out.println("1. Tampilkan semua lagu");
            System.out.println("2. Tambah lagu baru");
            System.out.println("3. Hapus lagu berdasarkan judul");
            System.out.println("4. Cari lagu berdasarkan judul");
            System.out.println("5. Urutkan lagu berdasarkan durasi");
            System.out.println("6. Keluar");
            System.out.println("================================");
            System.out.print("Pilih menu: ");

            pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {

                case 1:
                    // Memanggil method Traversal
                    tampilkanSemuaLagu();
                    break;

                case 2:
                    // Memanggil method Insertion untuk menambahkan lagu baru
                    tambahLagu();
                    break;

                case 3:
                    // Memanggil method Deletion untuk menghapus lagu berdasarkan judul
                    hapusLagu();
                    break;

                case 4:
                    // Akan diimplementasikan pada bagian Searching
                    cariLagu();
                    break;

                case 5:
                    // Memanggil method Sorting untuk mengurutkan lagu berdasarkan durasi
                    urutkanLaguBerdasarkanDurasi();
                    break;


                case 6:
                    System.out.println("Program selesai. Terima kasih!");
                    break;

                default:
                    System.out.println("Pilihan tidak valid!");
            }

        } while (pilihan != 6);

        input.close();
    }


    public static void tampilkanSemuaLagu() {
        // Memeriksa apakah playlist masih kosong
        if (jumlahLagu == 0) {
            System.out.println("\nPlaylist masih kosong.");
            return;
        }

        System.out.println("\n========== DAFTAR LAGU ==========");

        // Melakukan traversal dari indeks 0 sampai indeks terakhir yang berisi lagu
        for (int i = 0; i < jumlahLagu; i++) {

            System.out.println("\nLagu ke-" + (i + 1));

            // Menampilkan informasi objek Lagu
            playlist[i].tampilkanInfo();

            System.out.println("--------------------------------");
        }
    }

    // Method Insertion untuk menambahkan lagu baru ke dalam playlist
    public static void tambahLagu() {
        // Memeriksa apakah jumlah lagu sudah mencapai kapasitas maksimum array
        if (jumlahLagu >= playlist.length) {
            System.out.println("\nPlaylist sudah penuh. Maksimal 10 lagu.");
            return;
        }

        // Meminta pengguna memasukkan judul lagu
        System.out.print("\nMasukkan judul lagu : ");
        String judul = input.nextLine();

        // Meminta pengguna memasukkan nama artis
        System.out.print("Masukkan artis      : ");
        String artis = input.nextLine();

        // Meminta pengguna memasukkan durasi lagu dalam menit
        System.out.print("Masukkan durasi (menit): ");
        double durasi = input.nextDouble();
        input.nextLine(); // Membersihkan karakter newline yang tersisa pada buffer input

        // Membuat objek Lagu baru dari data yang dimasukkan pengguna
        Lagu laguBaru = new Lagu(judul, artis, durasi);

        // Menyisipkan objek lagu pada indeks kosong berikutnya
        playlist[jumlahLagu] = laguBaru;

        // Menambah jumlah lagu setelah proses insertion berhasil
        jumlahLagu++;

        System.out.println("Lagu berhasil ditambahkan!");
        
        // Menampilkan daftar lagu saat ini
        System.out.println("\nDaftar lagu saat ini:");
        tampilkanRingkas();
    }

    // Method Delete untuk menghapus lagu berdasarkan judul
    public static void hapusLagu() {
        // Cek jika playlist masih kosong (belum ada lagu)
        if (jumlahLagu == 0) {
            // jika playlist masih kosong ~> tampilkan pesan info kepada User ~> hentikan proses
            System.out.println("\nPlaylist masih kosong. Tidak ada lagu yang bisa dihapus.");
            return;
        }

        System.out.print("\nMasukkan judul lagu yang ingin dihapus: ");
        String judulCari = input.nextLine(); // Simpan judul lagu yang di input oleh User

        // Cari lagu yang ingin dihapus dari playlist lagu
        int indeksDitemukan = -1;
        for (int i = 0; i < jumlahLagu; i++) {
            if (playlist[i].getJudul().equalsIgnoreCase(judulCari)) {
                indeksDitemukan = i;
                break;
            }
        }

        // Cek apakah lagu ditemukan
        if (indeksDitemukan == -1) {
            // jika lagu tidak ditemukan di dalam playlist ~> tampilkan pesan error kepada User ~> hentikan proses
            System.out.println("Lagu dengan judul \"" + judulCari + "\" tidak ditemukan.");
            return;
        }

        // Menggeser setiap elemen setelah posisi yang dihapus maju satu indeks
        for (int i = indeksDitemukan; i < jumlahLagu - 1; i++) { // inisialisasi i = indekDitemukan, untuk mengskip lagu index sebelumnya (karna seharusnya posisi lagu di index sebelumnya tidak diubah)
            playlist[i] = playlist[i + 1]; // geser lagu
        }

        // Kosongkan indeks terakhir yang kini sudah tidak terpakai
        playlist[jumlahLagu - 1] = null;

        // Mengurangi jumlah lagu setelah proses deletion berhasil
        jumlahLagu--;

        System.out.println("Lagu \"" + judulCari + "\" berhasil dihapus dari playlist!");

        // Menampilkan daftar lagu saat ini
        System.out.println("\nDaftar lagu saat ini:");
        tampilkanRingkas();
    }

    //Method searching menggunakan Linear Search
    public static void cariLagu(){
        //Memeriksa isi playlist
        if (jumlahLagu == 0){
            System.out.println("\nPlaylist masih kosong. Tidak ada lagu yang bisa dicari.");
            return;
        }

        // Menampilkan daftar lagu saat ini
        System.out.println("\nDaftar lagu saat ini:");
        tampilkanRingkas();

        //Mencari judul lagu
        System.out.print("\nMasukkan judul lagu: ");
        String judulCari = input.nextLine();

        //Linear Search
        for (int i=0; i < jumlahLagu; i++){
            if (playlist[i].getJudul().equalsIgnoreCase(judulCari)){
                System.out.println("Lagu ditemukan");
                playlist[i].tampilkanInfo();
                return;
            }
        }
        //Jika lagu tidak ditemukan
        System.out.println("Lagu \"" + judulCari + "\" tidak ditemukan");

    }

    // Method Sorting untuk mengurutkan lagu berdasarkan durasi (ascending) menggunakan Bubble Sort
    public static void urutkanLaguBerdasarkanDurasi() {
        // Memeriksa apakah playlist masih kosong
        if (jumlahLagu == 0) {
            System.out.println("\nPlaylist masih kosong. Tidak ada lagu yang bisa diurutkan.");
            return;
        }

        // Menampilkan daftar lagu sebelum diurutkan
        System.out.println("\nDaftar lagu sebelum diurutkan:");
        tampilkanRingkas();

        // Bubble Sort: membandingkan tiap pasangan elemen bersebelahan secara berulang
        // dan menukarnya apabila urutannya salah, sehingga elemen dengan durasi terbesar
        // akan "menggelembung" (bubble up) ke posisi paling akhir pada tiap iterasi luar.
        for (int i = 0; i < jumlahLagu - 1; i++) {
            // Setiap iterasi luar menjamin satu elemen terbesar berada di posisi yang tepat,
            // sehingga pembanding pada iterasi berikutnya cukup sampai (jumlahLagu - 1 - i)
            for (int j = 0; j < jumlahLagu - 1 - i; j++) {
                if (playlist[j].getDurasi() > playlist[j + 1].getDurasi()) {
                    // Tukar posisi kedua lagu jika lagu di posisi j durasinya lebih besar
                    Lagu temp = playlist[j];
                    playlist[j] = playlist[j + 1];
                    playlist[j + 1] = temp;
                }
            }
        }

        // Menampilkan daftar lagu setelah diurutkan
        System.out.println("\nDaftar lagu setelah diurutkan (berdasarkan durasi, ascending):");
        tampilkanRingkas();
    }

    // Method bantuan untuk menampilkan daftar lagu dalam format ringkas satu baris per lagu
    private static void tampilkanRingkas() {
        for (int i = 0; i < jumlahLagu; i++) {
            System.out.println((i + 1) + ". " + playlist[i].getJudul() + " - " + playlist[i].getArtis() + " (" + String.format("%.2f", playlist[i].getDurasi()) + " menit)");
        }
    }
}