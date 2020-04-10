package Controllers;

import Logic.Carrera;
import Logic.Curso;
import Logic.Estudiante;
import Models.EstudiantesModel;
import Views.EstudiantesView;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Alessandro Fazio
 */
public class EstudiantesController {

    private EstudiantesModel estudiantesModel;
    private EstudiantesView estudiantesView;
    private String urlEstudiantes = "http://localhost:8080/Server/ServeletAlumnos";
    private String urlEstudiantesDelete = "http://localhost:8080/Server/ServeletAlumnos?cedula=%s";
    private String urlCarreras = "http://localhost:8080/Server/ServeletCarrera";

    public EstudiantesController(EstudiantesModel estudiantesModel, EstudiantesView estudiantesView) {
        this.estudiantesModel = estudiantesModel;
        this.estudiantesView = estudiantesView;
        this.estudiantesView.setController(this);
        this.estudiantesModel.addObserver(this.estudiantesView);
    }

    public void setVisible(boolean visible) {
        this.estudiantesView.setVisible(visible);
    }

    public EstudiantesModel getEstudiantesModel() {
        return estudiantesModel;
    }

    public void getEstudiantesRequest() throws Exception {
        HttpURLConnection connection = (HttpURLConnection) new URL(urlEstudiantes).openConnection();
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
            JSONArray estudiantes = jsonResponse.getJSONArray("estudiantes");

            estudiantesModel.getEstudiantes().clear();

            for (int i = 0; i < estudiantes.length(); ++i) {
                estudiantesModel.getEstudiantes().add(new Estudiante(estudiantes.getJSONObject(i).getInt("cedula") + "",
                        estudiantes.getJSONObject(i).getString("nombre"),
                        estudiantes.getJSONObject(i).getInt("telefono") + "",
                        estudiantes.getJSONObject(i).getString("email"),
                        new Carrera(estudiantes.getJSONObject(i).getInt("carrera"), estudiantes.getJSONObject(i).getString("nombreCarrera")),
                        null, null));
            }

            estudiantesModel.notificar("Estudiantes");
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

            estudiantesModel.getCarreras().clear();

            for (int i = 0; i < carreras.length(); ++i) {
                estudiantesModel.getCarreras().add(new Carrera(carreras.getJSONObject(i).getInt("codigo"),
                        carreras.getJSONObject(i).getString("nombre")));
            }

            estudiantesModel.notificar("Carreras");

        } else {
            throw new Exception(jsonResponse.getString("Error"));
        }
    }

    public void putEstudianteRequest(Estudiante estudiante, int index) throws Exception {
        HttpURLConnection connection = (HttpURLConnection) new URL(urlEstudiantes).openConnection();
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);

        estudiantesModel.getEstudiantes().get(index).setNombre(estudiante.getNombre());
        estudiantesModel.getEstudiantes().get(index).setTelefono(estudiante.getTelefono());
        estudiantesModel.getEstudiantes().get(index).setEmail(estudiante.getEmail());
        estudiantesModel.getEstudiantes().get(index).setCarrera(estudiante.getCarrera());

        JSONObject request = new JSONObject();
        request.put("cedula", Integer.parseInt(estudiante.getCedula()));
        request.put("nombre", estudiante.getNombre());
        request.put("telefono", Integer.parseInt(estudiante.getTelefono()));
        request.put("email", estudiante.getEmail());
        request.put("carrera", estudiante.getCarrera().getCodigo());

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
            estudiantesModel.notificar("Estudiantes");

        } else {
            throw new Exception(jsonResponse.getString("Error"));
        }
    }

    public void postEstudianteRequest(Estudiante estudiante) throws Exception {
        HttpURLConnection connection = (HttpURLConnection) new URL(urlEstudiantes).openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);

        JSONObject request = new JSONObject();
        request.put("cedula", Integer.parseInt(estudiante.getCedula()));
        request.put("nombre", estudiante.getNombre());
        request.put("telefono", Integer.parseInt(estudiante.getTelefono()));
        request.put("email", estudiante.getNombre());
        request.put("carrera", estudiante.getCarrera().getCodigo());
        request.put("usuario", estudiante.getUsuario().getUsuario());
        request.put("clave", estudiante.getUsuario().getClave());

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
            this.getEstudiantesRequest();

        } else {
            throw new Exception(jsonResponse.getString("Error"));
        }
    }

    public void deleteEstudianteRequest(String cedula) throws Exception {
        HttpURLConnection connection = (HttpURLConnection) new URL(String.format(urlEstudiantesDelete, cedula)).openConnection();
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
            this.getEstudiantesRequest();

        } else {
            throw new Exception(jsonResponse.getString("Error"));
        }
    }
}
