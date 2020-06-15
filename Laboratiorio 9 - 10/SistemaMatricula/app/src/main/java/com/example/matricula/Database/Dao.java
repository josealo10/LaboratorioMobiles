package com.example.matricula.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.matricula.Logic.Curso;
import com.example.matricula.Logic.Estudiante;
import com.example.matricula.Logic.Usuario;

import java.util.ArrayList;

public class Dao {

    private static Dao instance = null;
    private final String getEstudiantes = "select * from Estudiante, Usuario where Estudiante.usuario = Usuario.usuario";
    private final String getCursos = "select * from Curso";
    private final String getUsuario = "select * from Usuario where Usuario.usuario = ? and Usuario.clave = ?";
    private final String getEstudiante = "select * from Estudiante where Estudiante.usuario = ?";
    private final String getCursosMatriculados = "select Curso.* from Matriculado, Curso where Matriculado.curso = Curso.codigo and Matriculado.estudiante = ?";
    private RelDatabase relDatabase;

    private Dao(Context context) {
        relDatabase = new RelDatabase(context);
    }

    public static Dao getInstance(Context context) {
        if (instance == null) {
            instance = new Dao(context);
        }

        return instance;
    }

    public ArrayList<Estudiante> getEstudiantes() throws Exception {
        Cursor cursor = relDatabase.getWritableDatabase().rawQuery(getEstudiantes, null);

        if (cursor.getCount() == 0) {
            throw new Exception("No hay estudiantes");
        }

        ArrayList<Estudiante> estudiantes = new ArrayList<>();

        while (cursor.moveToNext()) {
            estudiantes.add(new Estudiante(cursor.getString(cursor.getColumnIndex("cedula")),
                    cursor.getString(cursor.getColumnIndex("nombre")),
                    cursor.getString(cursor.getColumnIndex("apellidos")),
                    cursor.getInt(cursor.getColumnIndex("edad")),
                    new Usuario(cursor.getString(cursor.getColumnIndex("usuario")), cursor.getString(cursor.getColumnIndex("clave")), "estudiante"),
                    new ArrayList<>()));
        }

        return estudiantes;
    }

    public Estudiante getEstudiante(String usuario) {
        Cursor cursor = relDatabase.getWritableDatabase().rawQuery(getEstudiante, new String[]{usuario});

        cursor.moveToNext();

        return new Estudiante(cursor.getString(cursor.getColumnIndex("cedula")),
                cursor.getString(cursor.getColumnIndex("nombre")),
                cursor.getString(cursor.getColumnIndex("apellidos")),
                cursor.getInt(cursor.getColumnIndex("edad")),
                null,
                new ArrayList<>());
    }

    public void postEstudiante(Estudiante estudiante) throws Exception {
        ContentValues contentValues = new ContentValues();
        contentValues.put("cedula", estudiante.getCedula());
        contentValues.put("nombre", estudiante.getNombre());
        contentValues.put("apellidos", estudiante.getApellidos());
        contentValues.put("edad", estudiante.getEdad());
        contentValues.put("usuario", estudiante.getUsuario().getUsuario());

        if (relDatabase.getWritableDatabase().insert("Estudiante", null, contentValues) == -1) {
            throw new Exception("Estudiante ya existe");
        }
    }

    public void deleteEstudiante(String cedula, String usuario) {
        relDatabase.getWritableDatabase().delete("Estudiante", "cedula = ?", new String[]{cedula});
        deleteUsuario(usuario);
    }

    public void putEstudiante(Estudiante estudiante) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", estudiante.getNombre());
        contentValues.put("apellidos", estudiante.getApellidos());
        contentValues.put("edad", estudiante.getEdad());

        relDatabase.getWritableDatabase().update("Estudiante", contentValues, "cedula = ?", new String[]{estudiante.getCedula()});
    }

    public ArrayList<Curso> getCursosMatriculados(String cedula) throws Exception {
        Cursor cursor = relDatabase.getWritableDatabase().rawQuery(getCursosMatriculados, new String[]{cedula});

        if (cursor.getCount() == 0) {
            throw new Exception("No hay tiene cursos matriculados");
        }

        ArrayList<Curso> cursos = new ArrayList<>();

        while (cursor.moveToNext()) {
            cursos.add(new Curso(cursor.getString(cursor.getColumnIndex("codigo")),
                    cursor.getString(cursor.getColumnIndex("nombre")),
                    cursor.getInt(cursor.getColumnIndex("creditos"))));
        }

        return cursos;
    }

    public void postMatriculado(String estudiante, String curso) throws Exception {
        ContentValues contentValues = new ContentValues();
        contentValues.put("estudiante", estudiante);
        contentValues.put("curso", curso);

        if (relDatabase.getWritableDatabase().insert("Matriculado", null, contentValues) == -1) {
            throw new Exception("Ya matriculó este curso");
        }
    }

    public void deleteMatriculado(String estudiante, String curso) {
        relDatabase.getWritableDatabase().delete("Matriculado", "estudiante = ? and curso = ?", new String[]{estudiante, curso});
    }

    public ArrayList<Curso> getCursos() throws Exception {
        Cursor cursor = relDatabase.getWritableDatabase().rawQuery(getCursos, null);

        if (cursor.getCount() == 0) {
            throw new Exception("No hay cursos");
        }

        ArrayList<Curso> cursos = new ArrayList<>();

        while (cursor.moveToNext()) {
            cursos.add(new Curso(cursor.getString(cursor.getColumnIndex("codigo")),
                    cursor.getString(cursor.getColumnIndex("nombre")),
                    cursor.getInt(cursor.getColumnIndex("creditos"))));
        }

        return cursos;
    }

    public void postCurso(Curso curso) throws Exception {
        ContentValues contentValues = new ContentValues();
        contentValues.put("codigo", curso.getCodigo());
        contentValues.put("nombre", curso.getNombre());
        contentValues.put("creditos", curso.getCreditos());

        if (relDatabase.getWritableDatabase().insert("Curso", null, contentValues) == -1) {
            throw new Exception("Curso ya existe");
        }
    }

    public void deleteCurso(String cedula) {
        relDatabase.getWritableDatabase().delete("Curso", "codigo = ?", new String[]{cedula});
    }

    public void putCurso(Curso curso) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", curso.getNombre());
        contentValues.put("creditos", curso.getCreditos());

        relDatabase.getWritableDatabase().update("Curso", contentValues, "codigo = ?", new String[]{curso.getCodigo()});
    }

    public String getUsuario(String usuario, String clave) throws Exception {
        Cursor cursor = relDatabase.getWritableDatabase().rawQuery(getUsuario, new String[]{usuario, clave});

        if (cursor.getCount() == 0) {
            throw new Exception("Credenciales incorrectos");
        }

        cursor.moveToNext();
        return cursor.getString(cursor.getColumnIndex("rol"));
    }

    public void postUsuario(Usuario usuario) throws Exception {
        ContentValues contentValues = new ContentValues();
        contentValues.put("usuario", usuario.getUsuario());
        contentValues.put("clave", usuario.getClave());
        contentValues.put("rol", usuario.getRol());

        if (relDatabase.getWritableDatabase().insert("Usuario", null, contentValues) == -1) {
            throw new Exception("Usuario ya existe");
        }
    }

    public void deleteUsuario(String usuario) {
        relDatabase.getWritableDatabase().delete("Usuario", "usuario = ?", new String[]{usuario});
    }
}
