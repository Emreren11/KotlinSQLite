package com.emre.kotlinsqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {

            //Veri tabanı oluşturma
            val myDatabase = this.openOrCreateDatabase("Musicians", MODE_PRIVATE,null) // Veritabanı ismi

            //Sql Kodları
            myDatabase.execSQL("create table if not exists musicians(id integer primary key, name varchar, age int)") // Tablo oluşturur ve ismini musicians verir
            //myDatabase.execSQL("insert into musicians (name, age) values ('James', 50)") // Sql olduğu için tek tırnak kullanılır
            //myDatabase.execSQL("insert into musicians (name, age) values ('Kirk', 50)") // Sql olduğu için tek tırnak kullanılır
            //myDatabase.execSQL("delete from musicians where id = 2")
            //myDatabase.execSQL("update musicians set name = 'Kirk Hemmet' where name = 'Kirk'")

            //Database verileri çekme
            val cursor = myDatabase.rawQuery("select * from musicians", null) // selectionArg - filtre yapılacak mı?
            //val cursor = myDatabase.rawQuery("select * from musicians where name like '%s'", null) // %s -> s ile biten (s% -> s ile başlayan)

            val nameIx = cursor.getColumnIndex("name") // name'in indexini verir
            val ageIx = cursor.getColumnIndex("age")
            val idIx = cursor.getColumnIndex("id")

            while (cursor.moveToNext()) { // tek tek hücreleri gezer. While kullandık çünkü son veriye kadar işlem yapılacak
                println("ID: " + cursor.getInt(idIx))
                println("Name: " + cursor.getString(nameIx))
                println("Age: " + cursor.getString(ageIx))

            }
            cursor.close()


        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}