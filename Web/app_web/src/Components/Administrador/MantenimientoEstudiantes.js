import React, {useState, useEffect} from 'react';
import MaterialTable from 'material-table';

import './MantenimientoEstudiantes.css'

export function MantenimientoEstudiantes(){
    const [state, setState] = React.useState({
      columns: [
        { title: 'Cedula', field: 'cedula', type: 'numeric'},
        { title: 'Nombre', field: 'nombre' },
        { title: 'Telefono', field: 'telefono', type: 'numeric'},
        { title: 'Email', field: 'email'}
      ],
      data: [],
    });

    const [carreras, setCarreras] = React.useState({})

    function getEstudiantes(){
        fetch("http://localhost:31762/Server/ServeletAlumnos",{
            method: 'GET'
            })
        .then(res => res.json())
        .then(
            (result) => {
                if(result.success == true){
                    setState({...state, ['data']: result.estudiantes})
                }else{
                    alert('Error: ' + result.error )
                }
            },
            (error) => {
                console.log(error)
            })
    }

    function getCarreras(){
        fetch("http://localhost:31762/Server/ServeletCarrera",{
            method: 'GET'
            })
        .then(res => res.json())
        .then(
            (result) => {
                if(result.success == true){
                    let obj = {}
                    result.carreras.forEach(element => {
                        obj[element.codigo] = element.nombre
                    });
                    let obj2 = {title: 'Carrera', field: 'carrera', lookup: obj}
                    let col = state.columns
                    col.push(obj2)
                    setState({...state, ['columns']:col})
                }else{
                    alert('Error: ' + result.error )
                }
            },
            (error) => {
                console.log(error)
            })
    }

    const makeRequest = (data,type) =>{
      if(type == 'PUT'){
          var url = 'http://localhost:31762/Server/ServeletAlumnos?action=PUT'
          var headers = {method: 'POST',body: JSON.stringify(data)}
      }
      if(type == 'DELETE'){
      var url = 'http://localhost:31762/Server/ServeletAlumnos?cedula=' + data.cedula + '&action=DELETE'
      var headers = {method: 'POST'}
      }
      if(type == 'POST'){
        var url = 'http://localhost:31762/Server/ServeletAlumnos'
        var headers = {method: type,body: JSON.stringify(data)}
      }
      console.log(data)
      console.log(headers)
      console.log(url)
        fetch(url,headers)
            .then(res => res.json())
        .then(
            (result) => {
                if(result.success == false){
                    alert('Error: ' + result.error )
                }
            },
            (error) => {
                console.log(error)
            })
        setState(state)
    }

    useEffect(() => {
        getCarreras()
        getEstudiantes()
        
      },[])
  
    return (
        <div>
      <MaterialTable
        title="Estudiantes"
        columns={state.columns}
        data={state.data}
        editable={{
          onRowAdd: newData =>
            new Promise(resolve => {
              setTimeout(() => {
                resolve();
                setState(prevState => {
                  const data = [...prevState.data];
                  data.push(newData);
                  makeRequest(data[data.length-1],'POST')
                  return { ...prevState, data };
                });
              }, 600);
            }),
          onRowUpdate: (newData, oldData) =>
            new Promise(resolve => {
              setTimeout(() => {
                resolve();
                if (oldData) {
                  setState(prevState => {
                    const data = [...prevState.data];
                    data[data.indexOf(oldData)] = newData;
                    makeRequest(newData,'PUT')
                    return { ...prevState, data };
                  });
                }
              }, 600);
            }),
          onRowDelete: oldData =>
            new Promise(resolve => {
              setTimeout(() => {
                resolve();
                setState(prevState => {
                  const data = [...prevState.data];
                  makeRequest({cedula: data[data.indexOf(oldData)].cedula},'DELETE')
                  data.splice(data.indexOf(oldData), 1);
                  return { ...prevState, data };
                });
              }, 600);
            }),
        }}
      />
      </div>
    );
  }