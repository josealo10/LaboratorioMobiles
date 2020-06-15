package com.example.matricula.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RelDatabase extends SQLiteOpenHelper {

    private final String usuario =
            "create table Usuario(" +
                    "usuario varchar(9)," +
                    "clave text," +
                    "rol text," +
                    "constraint pkUsuario primary key(usuario))";

    private final String estudiante =
            "create table Estudiante(" +
                    "cedula varchar(9)," +
                    "nombre text," +
                    "apellidos text," +
                    "edad int," +
                    "usuario varchar(9)," +
                    "constraint pkEstudiante primary key(cedula)," +
                    "constraint fk1Estudiante foreign key(usuario) references Usuario(usuario) on delete cascade);";

    private final String curso =
            "create table Curso(" +
                    "codigo varchar(6)," +
                    "nombre text," +
                    "creditos int," +
                    "constraint pkCurso primary key(codigo));";

    private final String matriculado =
            "create table Matriculado(" +
                    "estudiante varchar(9)," +
                    "curso varchar(6)," +
                    "constraint pkMatriculado primary key(estudiante, curso)," +
                    "constraint fk1Matriculado foreign key(estudiante) references Estudiante(cedula) on delete cascade," +
                    "constraint fk2Matriculado foreign key(curso) references Curso(codigo) on delete cascade);";

    public RelDatabase(Context context) {
        super(context, "matricula", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(usuario);
        db.execSQL(estudiante);
        db.execSQL(curso);
        db.execSQL(matriculado);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
