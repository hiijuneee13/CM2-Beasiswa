import java.util.Scanner;

public class CM2Beasiswa25 {
    static Scanner sc = new Scanner(System.in);
    
    // array 2 dimensi
    // kolom: 0=nama, 1=nim, 2=ipk, 3=jenis, 4=penghasilan
    static String[][] data = new String[5][5];
    static int jumlahData = 0; // berapa banyak data yang sudah disimpan

    public static void main(String[] args) {
        // main: menu utama -> panggil fungsi sesuai pilihan
        while (true) {
            System.out.println();
            System.out.println("=== Sistem Pendaftaran Mahasiswa ===");
            System.out.println("1. Tambah Data Pendaftar Beasiswa");
            System.out.println("2. Tampilkan Semua Pendaftar");
            System.out.println("3. Cari Pendaftar berdasarkan Jenis beasiswa");
            System.out.println("4. Hitung Rata-rata IPK per Jenis beasiswa");
            System.out.println("5. Tampilkan Data Pertama Ditambahkan");
            System.out.println("6. Tampilkan Data Terakhir Ditambahkan");
            System.out.println("7. Keluar");
            System.out.print("Pilih menu (1 - 7): ");
            String menu = sc.nextLine();

            if (menu.equals("1")) {
                inputData();       // fungsi tambah data pendaftar beasiswa
            } else if (menu.equals("2")) {
                tampilSemua();     // fungsi tampil semua pendaftar
            } else if (menu.equals("3")) {
                cariJenis();       // fungsi cari berdasar jenis beasiswa
            } else if (menu.equals("4")) {
                hitungRataRata();  // fungsi hitung rata-rata IPK per jenis beasiswa
            } else if (menu.equals("5")) {
                tampilPertama();  // fungsi tampil data pertama
            } else if (menu.equals("6")) {
                tampilTerakhir();   // fungsi tampil data terakhir
            } else if (menu.equals("7")) {
                System.out.println("Keluar dari program.");
                break;
            } else {
                System.out.println("Pilihan tidak ada, coba lagi.");
            }
        }

        sc.close();
    }


