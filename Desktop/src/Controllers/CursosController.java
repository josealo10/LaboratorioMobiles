package Controllers;

import Logic.Carrera;
import Logic.Curso;
import Models.CursosModel;
import Views.CursosView;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Alessandro Fazio
 */
public class CursosController {

    private CursosModel cursosModel;
    private CursosView cursosView;
    private String urlCursos = "http://localhost:8080/Server/ServeletCursos";
    private String urlCursosDelete = "http://localhost:8080/Server/ServeletCursos?codigo=%d";
    private String urlCarreras = "http://localhost:8080/Server/ServeletCarrera";

    public CursosController(CursosModel cursosModel, CursosView cursosView) {
        this.cursosModel = cursosModel;
        this.cursosView = cursosView;
        this.cursosView.setController(this);
        this.cursosModel.addObserver(this.cursosView);
    }

    public void setVisible(boolean visible) {
        this.cursosView.setVisible(visible);
    }

    public CursosModel getCursosModel() {
        return cursosModel;
    }

    public void getCursosRequest() throws Exception {
        HttpURLConnection connection = (HttpURLConnection) new URL(urlCursos).openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
        String responseLine;
        StringBuilder response = new StringBuilder();

        while ((responseLine = in.readLine()) != null) {
            response.append(responseLine.trim());
        }

        in.close();

        JSONObject jsonResponse = new JSONObject(response.toString());

        if (jsonResponse.getBoolean("success")) {
            JSONArray cursos = jsonResponse.getJSONArray("cursos");

            cursosModel.getCursos().clear();

            for (int i = 0; i < cursos.length(); ++i) {
                cursosModel.getCursos().add(new Curso(cursos.getJSONObject(i).getInt("codigo"),
                        cursos.getJSONObject(i).getInt("creditos"),
                        cursos.getJSONObject(i).getInt("horasSemanales"),
                        cursos.getJSONObject(i).getString("nombre"),
                        new Carrera(cursos.getJSONObject(i).getInt("carrera"), cursos.getJSONObject(i).getString("nombreCarrera"))));
            }

            cursosModel.notificar("Cursos");

        } else {
            throw new Exception(jsonResponse.getString("Error"));
        }
    }

    public void getCarrerasRequest() throws Exception {
        HttpURLConnection connection = (HttpURLConnection) new URL(urlCarreras).openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
        String responseLine;
        StringBuilder response = new StringBuilder();

        while ((responseLine = in.readLine()) != null) {
            response.append(responseLine.trim());
        }

        in.close();

        JSONObject jsonResponse = new JSONObject(response.toString());

        if (jsonResponse.getBoolean("success")) {
            JSONArray carreras = jsonResponse.getJSONArray("carreras");

            cursosModel.getCarreras().clear();

            for (int i = 0; i < carreras.length(); ++i) {
                cursosModel.getCarreras().add(new Carrera(carreras.getJSONObject(i).getInt("codigo"),
                        carreras.getJSONObject(i).getString("nombre")));
            }

            cursosModel.notificar("Carreras");

        } else {
            throw new Exception(jsonResponse.getString("Error"));
        }
    }

    public void putCursoRequest(Curso curso, int index) throws Exception {
        HttpURLConnection connection = (HttpURLConnection) new URL(urlCursos).openConnection();
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);

        cursosModel.getCursos().get(index).setCarrera(curso.getCarrera());
        cursosModel.getCursos().get(index).setCreditos(curso.getCreditos());
        cursosModel.getCursos().get(index).setHorasSemanales(curso.getHorasSemanales());
        cursosModel.getCursos().get(index).setNombre(curso.getNombre());

        JSONObject request = new JSONObject();
        request.put("codigo", curso.getCodigo());
        request.put("nombre", curso.getNombre());
        request.put("creditos", curso.getCreditos());
        request.put("horasSemanales", curso.getHorasSemanales());
        request.put("carrera", curso.getCarrera().getCodigo());

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = request.toString().getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
        String responseLine;
        StringBuilder response = new StringBuilder();

        while ((responseLine = in.readLine()) != null) {
            response.append(responseLine.trim());
        }

        in.close();

        JSONObject jsonResponse = new JSONObject(response.toString());

        if (jsonResponse.getBoolean("success")) {
            cursosModel.notificar("Cursos");

        } else {
            throw new Exception(jsonResponse.getString("Error"));
        }
    }

    public void postCursoRequest(Curso curso) throws Exception {
        HttpURLConnection connection = (HttpURLConnection) new URL(urlCursos).openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);

        JSONObject request = new JSONObject();
        request.put("nombre", curso.getNombre());
        request.put("creditos", curso.getCreditos());
        request.put("horasSemanales", curso.getHorasSemanales());
        request.put("carrera", curso.getCarrera().getCodigo());

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = request.toString().getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
        String responseLine;
        StringBuilder response = new StringBuilder();

        while ((responseLine = in.readLine()) != null) {
            response.append(responseLine.trim());
        }

        in.close();

        JSONObject jsonResponse = new JSONObject(response.toString());

        if (jsonResponse.getBoolean("success")) {
            this.getCursosRequest();

        } else {
            throw new Exception(jsonResponse.getString("Error"));
        }
    }

    public void deleteCursoRequest(int codigo) throws Exception {
        HttpURLConnection connection = (HttpURLConnection) new URL(String.format(urlCursosDelete, codigo)).openConnection();
        connection.setRequestMethod("DELETE");
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
        String responseLine;
        StringBuilder response = new StringBuilder();

        while ((responseLine = in.readLine()) != null) {
            response.append(responseLine.trim());
        }

        in.close();

        JSONObject jsonResponse = new JSONObject(response.toString());

        if (jsonResponse.getBoolean("success")) {
            this.getCursosRequest();

        } else {
            throw new Exception(jsonResponse.getString("Error"));
        }
    }
}
