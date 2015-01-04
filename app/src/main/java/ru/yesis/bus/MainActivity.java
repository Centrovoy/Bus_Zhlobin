package ru.yesis.bus;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity implements OnClickListener {

    ArrayList<String> listItems = new ArrayList<String>();

    ArrayAdapter<String> adapter;

    final String LOG_TAG = "myLogs";

    DataBaseHelper dbHelper;
    SQLiteDatabase db;

    /** Called when the activity is first created. */

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Очищаем курсор
        Cursor c = null;
        try {
            //Вызываем метод для чтения данных из таблицы в лог
            readtolog(c);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //Метод для вывода данных из sqlite в лог
    void readtolog (Cursor c) throws IOException
    {

        //Создаем объект dbHelper
        dbHelper = new DataBaseHelper(this);

        // Подключаемся к базе
        db = dbHelper.getWritableDatabase();

        String q = "SELECT number FROM bus_number";
        Cursor cursor = db.rawQuery(q, null);

//        if (cursor != null) {
//            Log.d(LOG_TAG, "Records count = " + cursor.getCount());
//            if (cursor.moveToFirst()) {
//                do {
//                    Log.d(LOG_TAG, cursor.getString(cursor.getColumnIndex("number")));
//                } while (cursor.moveToNext());
//            }
//        }


        ArrayList<String> values = new ArrayList<String>();
        while (cursor.moveToNext()) {
            values.add("Автобус № " + cursor.getString(cursor.getColumnIndex("number")));
        }
        cursor.close();
        // Now create a simple cursor adapter and set it to display


//        String [] m = {"text1","text2","text3"};
        ListView lvMain = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);
        lvMain.setAdapter(adapter);

    // Вывод сообщения по тапу
        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

//                String item = ((TextView)view).getText().toString();
//
//                Toast.makeText(getBaseContext(), item, Toast.LENGTH_SHORT).show();

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                if (id == 6) {
                    builder.setTitle("Временное рассписание!")

                            .setMessage("5-25, 5-35, 5-42, 5-50, 5-55, 6-06, 6-20, 6-25, 6-35, 6-43, 6-53, 7-01, 7-08, 7-12, 7-24, 7-39, 7-43, 7-55, 8-01, 8-15, 8-30, 9-10, 9-45, 10-45, 11-10, 12-12, 12-30, 13-00, 13-32, 13-45, 14-05, 14-15, 14-25ПТ, 14-30, 14-40 ПТ, 14-50, 15-06, 15-20, 15-25РД, 15-40, 15-45РД, 15-55РД, 16-03, 16-08РД, 16-17, 16-28 РД, 16-39, 17-11, 17-30, 17-55, 18-00, 18-25, 18-50, 19-15, 19-45, 20-10, 20-35, 21-05, 21-30, 22-01")
                            .setCancelable(false)
                            .setNegativeButton("Спасибо Денису",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });

                }
                else if (id == 19) {
                    builder.setTitle("Временное рассписание!")

                            .setMessage("5-30, 5-40, 5-49, 5-53, 5-58, 6-15, 6-22, 6-38, 6-48, 6-55, 7-00, 7-03, 7-06, 7-10, 7-14, 7-30, 7-45, 8-05, 8-20, 8-50, 9-25, 10-00, 10-20, 10-30,11-00, 11-40, 11-55, 12-45, 13-15, 13-23 ПТ, 13-55, 14-10, 14-22, 14-38, 14-45, 15-01, 15-10, 15-15, 15-35, 15-45ПТ, 15-58, 16-13, 16-22, 16-26 РД, 16-32 РД, 16-41РД, 16-55, 17-51, 18-08, 18-30, 19-02,19-28, 19-50, 20-22, 20-50, 21-18, 21-45, 22-15, 22-42")
                            .setCancelable(false)
                            .setNegativeButton("Спасибо Денису",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });

                }
                else{
                    builder.setTitle("Временное рассписание отсутствует!")
                            .setCancelable(false)
                            .setNegativeButton("Всё равно спасибо Денису :)",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });
                }

                AlertDialog alert = builder.create();
                alert.show();

            }
        });