    // Fungsi: inputData Pendaftar Beasiswa
    // Tugas: minta input user, validasi, lalu simpan ke array 2D
    // Jenis kode: input, validasi, array
    static void inputData() {
        // cek kapasitas array
        if (jumlahData == data.length) {
            System.out.println("Kapasitas penuh, tidak bisa tambah.");
            return;
        }

        System.out.print("Nama Mahasiswa: ");
        String nama = sc.nextLine();

        System.out.print("NIM: ");
        String nim = sc.nextLine();

        // input IPK dengan validasi
        String ipk = "";
        while (true) {
            System.out.print("IPK (0.0 - 4.0): ");
            ipk = sc.nextLine();
            try {
                double angkaIpk = Double.parseDouble(ipk);
                if (angkaIpk < 0 || angkaIpk > 4) {
                    System.out.println("IPK harus antara 0 - 4");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Masukkan IPK yang benar, misal 3.4");
            }
        }

        // pilih jenis beasiswa
        String jenis = "";
        while (true) {
            System.out.println("Pilih jenis beasiswa:");
            System.out.println("1. Reguler");
            System.out.println("2. Unggulan");
            System.out.println("3. Riset");
            System.out.print("Masukkan Jenis Beasiswa: ");
            String pilihanJenis = sc.nextLine();

            if (pilihanJenis.equals("Reguler")) {
                jenis = "Reguler";
                break;
            } else if (pilihanJenis.equals("Unggulan")) {
                jenis = "Unggulan";
                break;
            } else if (pilihanJenis.equals("Riset")) {
                jenis = "Riset";
                break;
            } else {
                System.out.println("Pilihan tidak ada, ulangi.");
            }
        }

        // input penghasilan dengan validasi
        String penghasilan = "";
        while (true) {
            System.out.print("Penghasilan orang tua (maks 2000000): ");
            penghasilan = sc.nextLine();
            try {
                int angka = Integer.parseInt(penghasilan);
                if (angka < 0) {
                    System.out.println("Angka tidak boleh negatif.");
                } else if (angka > 2000000) {
                    System.out.println("Pendaftaran dibatalkan karena penghasilan melebihi batas maksimal.");
                    penghasilan = ""; 
                    break;
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Masukkan angka yang benar, contoh: 1500");
            }
        }

        // simpan data kalau valid
        if (!penghasilan.equals("")) {
            data[jumlahData][0] = nama;
            data[jumlahData][1] = nim;
            data[jumlahData][2] = ipk;
            data[jumlahData][3] = jenis;
            data[jumlahData][4] = penghasilan;
            jumlahData++;
            System.out.println("Data tersimpan: " + nim);
            System.out.println("Total pendaftar: " + jumlahData);
        }
    }

    // Fungsi: tampil semua pendaftar
    // Tugas: menampilkan semua data yang tersimpan
    // Jenis kode: perulangan, array
    static void tampilSemua() {
        if (jumlahData == 0) {
            System.out.println("Belum ada data.");
            return;
        }

        // Data dalam bentuk tabel
        System.out.println("                   Data Semua Pendaftar Beasiswa");
        System.out.println("=====================================================================");
        System.out.printf("| %-15s | %-10s | %-4s | %-10s | %-12s |\n",
                        "Nama", "NIM", "IPK", "Jenis", "Penghasilan");
        System.out.println("=====================================================================");

        for (int i = 0; i < jumlahData; i++) {
            System.out.printf("| %-15s | %-10s | %-4s | %-10s | %-12s |\n",
                            data[i][0],  // Nama
                            data[i][1],  // NIM
                            data[i][2],  // IPK
                            data[i][3],  // Jenis
                            data[i][4]); // Penghasilan
        }
        System.out.println("=====================================================================");
    }


    // Fungsi: cari berdasar jenis beasiswa
    // Tugas: cari dan tampil pendaftar sesuai jenis yang dipilih
    // Jenis kode: kondisi, perulangan
    static void cariJenis() {
        if (jumlahData == 0) {
            System.out.println("Belum ada data.");
            return;
        }

        System.out.println("Cari jenis:");
        System.out.println("1. Reguler");
        System.out.println("2. Unggulan");
        System.out.println("3. Riset");
        System.out.print("Masukkan Jenis Beasiswa: ");
        String jenisBeasiswa = sc.nextLine();

        String jenisDicari = "";
        if (jenisBeasiswa.equals("Reguler")) jenisDicari = "Reguler";
        else if (jenisBeasiswa.equals("Unggulan")) jenisDicari = "Unggulan";
        else if (jenisBeasiswa.equals("Riset")) jenisDicari = "Riset";
        else {
            System.out.println("Pilihan jenis tidak ada.");
            return;
        }

        int ketemu = 0;
        for (int i = 0; i < jumlahData; i++) {
            if (data[i][3].equals(jenisDicari)) {
                ketemu++;
                System.out.println("Nama: " + data[i][0] + ", NIM: " + data[i][1] + ", IPK: " + data[i][2]);
            }
        }

        if (ketemu == 0) {
            System.out.println("Tidak ada pendaftar jenis " + jenisDicari);
        }
    }


     // Fungsi: tampil data terakhir ditambahkan
     // tugas: menampilkan data terakhir yang disimpan
     // jenis kode: array
    static void tampilTerakhir() {
        if (jumlahData == 0) {
            System.out.println("Belum ada data pendaftar.");
            return;
            }

            int idx = jumlahData - 1; // indeks data terakhir

            System.out.println("=== DATA TERAKHIR DITAMBAHKAN ===");
            System.out.println("Nama        : " + data[idx][0]);
            System.out.println("NIM         : " + data[idx][1]);
            System.out.println("IPK         : " + data[idx][2]);
            System.out.println("Jenis       : " + data[idx][3]);
            System.out.println("Penghasilan : " + data[idx][4]);
        }



    // Fungsi: tampil data pertama ditambahkan
    // tugas: menampilkan data pertama yang disimpan
    // jenis kode: array
    static void tampilPertama() {
    if (jumlahData == 0) {
        System.out.println("Belum ada data pendaftar.");
        return;
    }

    System.out.println("=== DATA PERTAMA DITAMBAHKAN ===");
    System.out.println("Nama        : " + data[0][0]);
    System.out.println("NIM         : " + data[0][1]);
    System.out.println("IPK         : " + data[0][2]);
    System.out.println("Jenis       : " + data[0][3]);
    System.out.println("Penghasilan : " + data[0][4]);
}


    // Fungsi:  hitung rata-rata IPK per jenis beasiswa
    // Tugas: hitung rata-rata IPK per jenis dan tampil
    // Jenis kode: perhitungan, kondisi
    static void hitungRataRata() {
        if (jumlahData == 0) {
            System.out.println("Belum ada data.");
            return;
        }

        // total dan hitung masing-masing jenis beasiswa
        double totalReguler = 0;
        double totalUnggulan = 0;
        double totalRiset = 0;
        int hitungReguler = 0;
        int hitungUnggulan = 0;
        int hitungRiset = 0;

        for (int i = 0; i < jumlahData; i++) {
            double nilaiIpk = Double.parseDouble(data[i][2]);
            String jenisBeasiswa = data[i][3];

            if (jenisBeasiswa.equals("Reguler")) {
                totalReguler = totalReguler + nilaiIpk;   // akumulasi
                hitungReguler = hitungReguler + 1;        // jumlah pendaftar Reguler
            } else if (jenisBeasiswa.equals("Unggulan")) {
                totalUnggulan = totalUnggulan + nilaiIpk;
                hitungUnggulan = hitungUnggulan + 1;      // jumlah pendaftar Unggulan
            } else if (jenisBeasiswa.equals("Riset")) {
                totalRiset = totalRiset + nilaiIpk;
                hitungRiset = hitungRiset + 1;          // jumlah pendaftar Riset
            }
        }

        System.out.println("=== RATA-RATA IPK ===");

        // Beasiswa Reguler
        if (hitungReguler > 0) {
            double rataReg = totalReguler / hitungReguler; // rata = total / jumlah
            System.out.println("Reguler  : " + rataReg);
        } else {
            System.out.println("Reguler  : -");
        }

        // Beasiswa Unggulan
        if (hitungUnggulan > 0) {
            double rataUng = totalUnggulan / hitungUnggulan;
            System.out.println("Unggulan : " + rataUng);
        } else {
            System.out.println("Unggulan : -");
        }

        // Beasiswa Riset
        if (hitungRiset > 0) {
            double rataRis = totalRiset / hitungRiset;
            System.out.println("Riset    : " + rataRis);
        } else {
            System.out.println("Riset    : -");
        }
    }
}
