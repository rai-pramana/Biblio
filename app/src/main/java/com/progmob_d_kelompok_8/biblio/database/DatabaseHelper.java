package com.progmob_d_kelompok_8.biblio.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

//    public static final String TAG = DatabaseHelper.class.getSimpleName();
    private static final String DATABASE_NAME = "db_biblio_last"; // .db
    private static final int DATABASE_VERSION = 16;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        // tb_penulis
//        String query_create_tb_penulis =
//                "CREATE TABLE tb_penulis" +
//                        "(" +
//                        "id_penulis INTEGER PRIMARY KEY AUTOINCREMENT," +
//                        "nama_penulis VARCHAR(30)" +
//                        ");"
//                ;
//        db.execSQL(query_create_tb_penulis);
//
//        // tb_penerbit
//        String query_create_tb_penerbit =
//                "CREATE TABLE tb_penerbit" +
//                        "(" +
//                        "id_penerbit INTEGER PRIMARY KEY AUTOINCREMENT," +
//                        "nama_penerbit VARCHAR(30)" +
//                        ");"
//                ;
//        db.execSQL(query_create_tb_penerbit);
//
//        // tb_genre
//        String query_create_tb_genre =
//                "CREATE TABLE tb_genre" +
//                        "(" +
//                        "id_genre INTEGER PRIMARY KEY AUTOINCREMENT," +
//                        "nama_genre VARCHAR(30)" +
//                        ");"
//                ;
//        db.execSQL(query_create_tb_genre);
//
//        // tb_role
//        String query_create_tb_role =
//                "CREATE TABLE tb_role" +
//                        "(" +
//                        "id_role INTEGER PRIMARY KEY AUTOINCREMENT," +
//                        "nama_role VARCHAR(30)" +
//                        ");"
//                ;
//        db.execSQL(query_create_tb_role);
//
//        // tb_jenis_buku
//        String query_create_tb_jenis_buku =
//                "CREATE TABLE tb_jenis_buku" +
//                        "(" +
//                        "id_jenis_buku INTEGER PRIMARY KEY AUTOINCREMENT," +
//                        "nama_jenis_buku VARCHAR(30)" +
//                        ");"
//                ;
//        db.execSQL(query_create_tb_jenis_buku);
//
//        // tb_pengguna
//        String query_create_tb_pengguna =
//                "CREATE TABLE tb_pengguna" +
//                        "(" +
//                        "id_pengguna INTEGER PRIMARY KEY AUTOINCREMENT," +
//                        "id_role INTEGER," +
//                        "nama VARCHAR(30)," +
//                        "email VARCHAR(30)," +
//                        "no_hp VARCHAR(15)," +
//                        "jenis_kelamin CHAR(1)," +
//                        "alamat VARCHAR(80), " +
//                        "tgl_gabung DATE," +
//                        "FOREIGN KEY(id_role) REFERENCES tb_role(id_role) ON DELETE CASCADE ON UPDATE CASCADE" +
//                        ");"
//                ;
//        db.execSQL(query_create_tb_pengguna);
//
//        // tb_buku
//        String query_create_tb_buku =
//                "CREATE TABLE tb_buku" +
//                        "(" +
//                        "id_buku INTEGER PRIMARY KEY AUTOINCREMENT," +
//                        "id_pengguna INTEGER," +
//                        "id_jenis_buku INTEGER," +
//                        "id_penerbit INTEGER," +
//                        "judul_buku VARCHAR(30)," +
//                        "tgl_terbit DATE," +
//                        "sinopsis TEXT," +
//                        "skor REAL," +
//                        "gambar_sampul MEDIUMBLOB," +
//                        "jumlah_pembaca INTEGER," +
//                        "FOREIGN KEY(id_pengguna) REFERENCES tb_pengguna(id_pengguna) ON DELETE CASCADE ON UPDATE CASCADE," +
//                        "FOREIGN KEY(id_jenis_buku) REFERENCES tb_jenis_buku(id_jenis_buku) ON DELETE CASCADE ON UPDATE CASCADE," +
//                        "FOREIGN KEY(id_penerbit) REFERENCES tb_penerbit(id_penerbit) ON DELETE CASCADE ON UPDATE CASCADE" +
//                        ");"
//                ;
//        db.execSQL(query_create_tb_buku);
//
//        // tb_penulis_detail
//        String query_create_tb_penulis_detail =
//                "CREATE TABLE tb_penulis_detail" +
//                        "(" +
//                        "id_penulis INTEGER," +
//                        "id_buku INTEGER," +
//                        "FOREIGN KEY(id_penulis) REFERENCES tb_penulis(id_penulis) ON DELETE CASCADE ON UPDATE CASCADE," +
//                        "FOREIGN KEY(id_buku) REFERENCES tb_buku(id_buku) ON DELETE CASCADE ON UPDATE CASCADE" +
//                        ");"
//                ;
//        db.execSQL(query_create_tb_penulis_detail);
//
//        // tb_genre_detail
//        String query_create_tb_genre_detail =
//                "CREATE TABLE tb_genre_detail" +
//                        "(" +
//                        "id_genre INTEGER," +
//                        "id_buku INTEGER," +
//                        "FOREIGN KEY(id_genre) REFERENCES tb_genre(id_genre) ON DELETE CASCADE ON UPDATE CASCADE," +
//                        "FOREIGN KEY(id_buku) REFERENCES tb_buku(id_buku) ON DELETE CASCADE ON UPDATE CASCADE" +
//                        ");"
//                ;
//        db.execSQL(query_create_tb_genre_detail);
//
//        // tb_list
//        String query_create_tb_list =
//                "CREATE TABLE tb_list" +
//                        "(" +
//                        "id_pengguna INTEGER PRIMARY KEY," +
//                        "id_list INTEGER," +
//                        "selesai INTEGER," +
//                        "rencana_dibaca INTEGER," +
//                        "sedang_dibaca INTEGER," +
//                        "dijatuhkan INTEGER," +
//                        "FOREIGN KEY(id_pengguna) REFERENCES tb_pengguna(id_pengguna) ON DELETE CASCADE ON UPDATE CASCADE" +
//                        ");"
//                ;
//        db.execSQL(query_create_tb_list);
//
//        // tb_list_detail
//        String query_create_tb_list_detail =
//                "CREATE TABLE tb_list_detail" +
//                        "(" +
//                        "id_pengguna INTEGER PRIMARY KEY," +
//                        "id_buku INTEGER," +
//                        "status_baca VARCHAR(30)," +
//                        "status_favorit INTEGER," +
//                        "skor REAL," +
//                        "review TEXT," +
//                        "FOREIGN KEY(id_pengguna) REFERENCES tb_pengguna(id_pengguna) ON DELETE CASCADE ON UPDATE CASCADE," +
//                        "FOREIGN KEY(id_buku) REFERENCES tb_buku(id_buku) ON DELETE CASCADE ON UPDATE CASCADE" +
//                        ");"
//                ;
//        db.execSQL(query_create_tb_list_detail);
//

        // tb_pengguna
        String query_create_tb_pengguna =
                "CREATE TABLE tb_pengguna" +
                        "(" +
                        "id_pengguna INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "role VARCHAR(30) DEFAULT 'Pengguna'," +
                        "nama VARCHAR(30) DEFAULT 'Pengguna'," +
                        "email VARCHAR(30)," +
                        "password_pengguna VARCHAR(88)," +
                        "no_hp VARCHAR(15) DEFAULT '08XXXXXXXXXX'," +
                        "jenis_kelamin VARCHAR(15) DEFAULT 'Pria/Wanita'," +
                        "alamat VARCHAR(80) DEFAULT 'Jl. XX', " +
                        "foto BLOB, " +
                        "tgl_gabung DATE DEFAULT (strftime('%Y-%m-%d', 'now', 'localtime'))," +
                        "selesai INTEGER DEFAULT 0," +
                        "rencana_dibaca INTEGER DEFAULT 0," +
                        "sedang_dibaca INTEGER DEFAULT 0," +
                        "dijatuhkan INTEGER DEFAULT 0," +
                        "skor_mean REAL DEFAULT 0.0" +
                        ");"
                ;
        db.execSQL(query_create_tb_pengguna);

        // tb_buku
        String query_create_tb_buku =
                "CREATE TABLE tb_buku" +
                        "(" +
                        "id_buku INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "id_pengguna INTEGER," +
                        "nama_jenis_buku VARCHAR(30)," +
                        "nama_genre VARCHAR(30)," +
                        "nama_penulis VARCHAR(30)," +
                        "nama_penerbit VARCHAR(30)," +
                        "judul_buku VARCHAR(30)," +
                        "tgl_terbit DATE," +
                        "sinopsis TEXT," +
                        "skor REAL DEFAULT 0.0," +
                        "gambar_sampul BLOB," +
                        "jumlah_pembaca INTEGER DEFAULT 0," +
                        "peringkat INTEGER DEFAULT 0," +
                        "FOREIGN KEY(id_pengguna) REFERENCES tb_pengguna(id_pengguna) ON DELETE CASCADE ON UPDATE CASCADE" +
                        ");"
                ;
        db.execSQL(query_create_tb_buku);

        // tb_list_detail
        String query_create_tb_list_detail =
                "CREATE TABLE tb_list_detail" +
                        "(" +
                        "id_pengguna INTEGER," +
                        "id_buku INTEGER," +
                        "status_baca VARCHAR(30) DEFAULT 'Rencana Dibaca'," +
                        "status_favorit VARCHAR(30) DEFAULT 'Normal'," +
                        "skor VARCHAR(30) DEFAULT 'null'," +
                        "review TEXT DEFAULT 'Belum direview'," +
                        "tgl_mulai DATE DEFAULT (strftime('%Y-%m-%d', 'now', 'localtime'))," +
                        "tgl_selesai DATE DEFAULT (strftime('%Y-%m-%d', 'now', 'localtime'))," +
                        "FOREIGN KEY(id_pengguna) REFERENCES tb_pengguna(id_pengguna) ON DELETE CASCADE ON UPDATE CASCADE," +
                        "FOREIGN KEY(id_buku) REFERENCES tb_buku(id_buku) ON DELETE CASCADE ON UPDATE CASCADE" +
                        ");"
                ;
        db.execSQL(query_create_tb_list_detail);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