//        read(db, "buss");
//
////        Log.d(LOG_TAG, "-— Назначаем курсору С данные из БД —-");
//
//
////        Cursor cursor  = db.query("bus_number", null, null, null, null, null, null);
////
////        while (cursor.moveToNext()) {
////            // GET COLUMN INDICES + VALUES OF THOSE COLUMNS
////            int id = cursor.getInt(cursor.getColumnIndex(cursor));
////            String name = cursor.getString(cursor
////                    .getColumnIndex(cursor));
////            Log.i("LOG_TAG", "ROW " + id + " HAS NAME " + name);
////        }
//
//
//        Log.d(LOG_TAG, "-— Данные назначены курсору С —-");
//
//

//
//
//
//
////        if (c != null) {
////            if (c.moveToFirst()) {
////                String str;
////
////                do {
////                    str = "";
////                    for (String cn : c.getColumnNames()) {
////                        //str = str.concat(c.getString(c.getColumnIndex(cn)));
////                        str = c.getString(c.getColumnIndex(cn));
////
////                        System.out.println("Автобус №" + str);
////
////
////                    }
////
////
////                    Log.d(LOG_TAG, str);
////
////                } while (c.moveToNext());
////            }
////            c.close();
////        } else
////            Log.d(LOG_TAG, "Cursor is null");
//
//
//
//
//        dbHelper.close();
//    }

//    void read(SQLiteDatabase db, String table) {
//        Log.d(LOG_TAG, "Read table " + table);
//        Cursor c = db.query("bus_number", null, null, null, null, null, null);
//        if (c != null) {
//            Log.d(LOG_TAG, "Records count = " + c.getCount());
//            if (c.moveToFirst()) {
//                do {
//                    Log.d(LOG_TAG, c.getString(c.getColumnIndex("val")));
//                } while (c.moveToNext());
//            }
//            c.close();
//        }
   }


    public class DataBaseHelper extends SQLiteOpenHelper {
        private Context mycontext;

        ContextWrapper cw =new ContextWrapper(getApplicationContext());

        private static final String DB_NAME = "buss"; //Расширение может быть только .sqlite или .db
        public SQLiteDatabase myDataBase;

        private String DB_PATH = "/data/data/ru.yesis.bus/databases/";



        public DataBaseHelper(Context context) throws IOException {
            super(context, DB_NAME, null, 1);
            this.mycontext=context;
            boolean dbexist = checkdatabase();
            if (dbexist) {
                //System.out.println("База данных существует");
                opendatabase();
            } else {
                System.out.println("База данных не существует!");
                createdatabase();
            }
        }

        //Проверка, существует ли БД
        public void createdatabase() throws IOException {
            boolean dbexist = checkdatabase();
            if(dbexist) {
                //System.out.println("База данных существует");
            } else {
                this.getReadableDatabase();
                copydatabase();
            }
        }

        //Проверка, существует ли БД
        private boolean checkdatabase() {
            //SQLiteDatabase checkdb = null;
            boolean checkdb = false;
            try {
                String myPath = DB_PATH + DB_NAME;
                File dbfile = new File(myPath);
                checkdb = dbfile.exists();
            } catch(SQLiteException e) {
                System.out.println("База данных не существует!");
            }
            return checkdb;
        }

        //Копирование БД
        private void copydatabase()
        {
            Log.i("Database",
                    "Новая база данных копируется на устройство!");
            byte[] buffer = new byte[1024];
            OutputStream myOutput = null;
            int length;
            // Открываем локальную БД как входящий поток
            InputStream myInput = null;
            try
            {
                myInput = mycontext.getAssets().open(DB_NAME);
                // Передаем данные из inputfile в outputfile
                myOutput = new FileOutputStream(DB_PATH + DB_NAME);
                while((length = myInput.read(buffer)) > 0)
                {
                    myOutput.write(buffer, 0, length);
                }
                myOutput.close();
                myOutput.flush();
                myInput.close();
                Log.i("Database",
                        "Новая база данных скопирована на устройство");


            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }

        //Открытие БД
        public void opendatabase() throws SQLException {
            // Открываем базу данных
            String mypath = DB_PATH + DB_NAME;
            myDataBase = SQLiteDatabase.openDatabase(mypath, null, SQLiteDatabase.OPEN_READWRITE);
        }

        //Закрытие БД
        public synchronized void close() {
            if(myDataBase != null) {
                myDataBase.close();
            }
            super.close();
        }

        @Override
        public void onCreate(SQLiteDatabase arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
            // TODO Auto-generated method stub

        }

    }

    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub

    }

}
