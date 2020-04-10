package Controllers;

import Logic.Carrera;
import Logic.Curso;
import Models.CursosModel;
import Views.CursosView;
import java.io.BufferedReader;
import java.io.InputStreamReader;
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
    private String url = "http://localhost:8080/Server/ServeletCursos";

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
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
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

            for (int i = 0; i < cursos.length(); ++i) {
                cursosModel.getCursos().add(new Curso(cursos.getJSONObject(i).getInt("codigo"),
                        cursos.getJSONObject(i).getInt("creditos"),
                        cursos.getJSONObject(i).getInt("horasSemanales"),
                        cursos.getJSONObject(i).getString("nombre"),
                        new Carrera(cursos.getJSONObject(i).getInt("carrera"), cursos.getJSONObject(i).getString("nomnbreCarrera"))));
            }

            //estudiantesModel.notificar();

        } else {
            throw new Exception(jsonResponse.getString("Error"));
        }
    }
}