//        db.execSQL("DROP TABLE IF EXISTS tb_penulis");
//        db.execSQL("DROP TABLE IF EXISTS tb_penerbit");
//        db.execSQL("DROP TABLE IF EXISTS tb_genre");
//        db.execSQL("DROP TABLE IF EXISTS tb_role");
//        db.execSQL("DROP TABLE IF EXISTS tb_jenis_buku");
//        db.execSQL("DROP TABLE IF EXISTS tb_pengguna");
//        db.execSQL("DROP TABLE IF EXISTS tb_buku");
//        db.execSQL("DROP TABLE IF EXISTS tb_penulis_detail");
//        db.execSQL("DROP TABLE IF EXISTS tb_genre_detail");
//        db.execSQL("DROP TABLE IF EXISTS tb_list");
//        db.execSQL("DROP TABLE IF EXISTS tb_list_detail");

        db.execSQL("DROP TABLE IF EXISTS tb_pengguna");
        db.execSQL("DROP TABLE IF EXISTS tb_buku");
        db.execSQL("DROP TABLE IF EXISTS tb_list_detail");

        onCreate(db);
    }

    public void addUser(String email, String password, byte[] foto) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("email", email);
        values.put("password_pengguna", password);
        values.put("foto", foto);

        db.insert("tb_pengguna", null, values);
    }

    public boolean getUser(String email, String pass){
        String selectQuery = "select * from  " + "tb_pengguna" + " where " +
                "email" + " = " + "'"+email+"'" + " and " + "password_pengguna" + " = " + "'"+pass+"'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            return true;
        }

        cursor.close();

        return false;
    }

    public int getUserId(String email, String pass){
        String selectQuery = "select * from  " + "tb_pengguna" + " where " +
                "email" + " = " + "'"+email+"'" + " and " + "password_pengguna" + " = " + "'"+pass+"'  LIMIT 1";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        cursor.moveToFirst();

        int indexIdPengguna = cursor.getColumnIndex("id_pengguna");
        int idPengguna = cursor.getInt(indexIdPengguna);

        cursor.close();

        return idPengguna;
    }

    public boolean isUserAdmin(int userId){
        String selectQuery = "select * from  " + "tb_pengguna" + " where " +
                "id_pengguna" + " = " + "'"+userId+"'" + " and " +  "role" + " = " + "'Admin' LIMIT 1";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            return true;
        }

        cursor.close();

        return false;
    }

    public void addBook(int userId
            , String namaJenisBuku
            , String namaGenre
            , String namaPenulis
            , String namaPenerbit
            , String judulBuku
            , String tglTerbit
            , String sinops
            , byte[] gambarSampul){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valuess = new ContentValues();
        valuess.put("id_pengguna", userId);
        valuess.put("nama_jenis_buku", namaJenisBuku);
        valuess.put("nama_genre", namaGenre);
        valuess.put("nama_penulis", namaPenulis);
        valuess.put("nama_penerbit", namaPenerbit);
        valuess.put("judul_buku", judulBuku);
        valuess.put("tgl_terbit", tglTerbit);
        valuess.put("sinopsis", sinops);
        valuess.put("gambar_sampul", gambarSampul);

        db.insert("tb_buku", null, valuess);

        db.close();
    }

    public boolean getBook(String judulBuku, String tglTerbit){
        //HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "select * from  " + "tb_buku" + " where " +
                "judul_buku" + " = " + "'"+judulBuku+"'" + " and " + "tgl_terbit" + " = " + "'"+tglTerbit+"'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            return true;
        }

        cursor.close();

        return false;
    }

    public Cursor getAllBookData(){
        String selectQuery = "select id_buku, id_pengguna, nama_jenis_buku, nama_genre, nama_penulis, nama_penerbit, judul_buku, strftime('%Y', tgl_terbit), sinopsis, skor, gambar_sampul, jumlah_pembaca, peringkat from tb_buku";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        return cursor;
    }

    public Cursor getBook(int bookId){
        String selectQuery = "select id_buku, id_pengguna, nama_jenis_buku, nama_genre, nama_penulis, nama_penerbit, judul_buku, strftime('%Y', tgl_terbit), sinopsis, skor, gambar_sampul, jumlah_pembaca, peringkat from tb_buku where id_buku =" + "'"+bookId+"'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        return cursor;
    }

    public Cursor getBookDate(int bookId){
        String selectQuery = "select * from tb_buku where id_buku =" + "'"+bookId+"'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        return cursor;
    }

