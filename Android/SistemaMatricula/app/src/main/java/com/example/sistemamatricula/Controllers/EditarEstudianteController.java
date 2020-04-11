package com.example.sistemamatricula.Controllers;

import com.example.sistemamatricula.Data.Data;
import com.example.sistemamatricula.Models.EditarEstudianteModel;
import com.example.sistemamatricula.Views.Activities.EditarEstudianteActivity;

import java.util.ArrayList;

import Logic.Carrera;
import Logic.Estudiante;

public class EditarEstudianteController {

    private EditarEstudianteModel editarEstudianteModel;

    public EditarEstudianteController(EditarEstudianteModel editarEstudianteModel, EditarEstudianteActivity editarEstudianteActivity) {
        this.editarEstudianteModel = editarEstudianteModel;
        this.editarEstudianteModel.addObserver(editarEstudianteActivity);
    }

    public void getCarrerasRequest() {
        editarEstudianteModel.getCarreras().addAll(Data.getInstance().getCarreras());
        editarEstudianteModel.notificar("Carreras obtenidas");
    }

    public void putEstudianteRequest(String nombre, String email, String telefono, String carrera) {
        for (Estudiante estudiante : Data.getInstance().getEstudiantes()) {
            if (estudiante.getCedula().equals(editarEstudianteModel.getEstudiante().getCedula())) {
                estudiante.setNombre(nombre);
                estudiante.setEmail(email);
                estudiante.setTelefono(telefono);

                editarEstudianteModel.getEstudiante().setNombre(nombre);
                editarEstudianteModel.getEstudiante().setEmail(email);
                editarEstudianteModel.getEstudiante().setTelefono(telefono);

                for (Carrera carrera1 : Data.getInstance().getCarreras()) {
                    if (carrera1.getNombre().equals(carrera)) {
                        estudiante.setCarrera(carrera1);

                        editarEstudianteModel.getEstudiante().setCarrera(carrera1);
                        editarEstudianteModel.notificar("Estudiante actualizado");
                        return;
                    }
                }
            }
        }

    }
}
