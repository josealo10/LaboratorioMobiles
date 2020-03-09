import React, {useState, useEffect} from 'react';
import MaterialTable from 'material-table';

import './MantenimientoCursos.css'

export function MantenimientoCursos(){
    const [state, setState] = React.useState({
      columns: [
        { title: 'Codigo', field: 'codigo', type: 'numeric', editable: 'never'},
        { title: 'Nombre', field: 'nombre' },
        { title: 'Creditos', field: 'creditos', type: 'numeric'},
        { title: 'Horas semanales', field: 'horasSemanales', type: 'numeric' },
        { title: 'Carrera', field: 'carrera'}
      ],
      data: [],
    });

    const [carreras, setCarreras] = React.useState({})

    function getCursos(){
        fetch("http://localhost:31762/Server/ServeletCursos",{
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
                    console.log(result.carreras)
                    let obj = {}
                    result.carreras.forEach(element => {
                        console.log(element)
                        obj[element.codigo] = element.nombre
                        console.log(obj)
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

    const insertCurso = (curso) =>{
        console.log(JSON.stringify(curso))
        fetch("http://localhost:31762/Server/ServeletCurso",{
            method: 'PUT',
             body: JSON.stringify(curso)
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
                setState(prevState => {
                  const data = [...prevState.data];
                  data.push(newData);
                  //insertCurso(data[data.length-1])
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