//    public void updateData(int idBuku
//            , String namaJenisBuku
//            , String namaGenre
//            , String namaPenulis
//            , String namaPenerbit
//            , String judulBuku
//            , String tglTerbit
//            , String sinops
//            , byte[] gambarSampul) {
//        SQLiteDatabase database = getWritableDatabase();
//
//        String query = "UPDATE tb_buku SET " +
//                "nama_jenis_buku = ?, " +
//                "nama_genre = ?, " +
//                "nama_penulis = ?, " +
//                "nama_penerbit = ?, " +
//                "judul_buku = ?, " +
//                "tgl_terbit = ?, " +
//                "sinopsis = ?, " +
//                "gambar_sampul = ? " +
//                "WHERE id = ?";
//        SQLiteStatement statement = database.compileStatement(query);
//
//        statement.bindString(1, namaJenisBuku);
//        statement.bindString(2, namaGenre);
//        statement.bindBlob(, gambarSampul);
//        statement.bindDouble(4, (double)idBuku);
//
//        statement.execute();
//        database.close();
//    }

    public void updateBookData(int idBuku
            , String namaJenisBuku
            , String namaGenre
            , String namaPenulis
            , String namaPenerbit
            , String judulBuku
            , String tglTerbit
            , String sinops
            , byte[] gambarSampul){

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("PRAGMA foreign_keys=ON");
        ContentValues cv = new ContentValues();

        cv.put("nama_jenis_buku", namaJenisBuku);
        cv.put("nama_genre", namaGenre);
        cv.put("nama_penulis", namaPenulis);
        cv.put("nama_penerbit", namaPenerbit);
        cv.put("judul_buku", judulBuku);
        cv.put("tgl_terbit", tglTerbit);
        cv.put("sinopsis", sinops);
        cv.put("gambar_sampul", gambarSampul);

        db.update("tb_buku", cv, "id_buku=?", new String[]{String.valueOf(idBuku)});
    }

    public void deleteOneBook(int idBuku){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("PRAGMA foreign_keys=ON");

        db.delete("tb_buku", "id_buku=?", new String[]{String.valueOf(idBuku)});
    }

    public void addUserData(String role
            , String nama
            , String email
            , String password_pengguna
            , String no_hp
            , String jenis_kelamin
            , String alamat
            , byte[] foto){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valuess = new ContentValues();
        valuess.put("role", role);
        valuess.put("nama", nama);
        valuess.put("email", email);
        valuess.put("password_pengguna", password_pengguna);
        valuess.put("no_hp", no_hp);
        valuess.put("jenis_kelamin", jenis_kelamin);
        valuess.put("alamat", alamat);
        valuess.put("foto", foto);

        db.insert("tb_pengguna", null, valuess);

        db.close();
    }

    public Cursor getAllUserDataUnlessOne(int idUser){
        String selectQuery = "select * from tb_pengguna where id_pengguna !=" + "'"+idUser+"'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        return cursor;
    }

    public Cursor getOneUserData(int idUser){
        String selectQuery = "select * from tb_pengguna where id_pengguna =" + "'"+idUser+"'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        return cursor;
    }

    public void deleteOneUser(int idUser){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("PRAGMA foreign_keys=ON");

        db.delete("tb_pengguna", "id_pengguna=?", new String[]{String.valueOf(idUser)});
    }

    public void updateUserData(int idUser
            , String role
            , String nama
            , String email
            , String password_pengguna
            , String no_hp
            , String jenis_kelamin
            , String alamat
            , byte[] foto){

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("PRAGMA foreign_keys=ON");
        ContentValues cv = new ContentValues();

        cv.put("role", role);
        cv.put("nama", nama);
        cv.put("email", email);
        cv.put("password_pengguna", password_pengguna);
        cv.put("no_hp", no_hp);
        cv.put("jenis_kelamin", jenis_kelamin);
        cv.put("alamat", alamat);
        cv.put("foto", foto);

        db.update("tb_pengguna", cv, "id_pengguna=?", new String[]{String.valueOf(idUser)});
    }

    public void addList(int id_pengguna
            , int id_buku
            , String status_baca
            , String status_favorit
            , String skor
            , String review
            , String tgl_mulai
            , String tgl_selesai){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valuess = new ContentValues();

        valuess.put("id_pengguna", id_pengguna);
        valuess.put("id_buku", id_buku);
        valuess.put("status_baca", status_baca);
        valuess.put("status_favorit", status_favorit);
        valuess.put("skor", skor);
        valuess.put("review", review);
        valuess.put("tgl_mulai", tgl_mulai);
        valuess.put("tgl_selesai", tgl_selesai);

        db.insert("tb_list_detail", null, valuess);
    }

    public void updateList(int id_pengguna
            , int id_buku
            , String status_baca
            , String status_favorit
            , String skor
            , String review
            , String tgl_mulai
            , String tgl_selesai){

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("PRAGMA foreign_keys=ON");
        ContentValues cv = new ContentValues();

        cv.put("id_pengguna", id_pengguna);
        cv.put("id_buku", id_buku);
        cv.put("status_baca", status_baca);
        cv.put("status_favorit", status_favorit);
        cv.put("skor", skor);
        cv.put("review", review);
        cv.put("tgl_mulai", tgl_mulai);
        cv.put("tgl_selesai", tgl_selesai);

        db.update("tb_list_detail", cv, "id_pengguna=? AND id_buku=?", new String[]{String.valueOf(id_pengguna), String.valueOf(id_buku)});
    }

    public void deleteList(int idPengguna, int idBuku){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("PRAGMA foreign_keys=ON");

        db.delete("tb_list_detail", "id_pengguna=? AND id_buku=?", new String[]{String.valueOf(idPengguna), String.valueOf(idBuku)});
    }

    public Cursor getUserList(int idPengguna, int idBuku){
        String selectQuery = "select * from tb_list_detail " +
                "where id_pengguna =" + "'" + idPengguna + "' and id_buku='" + idBuku + "'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        return cursor;
    }

    public Cursor getAllUserList(int idPengguna){
        String selectQuery = "select * from tb_buku " +
                "inner join tb_list_detail on tb_list_detail.id_buku = tb_buku.id_buku " +
                "where tb_list_detail.id_pengguna =" + "'" + idPengguna + "'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        return cursor;
    }

    public boolean isListed(int idPengguna, int idBuku){
        String selectQuery = "select * from  " + "tb_list_detail" + " where " +
                "id_pengguna" + " = " + "'"+idPengguna+"'" + " and " + "id_buku" + " = " + "'"+idBuku+"'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            return true;
        }

        cursor.close();

        return false;
    }

    public boolean isBookListed(int idBuku){
        String selectQuery = "select distinct * from  " + "tb_list_detail" + " where " +
                "id_buku" + " = " + "'"+idBuku+"'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            return true;
        }

        cursor.close();

        return false;
    }

    public float getAverageBookScore(int idBuku){
        String selectQuery = "select ifnull(avg(skor),0.0) from tb_list_detail " +
                "where id_buku =" + "'" + idBuku + "' and skor != '-'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();

        float score = cursor.getFloat(0);

        cursor.close();

        return score;
    }

    public int getBookStatisticJumlahPembaca(int idBuku){
        String selectQuery = "select ifnull(count(id_buku),0) from tb_list_detail " +
                "where id_buku =" + "'" + idBuku + "'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();

        int count = cursor.getInt(0);

        cursor.close();

        return count;
    }

    public void updateBookStatistic(int idBuku){

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("PRAGMA foreign_keys=ON");
        ContentValues cv = new ContentValues();

        cv.put("skor", getAverageBookScore(idBuku));
        cv.put("jumlah_pembaca", getBookStatisticJumlahPembaca(idBuku));

        db.update("tb_buku", cv, "id_buku=?", new String[]{String.valueOf(idBuku)});
    }

    public float getAverageUserScore(int idUser){
        String selectQuery = "select ifnull(avg(skor),0.0) from tb_list_detail " +
                "where id_pengguna =" + "'" + idUser + "' and skor != '-'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();

        float score = cursor.getFloat(0);

        cursor.close();

        return score;
    }

    public int getUserStatisticSelesai(int idPengguna){
        String selectQuery = "select ifnull(count(id_pengguna),0) from tb_list_detail " +
                "where id_pengguna =" + "'" + idPengguna + "' and status_baca = 'Selesai'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();

        int count = cursor.getInt(0);

        cursor.close();

        return count;
    }

    public int getUserStatisticRencanaDibaca(int idPengguna){
        String selectQuery = "select ifnull(count(id_pengguna),0) from tb_list_detail " +
                "where id_pengguna =" + "'" + idPengguna + "' and status_baca = 'Rencana Dibaca'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();

        int count = cursor.getInt(0);

        cursor.close();

        return count;
    }

    public int getUserStatisticSedangDibaca(int idPengguna){
        String selectQuery = "select ifnull(count(id_pengguna),0) from tb_list_detail " +
                "where id_pengguna =" + "'" + idPengguna + "' and status_baca = 'Sedang Dibaca'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();

        int count = cursor.getInt(0);

        cursor.close();

        return count;
    }

    public int getUserStatisticDijatuhkan(int idPengguna){
        String selectQuery = "select ifnull(count(id_pengguna),0) from tb_list_detail " +
                "where id_pengguna =" + "'" + idPengguna + "' and status_baca = 'Dijatuhkan'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();

        int count = cursor.getInt(0);

        cursor.close();

        return count;
    }

    public void updateUserStatistic(int idUser){

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("PRAGMA foreign_keys=ON");
        ContentValues cv = new ContentValues();

        cv.put("selesai", getUserStatisticSelesai(idUser));
        cv.put("rencana_dibaca", getUserStatisticRencanaDibaca(idUser));
        cv.put("sedang_dibaca", getUserStatisticSedangDibaca(idUser));
        cv.put("dijatuhkan", getUserStatisticDijatuhkan(idUser));
        cv.put("skor_mean", getAverageUserScore(idUser));

        db.update("tb_pengguna", cv, "id_pengguna=?", new String[]{String.valueOf(idUser)});
    }

    public void updateAllUserStatistic(){
        String selectQuery = "select id_pengguna from tb_pengguna";

        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("PRAGMA foreign_keys=ON");

        Cursor cursor = db.rawQuery(selectQuery, null);

        while (cursor.moveToNext()){
            updateUserStatistic(cursor.getInt(0));
        }

        cursor.close();
    }

    public void updateOneBookRank(int idBuku, int rank){

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("PRAGMA foreign_keys=ON");
        ContentValues cv = new ContentValues();

        cv.put("peringkat", rank);

        db.update("tb_buku", cv, "id_buku=?", new String[]{String.valueOf(idBuku)});
    }

    public void updateBookRank(){
        String selectQuery = "select id_buku from tb_buku order by skor desc,jumlah_pembaca desc";

        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("PRAGMA foreign_keys=ON");

        Cursor cursor = db.rawQuery(selectQuery, null);

        int rank = 0;

        while (cursor.moveToNext()){
            rank++;
            updateOneBookRank(cursor.getInt(0), rank);
        }

        cursor.close();
    }

    public void updateAllBookStatistic(){
        String selectQuery = "select id_buku from tb_buku";

        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("PRAGMA foreign_keys=ON");

        Cursor cursor = db.rawQuery(selectQuery, null);

        while (cursor.moveToNext()){
            updateBookStatistic(cursor.getInt(0));
        }

        cursor.close();

        updateBookRank();
    }

    public Cursor getReviewedBookData(int idUser, String status_baca){
        String selectQuery = "select l.id_buku, b.judul_buku, b.tgl_terbit, l.tgl_mulai, l.tgl_selesai, l.review, l.skor, b.gambar_sampul from tb_list_detail as l inner join tb_buku as b on b.id_buku = l.id_buku where l.id_pengguna =" + "'" +idUser+ "' and l.status_baca = '" +status_baca+ "'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        return cursor;
    }

    public Cursor getReviewedFavoriteBookData(int idUser, String statusFavorit){
        String selectQuery = "select l.id_buku, b.judul_buku, b.tgl_terbit, l.tgl_mulai, l.tgl_selesai, l.review, l.skor, b.gambar_sampul from tb_list_detail as l inner join tb_buku as b on b.id_buku = l.id_buku where l.id_pengguna =" + "'" +idUser+ "' and l.status_favorit = '" +statusFavorit+ "'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        return cursor;
    }

    public Cursor getReviewBook(int idBuku){
        String selectQuery = "select p.id_pengguna, p.nama, l.tgl_selesai, l.review, l.skor, p.foto from tb_list_detail as l inner join tb_pengguna as p on p.id_pengguna = l.id_pengguna where id_buku ='"+idBuku+"' and review != ''";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        return cursor;
    }

    public Cursor getAllBookDataRank(){
        String selectQuery = "select id_buku, id_pengguna, nama_jenis_buku, nama_genre, nama_penulis, nama_penerbit, judul_buku, strftime('%Y', tgl_terbit), sinopsis, skor, gambar_sampul, jumlah_pembaca, peringkat from tb_buku order by peringkat asc, jumlah_pembaca desc";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        return cursor;
    }

    public Cursor getAllBookDataReader(){
        String selectQuery = "select id_buku, id_pengguna, nama_jenis_buku, nama_genre, nama_penulis, nama_penerbit, judul_buku, strftime('%Y', tgl_terbit), sinopsis, skor, gambar_sampul, jumlah_pembaca, peringkat from tb_buku order by jumlah_pembaca desc, peringkat asc";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        return cursor;
    }
}
