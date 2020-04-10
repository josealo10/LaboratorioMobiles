import React, {useState, useEffect} from 'react';
import MaterialTable from 'material-table';
import $ from "jquery"

import './CursosMatriculados.css'

export function CursosMatriculados(){
    //alert(JSON.parse(localStorage.getItem('user')))
    const [user, setUser] = React.useState(JSON.parse(localStorage.getItem('user')));
    const [state, setState] = React.useState({
        columns: [
          { title: 'Codigo', field: 'codigo', type: 'numeric', editable: 'never'},
          { title: 'Nombre', field: 'nombre' },
          { title: 'Creditos', field: 'creditos', type: 'numeric'},
          { title: 'Horas semanales', field: 'horasSemanales', type: 'numeric' },
          { title: 'Carrera', field:'carrera'}
        ],
        data: [],
      });
  
      const [carreras, setCarreras] = React.useState({})

      function getCursos(){
        fetch("http://localhost:31762/Server/ServeletAlumnos?cedula="+user.cedula,{
            method: 'GET'
            })
        .then(res => res.json())
        .then(
            (result) => {
                if(result.success == true){
                    setState({...state, ['data']: result.cursos})
                }else{
                    alert('Error: ' + result.error )
                }
            },  
            (error) => {
                console.log(error)
            })
    }

    useEffect(() => {
        //setUser(JSON.parse(localStorage.getItem('user')));
        //alert(JSON.stringify(JSON.parse(localStorage.getItem('user'))))
        getCursos()
      },[])

    return (
        <div>
            <MaterialTable
                title="Cursos matriculados"
                columns={state.columns}
                data={state.data}
            />
      </div>
    );
}