import React, { useEffect} from 'react';
import MaterialTable from 'material-table';

import './MantenimientoCursos.css'

export function MantenimientoCursos(){
    const [state, setState] = React.useState({
      columns: [
        { title: 'Codigo', field: 'codigo', type: 'numeric', editable: 'never'},
        { title: 'Nombre', field: 'nombre' },
        { title: 'Creditos', field: 'creditos', type: 'numeric'},
        { title: 'Horas semanales', field: 'horasSemanales', type: 'numeric' }
      ],
      data: [],
    });

    function getCursos(){
        fetch("http://localhost:8080/Server/ServeletCursos?acction=GET",{
            method: 'GET'
            })
        .then(res => res.json())
        .then(
            (result) => {
                if(result.success === true){
                    setState({...state, data: result.cursos})
                }else{
                    alert('Error: ' + result.error )
                }
            },
            (error) => {
                console.log(error)
            })
    }

    function getCarreras(){
        fetch("http://localhost:8080/Server/ServeletCarrera",{
            method: 'GET'
            })
        .then(res => res.json())
        .then(
            (result) => {
                if(result.success === true){
                    let obj = {}
                    result.carreras.forEach(element => {
                        obj[element.codigo] = element.nombre
                    });
                    let obj2 = {title: 'Carrera', field: 'carrera', lookup: obj}
                    let col = state.columns
                    col.push(obj2)
                    setState({...state, columns:col})
                }else{
                    alert('Error: ' + result.error )
                }
            },
            (error) => {
                console.log(error)
            })
    }

    const makeRequest = (data,type) =>{
      var url,headers
      if(type === 'DELETE'){
       url = 'http://localhost:8080/Server/ServeletCursos?codigo=' + data.codigo + '&action=DELETE'
       headers = {method: 'POST'}
      }
      if(type === 'PUT'){
         url = 'http://localhost:8080/Server/ServeletCursos?action=PUT'
         headers = {method: 'POST',body: JSON.stringify(data)}
        }
      if(type === 'POST'){
         url = 'http://localhost:8080/Server/ServeletCursos'
         headers = {method: type,body: JSON.stringify(data)}
      }

        fetch(url,headers)
            .then(res => res.json())
        .then(
            (result) => {
                if(result.success === false){
                    alert('Error: ' + result.error )
                }else{
                  getCursos()
                }
            },
            (error) => {
                console.log(error)
            })
    }

    useEffect(() => {
        getCarreras()
        getCursos()
      },[])
  
    return (
        <div>
      <MaterialTable
        title="Cursos"
        columns={state.columns}
        data={state.data}
        editable={{
          onRowAdd: newData =>
            new Promise(resolve => {
              setTimeout(() => {
                resolve();
                makeRequest(newData,'POST')
              }, 600);
            }),
          onRowUpdate: (newData, oldData) =>
            new Promise(resolve => {
              setTimeout(() => {
                resolve();
                if (oldData) {
                  makeRequest(newData,'PUT')
                }
              }, 600);
            }),
          onRowDelete: oldData =>
            new Promise(resolve => {
              setTimeout(() => {
                resolve();
                if (oldData) {
                  makeRequest(oldData,'DELETE')
                }
              }, 600);
            }),
        }}
      />
      </div>
    );
  }