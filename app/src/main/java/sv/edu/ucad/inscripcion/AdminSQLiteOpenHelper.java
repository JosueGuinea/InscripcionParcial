package sv.edu.ucad.inscripcion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    String sqlqueryUniversidad = ("CREATE TABLE universidad(" +
            "IdUniversidad INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "NombreUniversidad TEXT NOT NULL," +
            "CodigoUniversidad CHAR(10));");

    String sqlquerySedes = ("CREATE TABLE sedes(" +
            "IdSede INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "IdUniversidad INTEGER REFERENCES universidad(IdUniversidad)," +
            "Departamento TEXT," +
            "CodSede TEXT);");

    String sqlqueryFacultad = ("CREATE TABLE facultad(" +
            "IdFacultad INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "NombreFacultad TEXT," +
            "CodigoFacultad CHAR(10)," +
            "IdSede INTEGER);");

    String sqlcatalogocarrera = ("CREATE TABLE catcarrera(" +
            "CatCarreraId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "CatCarreraNombre TEXT," +
            "CatCarreraAlias TEXT," +
            "CatCarreraCodigo TEXT);");

    String sqlqueryCarrera = ("CREATE TABLE carrera(" +
            "IdCarrera INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "CatCarreraId INTEGER REFERENCES catcarrera(CatCarreraId)," +
            "IdFacultad INTEGER REFERENCES facultad(IdFacultad));");

    String sqlcatalogomateria = ("CREATE TABLE catmateria(" +
            "IdCatMateria INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "CatMatNombre TEXT," +
            "CatMatAlias TEXT," +
            "CatMatUV INTEGER," +
            "CatMatCorrelativo INTEGER," +
            "CatMatPrerrequisito);");

    String sqlcatalogodia = ("CREATE TABLE catdia(" +
            "CatDiaId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "Dias TEXT);");

    String sqldia = ("CREATE TABLE dia(" +
            "IdDia INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "CatDiaId INTEGER REFERENCES catdia(CatDiaId));");

    String sqlcatalogohorario = ("CREATE TABLE cathorario(" +
            "CatHorarioId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "HoraInicio TIME," +
            "HoraFinalizacion TIME," +
            "IdDia INTEGER REFERENCES dia(IdDia));");

    String sqlhorario = ("CREATE TABLE horario(" +
            "IdHorario INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "CatHorarioId INTEGER REFERENCES cathorario(CatHorarioId));");

    String sqlciclo = ("CREATE TABLE ciclo(" +
            "IdCiclo INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "CicloAlias TEXT);");

    String sqlmateria = ("CREATE TABLE materia(" +
            "IdMateria INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "IdCatMateria INTEGER REFERENCES catmateria(IdCatMateria)," +
            "IdCarrera INTEGER REFERENCES carrera(IdCarrera)," +
            "IdCiclo INTEGER REFERENCES ciclo(IdCiclo)," +
            "IdHorario INTEGER REFERENCES horario(IdHorario));");

    String sqlestudiante = ("CREATE TABLE estudiante(" +
            "IdEstudiante INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "NombreEstudiante TEXT NOT NULL," +
            "ApellidosEstudiante TEXT NOT NULL," +
            "FechaNacimiento DATE," +
            "CodCarnet CHAR(8),"+
            "Genero TEXT);");

    String sqlusuario = ("CREATE TABLE usuario(" +
            "IdUsuaruioEstudiante INTEGER REFERENCES estudiante(IdEstudiante) PRIMARY KEY," +
            "UsuarioNick TEXT," +
            "UsuarioClave TEXT," +
            "UsuarioEmail TEXT," +
            "UsuarioEstado INTEGER);");

    String sqlinscripcion = ("CREATE TABLE inscripcion(" +
            "IdInscripcion INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "IdEstudiante INTEGER REFERENCES usuario(IdUsuarioEstudiante)," +
            "CarreraId TEXT," +
            "Ciclo INTEGER);");

    String sqldetinscripcion = ("CREATE TABLE detinscripcion(" +
            "IdMatricula INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "IdInscripcion INTEGER REFERENCES inscripcion(IdInscripcion)," +
            "IdMateria INTEGER REFERENCES materia(IdMateria)," +
            "MateriaEstado INTEGER," +
            "IntentosMateria INTEGER);");

    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlqueryUniversidad);
        db.execSQL(sqlquerySedes);
        db.execSQL(sqlqueryFacultad);
        db.execSQL(sqlcatalogocarrera);
        db.execSQL(sqlqueryCarrera);
        db.execSQL(sqlcatalogomateria);
        db.execSQL(sqlcatalogodia);
        db.execSQL(sqldia);
        db.execSQL(sqlcatalogohorario);
        db.execSQL(sqlhorario);
        db.execSQL(sqlciclo);
        db.execSQL(sqlmateria);
        db.execSQL(sqlestudiante);
        db.execSQL(sqlusuario);
        db.execSQL(sqlinscripcion);
        db.execSQL(sqldetinscripcion);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int VersionAnt, int VersionNueva) {
        db.execSQL("DROP TABLE IF EXISTS universidad");
        db.execSQL(sqlqueryUniversidad);
        db.execSQL("DROP TABLE IF EXISTS sedes");
        db.execSQL(sqlquerySedes);
        db.execSQL("DROP TABLE IF EXISTS facultad");
        db.execSQL(sqlqueryFacultad);
        db.execSQL("DROP TABLE IF EXISTS catcarrera");
        db.execSQL(sqlcatalogocarrera);
        db.execSQL("DROP TABLE IF EXISTS carrera");
        db.execSQL(sqlqueryCarrera);
        db.execSQL("DROP TABLE IF EXISTS catmateria");
        db.execSQL(sqlcatalogomateria);
        db.execSQL("DROP TABLE IF EXISTS catdia");
        db.execSQL(sqlcatalogodia);
        db.execSQL("DROP TABLE IF EXISTS dia");
        db.execSQL(sqldia);
        db.execSQL("DROP TABLE IF EXISTS cathorario");
        db.execSQL(sqlcatalogohorario);
        db.execSQL("DROP TABLE IF EXISTS horario");
        db.execSQL(sqlhorario);
        db.execSQL("DROP TABLE IF EXISTS ciclo");
        db.execSQL(sqlciclo);
        db.execSQL("DROP TABLE IF EXISTS materia");
        db.execSQL(sqlmateria);
        db.execSQL("DROP TABLE IF EXISTS estudiante");
        db.execSQL(sqlestudiante);
        db.execSQL("DROP TABLE IF EXISTS usuario");
        db.execSQL(sqlusuario);
        db.execSQL("DROP TABLE IF EXISTS inscripcion");
        db.execSQL(sqlinscripcion);
        db.execSQL("DROP TABLE IF EXISTS detinscripcion");
        db.execSQL(sqldetinscripcion);

    }
}