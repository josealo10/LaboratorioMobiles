import React, {useState, useEffect} from 'react';
import MaterialTable from 'material-table';
import $ from "jquery"

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

    const [carreras, setCarreras] = React.useState({})

    function getCursos(){
        fetch("http://localhost:31762/Server/ServeletCursos?acction=GET",{
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

      if(type == 'DELETE'){
      var url = 'http://localhost:31762/Server/ServeletCursos?codigo=' + data.codigo + '&action=DELETE'
      var headers = {method: 'POST'}
      }
      if(type == 'PUT'){
        var url = 'http://localhost:31762/Server/ServeletCursos?action=PUT'
        var headers = {method: 'POST',body: JSON.stringify(data)}
        }
      if(type == 'POST'){
        var url = 'http://localhost:31762/Server/ServeletCursos'
        var headers = {method: type,body: JSON.stringify(data)}
      }

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
                    makeRequest(data[data.indexOf(oldData)],'PUT')
                    data[data.indexOf(oldData)] = newData;
                    makeRequest(newData,'PUT')
                    console.log(newData)
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
                  makeRequest({codigo: data[data.indexOf(oldData)].codigo},'DELETE')
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